package com.lambda.handler;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.lambda.model.DataRequest;

public class S3Helper {
	
	private static final Logger log = LoggerFactory.getLogger(S3Helper.class);
	
	public InputStream getS3File(String bucketName, String s3Key) {
		GetObjectRequest request = new GetObjectRequest(bucketName, s3Key);
		AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion("ap-south-1").build();
		
		S3Object s3Object = client.getObject(request);
		log.info("Got s3Object: " + s3Object);
		
		if(null != s3Object) {
			return s3Object.getObjectContent();
		}
		else {
			return null;
		}
	}
	
	public List<Bucket> listBuckets(DataRequest request) {
		
		AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion("ap-south-1").build();
		List<Bucket> buckets = client.listBuckets();
		log.info("Got Bucket List of size: " + buckets.size());
		
		for(Bucket bucket: buckets) {
			log.info("Bucket Name: " + bucket.getName());
		}
		return buckets;
	}

}
