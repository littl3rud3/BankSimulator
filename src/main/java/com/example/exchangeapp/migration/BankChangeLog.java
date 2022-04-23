package com.example.exchangeapp.migration;

import static com.example.exchangeapp.config.MongockConfig.BANK_COLLECTION_NAME;
import static com.example.exchangeapp.config.MongockConfig.insertCount;

import com.example.exchangeapp.model.Bank;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import io.mongock.driver.mongodb.reactive.util.MongoSubscriberSync;
import io.mongock.driver.mongodb.reactive.util.SubscriberSync;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.jeasy.random.EasyRandom;

/**
 * Ченжлог для создания и заполнения коллекции банков.
 */
@ChangeUnit(id = "bankCollection", order = "1", author = "littl3rud3")
@RequiredArgsConstructor
public class BankChangeLog {

    private final EasyRandom easyRandom = new EasyRandom();

    /**
     * Создать коллекцию.
     *
     * @param mongoDatabase
     */
    @BeforeExecution
    public void createCollection(MongoDatabase mongoDatabase) {
        SubscriberSync<Void> subscriber = new MongoSubscriberSync<>();
        mongoDatabase.createCollection(BANK_COLLECTION_NAME).subscribe(subscriber);
        subscriber.await();
    }

    /**
     * Удалить коллекцию.
     *
     * @param mongoDatabase the mongo database
     */
    @RollbackBeforeExecution
    public void dropCollection(MongoDatabase mongoDatabase) {
        SubscriberSync<Void> subscriber = new MongoSubscriberSync<>();
        mongoDatabase.getCollection(BANK_COLLECTION_NAME).drop().subscribe(subscriber);
        subscriber.await();
    }

    /**
     * Заполнить коллекцию данными.
     *
     * @param clientSession сессия
     * @param mongoDatabase
     */
    @Execution
    public void fillCollection(ClientSession clientSession, MongoDatabase mongoDatabase) {

        SubscriberSync<InsertManyResult> subscriber = new MongoSubscriberSync<>();
        mongoDatabase.getCollection(BANK_COLLECTION_NAME, Bank.class)
                     .insertMany(clientSession, easyRandom.objects(Bank.class, insertCount).toList())
                     .subscribe(subscriber);
    }

    /**
     * Очистить коллекцию.
     *
     * @param clientSession сессия
     * @param mongoDatabase
     */
    @RollbackExecution
    public void truncateCollection(ClientSession clientSession, MongoDatabase mongoDatabase) {
        SubscriberSync<DeleteResult> subscriber = new MongoSubscriberSync<>();

        mongoDatabase
            .getCollection(BANK_COLLECTION_NAME, Bank.class)
            .deleteMany(clientSession, new Document())
            .subscribe(subscriber);
    }
}
