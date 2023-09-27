package com.panamby.backlogmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panamby.backlogmanager.model.Subscriber;
import com.panamby.backlogmanager.model.dto.SubscribeDataResponse;
import com.panamby.backlogmanager.model.dto.SubscribeRequest;
import com.panamby.backlogmanager.model.dto.SubscribeResponse;
import com.panamby.backlogmanager.utils.UUIDUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	private SubscriberService subscriberService;

	@Override
	public SubscribeResponse subscribe(SubscribeRequest subscribeRequest) {

		String transactionId = UUIDUtils.generateUUID();

		log.debug(String.format("Subscribe process started. ID [%s] - TRANSACTION_ID [%s]", subscribeRequest.getId(), transactionId));

		//Load subscriber from cache or DB.
		Subscriber subscriber = subscriberService.getSubscriber(subscribeRequest.getId(), transactionId);
		
		//Increase subscribe count products and update cache DB.
		subscriber.addToProducts(subscribeRequest.getProduct());
		subscriberService.updateSubscriber(subscriber);

        SubscribeResponse subscribeResponse = new SubscribeResponse(new SubscribeDataResponse(transactionId, subscribeRequest.getId(), subscriber.getProducts()));

		log.debug(String.format("Subscribe process finished. ID [%s] - TRANSACTION_ID [%s]", subscribeRequest.getId(), transactionId));
		
		return subscribeResponse;
	}	

    @Override
    public SubscribeResponse getSubscribe(String id) {

        String transactionId = UUIDUtils.generateUUID();

        log.debug(String.format("Get Subscribe process started. ID [%s] - TRANSACTION_ID [%s]", id, transactionId));

        //Mongodb Subscriber Lookup and Persistence in the Cache
        //If the subscriber is not in the database it will return a new subscriber with backlog 0
        Subscriber subscriber = subscriberService.getSubscriber(id, transactionId);

        SubscribeResponse subscribeResponse = new SubscribeResponse(new SubscribeDataResponse(transactionId, id, subscriber.getProducts()));

        log.info(String.format("Get Subscribe process finished. ID [%s] - TRANSACTION_ID [%s]", id, transactionId));
        
        return subscribeResponse;
    }

	@Override
	public SubscribeResponse removeProduct(String id, String product) {

        String transactionId = UUIDUtils.generateUUID();

		log.trace(String.format("Unsubscribe Product process started. TRANSACTION_ID [%s] - ID [%s] - PRODUCT [%s]",
				transactionId, id, product));

		//Load subscriber from cache or DB.
		Subscriber subscriber = subscriberService.getSubscriber(id, transactionId);

		subscriber.removeFromProducts(product, transactionId);
		subscriberService.updateSubscriber(subscriber);

        SubscribeResponse subscribeResponse = new SubscribeResponse(new SubscribeDataResponse(transactionId, id, subscriber.getProducts()));

		log.trace(String.format("Unsubscribe Product process finished. TRANSACTION_ID [%s] - ID [%s] - PRODUCT [%s]",
				transactionId, id, product));
		
		return subscribeResponse;
	}
}