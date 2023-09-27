package com.panamby.backlogmanager.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.panamby.backlogmanager.consts.MongoDBConstants;
import com.panamby.backlogmanager.exception.ProductNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = MongoDBConstants.BACKLOG_MANAGER_SUBSCRIBER)
@Slf4j
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    private String id;
	private List<Product> products;	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateOfLastPurchase;
    
    public void addToProducts(String product) {
    	
    	log.info("Starting add to product.");

    	// add product to subscriber
    	Product product2 = new Product(product);
    	
    	if(this.products == null) {
    		
    		this.products = new ArrayList<Product>();
    	}
    	
    	this.dateOfLastPurchase = LocalDateTime.now();
    	this.products.add(product2);
    	
    	log.info("Add to product finished.");
    }

    public void removeFromProducts(String product, String transactionId){

    	log.info("Starting remove from products.");
    	
    	if(!products.contains(new Product(product))) {
    		
    		throw new ProductNotFoundException("Product not found.", transactionId);
    	}
    	
    	for (Product subscriberProduct : products) {
        	
        	if(subscriberProduct.getProductName().equals(product)) {

            	log.info("Product found.");  
        		
        		this.products.remove(subscriberProduct);
        		break;
        	}			
		}	

    	log.info("Remove from products finished.");    	
    }
}