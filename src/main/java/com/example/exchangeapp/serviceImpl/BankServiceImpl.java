package com.example.exchangeapp.serviceImpl;

import java.util.List;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.mapper.BankMapper;
import com.example.exchangeapp.mapper.PersonalInfoMapper;
import com.example.exchangeapp.model.Bank;
import com.example.exchangeapp.model.User;
import com.example.exchangeapp.repository.BankRepository;
import com.example.exchangeapp.repository.UserRepository;
import com.example.exchangeapp.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final PersonalInfoMapper personalInfoMapper;

    private final UserRepository userRepository;

    private final BankMapper bankMapper;

    @Override
    public Flux<PersonalInfoDTO> allClients(String bankName) {

        return userRepository.findAllByPersonalInfoBankIdsIn(List.of(List.of(bankName)))
                             .map(User::getPersonalInfo)
                             .map(personalInfoMapper::toDTO);
    }

    @Override
    public Mono<BankDTO> create(BankDTO newBank) {

        Bank bank = bankMapper.toEntity(newBank);

        return bankRepository.save(bank)
                             .map(bankMapper::toDTO);
    }

    @Override
    public Mono<BankDTO> update(String objectId, String newName) {

        return bankRepository.findById(objectId)
                             .doOnSuccess(bank -> bank.setName(newName))
                             .flatMap(bankRepository::save)
                             .map(bankMapper::toDTO);
    }

    @Override
    public Mono<Void> delete(String bankId) {

        return bankRepository.deleteById(bankId);
    }

    @Override
    public Flux<BankDTO> getAll() {

        return bankRepository.findAll()
                             .map(bankMapper::toDTO);
    }
}
