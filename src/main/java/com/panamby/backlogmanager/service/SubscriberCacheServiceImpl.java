package com.panamby.backlogmanager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.panamby.backlogmanager.consts.RedisConstants;
import com.panamby.backlogmanager.model.Subscriber;
import com.panamby.backlogmanager.repository.SubscriberRepository;

import lombok.extern.slf4j.Slf4j;

@EnableCaching
@Slf4j
@Service
public class SubscriberCacheServiceImpl implements SubscriberCacheService{

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Persist in cache with key "id"
    @Cacheable(value = RedisConstants.BACKLOG_MANAGER_SUBSCRIBE_CACHE, key = "#id")
    public Subscriber findSubscriberById(String id){
    	
        log.debug(String.format("Starting Find Subscriber by Id: [%s]", id));

        //Search mongodb and if the subscriber is not in the database it will return a new subscriber with backlog 0
        Subscriber subscriber = subscriberRepository.findUserById(id).orElse(new Subscriber(id, new ArrayList<>(), null));
        
        log.debug(String.format("Find Subscriber by Id Finished: [%s]", id));
        
        return subscriber;
    }

    @CachePut(value = RedisConstants.BACKLOG_MANAGER_SUBSCRIBE_CACHE, key = "#subscriber.id")
    public Subscriber updateSubscriber(Subscriber subscriber){
    	
    	log.debug(String.format("Starting Update Subscriber: [%s]", subscriber));

        //Update the subscriber in mongodb if it exists, or create a new one if it doesn't
    	Subscriber subscriberUpdated = mongoTemplate.save(subscriber);
    	
    	log.debug(String.format("Update Subscriber Finished: [%s]", subscriber));
    	
        return subscriberUpdated;
    }

}