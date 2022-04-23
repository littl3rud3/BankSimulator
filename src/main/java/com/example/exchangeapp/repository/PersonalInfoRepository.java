//package com.example.exchangeapp.repository;
//
//import com.example.exchangeapp.model.PersonalInfo;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import reactor.core.publisher.Flux;
//
//public interface PersonalInfoRepository extends ReactiveCrudRepository<PersonalInfo, String> {
//
//    Flux<PersonalInfo> findByBankIdsIn(Flux<String> ids);
//}
