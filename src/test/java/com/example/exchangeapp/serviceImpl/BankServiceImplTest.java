package com.example.exchangeapp.serviceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.mapper.BankMapper;
import com.example.exchangeapp.mapper.PersonalInfoMapper;
import com.example.exchangeapp.model.Bank;
import com.example.exchangeapp.model.User;
import com.example.exchangeapp.repository.BankRepository;
import com.example.exchangeapp.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonalInfoMapper personalInfoMapper;

    @Mock
    private BankMapper bankMapper;

    @InjectMocks
    private BankServiceImpl bankService;

    private static List<Bank> banksStub;

    private static Bank bankStub;

    private static BankDTO bankDTOStub;

    private static PersonalInfoDTO personalInfoDTOStub;

    private static String bankIdStub, bankNameStub;

    private static User userStub;

    @BeforeAll
    static void set() {

        final EasyRandom EASY_RANDOM = new EasyRandom();

        bankStub = EASY_RANDOM.nextObject(Bank.class);
        bankDTOStub = EASY_RANDOM.nextObject(BankDTO.class);
        banksStub = EASY_RANDOM.objects(Bank.class, 10).toList();
        bankIdStub = EASY_RANDOM.nextObject(String.class);
        bankNameStub = EASY_RANDOM.nextObject(String.class);
        userStub = EASY_RANDOM.nextObject(User.class);
        personalInfoDTOStub = EASY_RANDOM.nextObject(PersonalInfoDTO.class);
    }

    @Test
    void allClients() {

        when(userRepository.findAllByPersonalInfoBankIdsIn(List.of(List.of(
            bankNameStub)))).thenReturn(Flux.just(userStub));
        when(personalInfoMapper.toDTO(any())).thenReturn(personalInfoDTOStub);

        bankService.allClients(bankNameStub).subscribe();

        verify(userRepository).findAllByPersonalInfoBankIdsIn(List.of(List.of(bankNameStub)));
    }

    @Test
    void create() {

        when(bankRepository.save(bankStub)).thenReturn(Mono.just(bankStub));
        when(bankMapper.toEntity(bankDTOStub)).thenReturn(bankStub);
        when(bankMapper.toDTO(bankStub)).thenReturn(bankDTOStub);

        bankService.create(bankDTOStub).subscribe();

        verify(bankRepository).save(bankStub);
    }

    @Test
    void update() {

        when(bankRepository.findById(bankIdStub)).thenReturn(Mono.just(bankStub));
        when(bankRepository.save(bankStub)).thenReturn(Mono.just(bankStub));

        bankService.update(bankIdStub, bankNameStub).subscribe();

        verify(bankRepository, times(2));
    }

    @Test
    void delete() {

        when(bankRepository.deleteById(bankIdStub)).thenReturn(Mono.never());

        bankService.delete(bankIdStub).subscribe();

        verify(bankRepository).deleteById(bankIdStub);
    }

    @Test
    void getAll() {

        when(bankRepository.findAll()).thenReturn(Flux.fromIterable(banksStub));
        when(bankMapper.toDTO(any())).thenReturn(bankDTOStub);

        bankService.getAll().subscribe();

        verify(bankRepository).findAll();
    }
}