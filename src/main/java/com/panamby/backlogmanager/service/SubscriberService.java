package com.panamby.backlogmanager.service;

import com.panamby.backlogmanager.model.Subscriber;

public interface SubscriberService {

	Subscriber getSubscriber(String id, String trackId);
	Subscriber updateSubscriber(Subscriber subscriber);
}
