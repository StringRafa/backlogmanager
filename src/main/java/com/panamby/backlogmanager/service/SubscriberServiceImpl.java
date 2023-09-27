package com.panamby.backlogmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.panamby.backlogmanager.exception.SubscriberException;
import com.panamby.backlogmanager.model.Subscriber;

import lombok.extern.slf4j.Slf4j;

@EnableCaching
@Slf4j
@Service
public class SubscriberServiceImpl implements SubscriberService {

	@Autowired
	private SubscriberCacheService subscriberCacheService;
	
	@Override
	public Subscriber getSubscriber(String id, String transactionId) {

		log.debug(String.format("Get Subscriber process started - MSISDN [%s] - TRANSACTION_ID [%s]", id, transactionId));

		Subscriber subscriber;

		try {

			//Mongodb Subscriber Lookup and Persistence in the Cache
			//If the subscriber is not in the database it will return a new subscriber with backlog 0
            subscriber = subscriberCacheService.findSubscriberById(id);
            
        }catch (Exception e){
        	
            log.error(String.format("Unable to perform a check. MSISDN [%s] - TRANSACTION_ID [%s]", id, transactionId), e);
            throw new SubscriberException("Failed to fetch subscriber.", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), transactionId, id);
        }

		log.debug(String.format("Get Subscriber process finished - MSISDN [%s] - TRANSACTION_ID [%s]", id, transactionId));

		return subscriber;
	}

	@Override
	public Subscriber updateSubscriber(Subscriber subscriber) {

		log.debug(String.format("updateSubscriber process started - SUBSCRIBER [%s]", subscriber));
		
		//Perform the update on the subscriber's backlog and if the subscriber doesn't exist in mongo db, it creates and updates.
		subscriber = subscriberCacheService.updateSubscriber(subscriber);
		
		log.debug(String.format("updateSubscriber process finished - SUBSCRIBER [%s]", subscriber));
		
		return subscriber;
	}
}
