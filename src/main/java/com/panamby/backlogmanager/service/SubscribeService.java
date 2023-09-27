package com.panamby.backlogmanager.service;

import com.panamby.backlogmanager.model.dto.SubscribeRequest;
import com.panamby.backlogmanager.model.dto.SubscribeResponse;

public interface SubscribeService {

	SubscribeResponse subscribe(SubscribeRequest subscribeRequest);
	SubscribeResponse getSubscribe(String id);
	SubscribeResponse removeProduct(String id, String product);
}