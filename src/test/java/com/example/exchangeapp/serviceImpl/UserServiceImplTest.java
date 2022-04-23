package com.example.exchangeapp.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.DTO.UserDTO;
import com.example.exchangeapp.DTO.feign.ExchangeResponseDTO;
import com.example.exchangeapp.constant.Rate;
import com.example.exchangeapp.feign.ExchangeFeign;
import com.example.exchangeapp.mapper.PersonalInfoMapper;
import com.example.exchangeapp.mapper.UserMapper;
import com.example.exchangeapp.model.PersonalInfo;
import com.example.exchangeapp.model.User;
import com.example.exchangeapp.repository.UserRepository;
import com.example.exchangeapp.service.ExchangeService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PersonalInfoMapper personalInfoMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ExchangeFeign exchangeFeign;

    @Mock
    private ExchangeService exchangeService;

    @InjectMocks
    private UserServiceImpl userService;

    private static User userStub;

    private static UserDTO userDTOStub;

    private static PersonalInfoDTO personalInfoDTOStub;

    private static PersonalInfo personalInfo;

    @BeforeAll
    private static void init() {

        final EasyRandom EASY_RANDOM = new EasyRandom();

        userStub = EASY_RANDOM.nextObject(User.class);
        userDTOStub = EASY_RANDOM.nextObject(UserDTO.class);
        personalInfoDTOStub = EASY_RANDOM.nextObject(PersonalInfoDTO.class);
        personalInfo = EASY_RANDOM.nextObject(PersonalInfo.class);

    }

    @BeforeEach
    private void beforeEach() {

        when(userRepository.save(any())).thenReturn(Mono.just(userStub));
        when(userRepository.findById(anyString())).thenReturn(Mono.just(userStub));
        when(userMapper.toDTO(any())).thenReturn(userDTOStub);
        when(userMapper.toEntity(any())).thenReturn(userStub);
        when(personalInfoMapper.toEntity(any())).thenReturn(personalInfo);
    }

    @Test
    void changeName() {

        userService.changeName(anyString(), personalInfoDTOStub).subscribe();

        verify(userRepository).findById(anyString());
        verify(userMapper).toDTO(any());
    }

    @Test
    void buy() {

        userService.buy(anyString(), anyLong()).subscribe();

        verify(userRepository).findById(anyString());
        verify(userMapper).toDTO(any());
    }

    @Test
    void addMoney() {

        userService.addMoney(anyString(), anyLong()).subscribe();

        verify(userRepository).findById(anyString());
        verify(userMapper).toDTO(any());
    }

    @Test
    void create() {

        userService.create(userDTOStub);
        verify(userRepository).save(any());
    }

    @Test
    void delete() {

        userService.delete(anyString());
        verify(userRepository).deleteById(anyString());
    }

    @Test
    void userInfo() {

        userService.userInfo(anyString());
        verify(userRepository).findById(anyString());
    }

    @Test
    void convert() {

        ExchangeResponseDTO exchangeResponseDTO = new ExchangeResponseDTO();
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 75.0);
        exchangeResponseDTO.setRates(rates);

        when(exchangeService.exchange(anyString(), anyString(), anyDouble())).thenReturn(Mono.just(1.0));

        Double usd = userService.convert(anyString(), Rate.RUB, Rate.USD, 1000.0).block();
        assertEquals(usd, userStub.getUsdAcc());
    }
}