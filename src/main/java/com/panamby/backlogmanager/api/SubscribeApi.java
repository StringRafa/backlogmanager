package com.panamby.backlogmanager.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.panamby.backlogmanager.model.dto.SubscribeRequest;
import com.panamby.backlogmanager.model.dto.SubscribeResponse;

@Validated
@RequestMapping("/subscribe")
public interface SubscribeApi {

	@PostMapping
	ResponseEntity<SubscribeResponse> subscribe(@RequestBody SubscribeRequest subscribeRequest);
	
    @GetMapping(value = "/{id}")
	ResponseEntity<SubscribeResponse> getSubscribe(@PathVariable String id);
	
    @DeleteMapping
	ResponseEntity<SubscribeResponse> deleteProduct(@RequestParam String id, @RequestParam String product);
}
