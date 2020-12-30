package com.lambda.config;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambda.handler.S3Helper;

//@Slf4j
public class SslConfig {
	
	private static final Logger log = LoggerFactory.getLogger(SslConfig.class);
	private String trustStorePassword = "changeit";
	private String s3Bucket = "example-certificates";
	private String s3Key = "rds-ca-certs-2019.jks";
	
	private SslConfig() {}
	
	public static class Builder {
		public Builder() {
		}
		
		public SslConfig loadTls() {
			SslConfig sslConfig = null;
					
			try {
				sslConfig = new SslConfig();
				sslConfig.loadTlsCertificate();
			}catch(Exception e) {
				log.error("Error while loadTlsCertificate:", e);
			}
			
			return sslConfig;
		}
		
		public SslConfig loadAllCerts() {
			SslConfig sslConfig = null;
					
			try {
				sslConfig = new SslConfig();
				sslConfig.loadAllCertificate();
			}catch(Exception e) {
				
			}
			
			return sslConfig;
		}
	}
	
	private void loadAllCertificate() throws Exception {
		log.info("Loading all certificates");
		
		final X509TrustManager defaultTm = this.getDefaultX509TrustManager();
		final X509TrustManager myTm = this.createTrustManagerFromCertificate(s3Bucket, s3Key);
	    
	    // Wrap it in your own class.
	    final X509TrustManager customTm = this.createCustomX509TrustManager(
	    		defaultTm, myTm);
	    
	    SSLContext sslContext = SSLContext.getInstance("TLS");
	    sslContext.init(null, new TrustManager[] {customTm}, null);
	    log.info("Done Loading all certificates");
	}
	
	private X509TrustManager createCustomX509TrustManager(final X509TrustManager defaultTm,
			final X509TrustManager myTm) {
		
		log.info("In createCustomX509TrustManager");
		X509TrustManager customTm = new X509TrustManager() {
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            // If you're planning to use client-cert auth,
	            // merge results from "defaultTm" and "myTm".
	            return defaultTm.getAcceptedIssuers();
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] chain,
	                String authType) throws CertificateException {
	            try {
	            	myTm.checkServerTrusted(chain, authType);
	            } catch (CertificateException e) {
	                // This will throw another CertificateException if this fails too.
	            	defaultTm.checkServerTrusted(chain, authType);
	            }
	        }

	        @Override
	        public void checkClientTrusted(X509Certificate[] chain,
	                String authType) throws CertificateException {
	            // If you're planning to use client-cert auth,
	            // do the same as checking the server.
	        	defaultTm.checkClientTrusted(chain, authType);
	        }
	    };
	    log.info("Done createCustomX509TrustManager");
	    return customTm;
	}
	
	private X509TrustManager getDefaultX509TrustManager() throws Exception {
		log.info("In getDefaultX509TrustManager");
		TrustManagerFactory tmf = TrustManagerFactory
			    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// Using null here initialises the TMF with the default trust store.
		tmf.init((KeyStore) null);
		
		// Get hold of the default trust manager
		X509TrustManager defaultTm = null;
		for (TrustManager tm : tmf.getTrustManagers()) {
		    if (tm instanceof X509TrustManager) {
		        defaultTm = (X509TrustManager) tm;
		        break;
		    }
		}
		log.info("Done getDefaultX509TrustManager");
		return defaultTm;
	}
	
	private X509TrustManager createTrustManagerFromCertificate( 
			String bucketName, String s3Key) throws Exception{
		
		log.info("In createTrustManagerFromCertificate");
		S3Helper s3Helper = new S3Helper();
	    InputStream myKsInputstream = s3Helper.getS3File(bucketName, s3Key);
	    
	    // Do the same with your trust store this time
	    // Adapt how you load the keystore to your needs
	    KeyStore myTrustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    myTrustStore.load(myKsInputstream, trustStorePassword.toCharArray());
	    myKsInputstream.close();
	    
	    TrustManagerFactory tmf = TrustManagerFactory
			    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
	    tmf.init(myTrustStore);
	    
	    // Get hold of the default trust manager
	    X509TrustManager myTm = null;
	    for (TrustManager tm : tmf.getTrustManagers()) {
	        if (tm instanceof X509TrustManager) {
	            myTm = (X509TrustManager) tm;
	            break;
	        }
	    }
	    log.info("Done createTrustManagerFromCertificate");
	    return myTm;
	}
	
	private void loadTlsCertificate() throws Exception {
		log.info("Loading TlsCertificate-----");
		
		S3Helper s3Helper = new S3Helper();
	    InputStream myKsInputstream = s3Helper.getS3File(s3Bucket, s3Key);
		
		// Create a new trust store, use getDefaultType for .jks files or "pkcs12" for .p12 files
	    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
	    // You can supply a FileInputStream to a .jks or .p12 file and the keystore password as an alternative to loading the crt file
	    ks.load(null, null);
	    
	    X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X509")
	    		.generateCertificate(myKsInputstream);
	    myKsInputstream.close();
	    
	    ks.setCertificateEntry(s3Key, certificate);
	    
	    // Convert the trust store to trust managers
	    TrustManagerFactory tmf = TrustManagerFactory.getInstance(
	    		TrustManagerFactory.getDefaultAlgorithm());
	    tmf.init(ks);
	    TrustManager[] trustManagers = tmf.getTrustManagers();
	    
	    SSLContext sslContext = SSLContext.getInstance("TLS");
	    sslContext.init(null, trustManagers, null);
	    
	    //SSLSocketFactory socketFactory = sslContext.getSocketFactory();
	    //HttpsURLConnection.setSSLSocketFactory(socketFactory);
	    
	    log.info("sslContext call init--");
	    
	}

}
