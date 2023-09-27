package com.panamby.backlogmanager.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.panamby.backlogmanager.model.Subscriber;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {

    Optional<Subscriber> findUserById(String id);
}
