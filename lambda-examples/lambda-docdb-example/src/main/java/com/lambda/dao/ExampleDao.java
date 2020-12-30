package com.lambda.dao;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class ExampleDao {
	
	private static final Logger log = LoggerFactory.getLogger(ExampleDao.class);
	
	public void updateDocument(final MongoClient mongoClient) {
		log.info("In updateDocument");
		MongoDatabase testDB = mongoClient.getDatabase("sample-database");
		log.info("Got MongoDatabase:"+testDB);
        
        MongoCollection<Document> numbersCollection = testDB.getCollection("sample-collection");
        log.info("Got numbersCollection:"+numbersCollection);
        
        Document doc = new Document("name", "pi").append("value", 3.14159);
        numbersCollection.insertOne(doc);
        log.info("Inserted doc");
        
        MongoCursor<Document> cursor = numbersCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
            	log.info(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
	}

}
