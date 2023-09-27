package com.panamby.backlogmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.panamby.backlogmanager.api.SubscribeApi;
import com.panamby.backlogmanager.model.dto.SubscribeRequest;
import com.panamby.backlogmanager.model.dto.SubscribeResponse;
import com.panamby.backlogmanager.service.SubscribeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SubscribeApiController implements SubscribeApi{

	@Autowired
	private SubscribeService subscribeService;

	@Override
	public ResponseEntity<SubscribeResponse> subscribe(@RequestBody SubscribeRequest subscribeRequest){

		log.info(String.format("Starting backlog-manager subscribe. ID [%s] - PRODUCT [%s]", subscribeRequest.getId(), subscribeRequest.getProduct()));
		
		//Carry out package signing and contracting.
		SubscribeResponse subscribeResponse = subscribeService.subscribe(subscribeRequest);
		
		log.info(String.format("Subscribe process finished. ID [%s] - PRODUCT [%s]", subscribeRequest.getId(), subscribeRequest.getProduct()));

		return new ResponseEntity<SubscribeResponse>(subscribeResponse, HttpStatus.CREATED);
	}
	
	@Override
    public ResponseEntity<SubscribeResponse> getSubscribe(@PathVariable String id){

		log.info(String.format("Starting backlog-manager get Subscribe. ID [%s]", id));

		//Perform get Subscribe
		SubscribeResponse subscribeResponse = subscribeService.getSubscribe(id);

        log.info(String.format("Get Subscribe process finished. SUBSCRIBE_RESPONSE [%s]", subscribeResponse));

        return new ResponseEntity<SubscribeResponse>(subscribeResponse, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<SubscribeResponse> deleteProduct(@PathVariable String id, @PathVariable String product) {

		log.info(String.format("Starting backlog-manager delete Product. ID [%s] - PRODUCT [%s]", id, product));

		//Perform get Subscribe
		SubscribeResponse subscribeResponse = subscribeService.removeProduct(id, product);

        log.info(String.format("Delete Product process finished. ID [%s] - PRODUCT [%s]", id, product));

        return new ResponseEntity<SubscribeResponse>(subscribeResponse, HttpStatus.OK);
	}

}
