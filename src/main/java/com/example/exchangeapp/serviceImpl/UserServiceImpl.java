package com.example.exchangeapp.serviceImpl;

import java.util.Objects;

import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.DTO.UserDTO;
import com.example.exchangeapp.constant.Rate;
import com.example.exchangeapp.mapper.PersonalInfoMapper;
import com.example.exchangeapp.mapper.UserMapper;
import com.example.exchangeapp.model.PersonalInfo;
import com.example.exchangeapp.model.User;
import com.example.exchangeapp.repository.UserRepository;
import com.example.exchangeapp.service.ExchangeService;
import com.example.exchangeapp.service.UserService;
import com.example.exchangeapp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PersonalInfoMapper personalInfoMapper;

    private final PasswordEncoder passwordEncoder;

    private final ReactiveUserDetailsService reactiveUserDetailsService;

    private final ExchangeService exchangeService;

    @Override
    public Mono<UserDTO> changeName(String id, PersonalInfoDTO newInfo) {

        PersonalInfo newInfoEntity = personalInfoMapper.toEntity(newInfo);

        return userRepository.findById(id)
                             .map(user -> setOnlyNotNull(user, newInfoEntity))
                             .flatMap(userRepository::save)
                             .map(userMapper::toDTO);
    }

    @Override
    public Mono<UserDTO> buy(String userId, Long price) {

        return userRepository.findById(userId)
                             .doOnNext(user -> user.setRubAcc(user.getRubAcc() - price))
                             .flatMap(userRepository::save)
                             .map(userMapper::toDTO);

    }

    @Override
    public Mono<UserDTO> addMoney(String userId, Long amount) {

        return userRepository.findById(userId)
                             .doOnNext(user -> user.setRubAcc(user.getRubAcc() + amount))
                             .flatMap(userRepository::save)
                             .map(userMapper::toDTO);
    }

    @Override
    public Mono<UserDTO> create(UserDTO newUser) {

        User user = userMapper.toEntity(newUser);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(user)
                             .map(userMapper::toDTO);
    }

    @Override
    public Mono<Void> delete(String objectId) {

        return userRepository.deleteById(objectId);
    }

    @Override
    public Mono<UserDTO> userInfo(String userId) {

        return userRepository.findById(userId).map(userMapper::toDTO);
    }

    public Mono<ResponseEntity<?>> login(UserDTO userDTO) {

        return reactiveUserDetailsService.findByUsername(userDTO.getLogin())
                                         .map(user ->
                                                  passwordEncoder.matches(userDTO.getPassword(), user.getPassword())
                                                  ? ResponseEntity.ok(JwtUtil.generateToken(user))
                                                  : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                                             )
                                         .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public Mono<Double> convert(String userId, Rate from, Rate to, Double amount) {

        return userRepository.findById(userId).doOnNext(user ->

            exchangeService.exchange(from.name(), to.name(), amount)
                           .doOnNext(convertedAmount -> {
           switch (to) {
                case EUR -> user.setEurAcc(convertedAmount);
                case USD -> user.setUsdAcc(convertedAmount);
                case RUB -> user.setRubAcc(convertedAmount);
            }
        }
        )).flatMap(userRepository::save).map(user -> switch (to) {
            case EUR -> user.getEurAcc();
            case USD -> user.getUsdAcc();
            case RUB -> user.getRubAcc();
        });
    }

    private User setOnlyNotNull(User user, PersonalInfo newInfo) {

        if (Objects.isNull(user.getPersonalInfo())) {
            user.setPersonalInfo(newInfo);

            return user;
        }

        user.getPersonalInfo()
            .setBankIds(newInfo.getBankIds() == null ? user.getPersonalInfo().getBankIds() : newInfo.getBankIds());
        user.getPersonalInfo()
            .setLastName(newInfo.getLastName() == null ? user.getPersonalInfo().getLastName() : newInfo.getLastName());
        user.getPersonalInfo().setFirstName(
            newInfo.getFirstName() == null ? user.getPersonalInfo().getFirstName() : newInfo.getFirstName());
        user.getPersonalInfo()
            .setMidName(newInfo.getMidName() == null ? user.getPersonalInfo().getMidName() : newInfo.getMidName());

        return user;
    }
}
