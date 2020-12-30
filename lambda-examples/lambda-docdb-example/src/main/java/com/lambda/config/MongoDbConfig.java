package com.lambda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbConfig {
	
	private static final Logger log = LoggerFactory.getLogger(MongoDbConfig.class);
	private String connectionString = "mongodb://docdbadmin:changeit@mydocdb.cluster-c5uhlp1vbumt.ap-south-1.docdb.amazonaws.com:27017/?ssl=true&ssl_ca_certs=rds-ca-2019-root.pem&replicaSet=rs0&readPreference=secondaryPreferred&retryWrites=false";
	
	private MongoClient mongoClient;
	
	private MongoDbConfig() {}
	
	public static class Builder {
		public Builder() {
		}
		
		public MongoDbConfig initialize() {
			MongoDbConfig mongoDbConfig = new MongoDbConfig();
			MongoClientURI clientUri = new MongoClientURI(mongoDbConfig.connectionString);
			mongoDbConfig.mongoClient = new MongoClient(clientUri);
			log.info("------- Initialized MongoClient");
			return mongoDbConfig;
		}
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
