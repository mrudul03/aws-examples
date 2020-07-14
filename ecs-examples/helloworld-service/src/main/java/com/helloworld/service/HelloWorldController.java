package com.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api/helloservice")
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping(value="/{name}", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> hello(@PathVariable String name){
		
		LOGGER.info("called hello");
		return ResponseEntity.ok("Helllo with spring profiles - "+name);
	}
	
}
