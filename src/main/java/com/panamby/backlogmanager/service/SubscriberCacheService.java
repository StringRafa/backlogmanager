package com.panamby.backlogmanager.service;

import com.panamby.backlogmanager.model.Subscriber;

public interface SubscriberCacheService {

    Subscriber findSubscriberById(String id);
    Subscriber updateSubscriber(Subscriber subscriber);
}
