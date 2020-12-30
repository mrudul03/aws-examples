## Lambda with SSL

This example demonstrates Lambda with SSL connectivity to other AWS services suchas DocumentDb or RDS.

By default, TLS is enabled on Amazon DocumentDB clusters. Below are the steps to load the certificate in your own trust store.

* Download the Amazon RDS CA file from 
https://s3.amazonaws.com/rds-downloads/rds-combined-ca-bundle.pem
https://s3.amazonaws.com/rds-downloads/rds-ca-2019-root.pem

* Create a KeyStore with the CA certiticate with below command

```
keytool -importcert -trustcacerts -file rds-ca-2019-root.pem -alias rds -keystore rds-ca-certs-2019.jks -storepass changeit 
```
* Create a custom trust manager 
* Load the certificate from the generated KeyStore reading it from S3

```
private static SSLSocketFactory createSSLSocketFactory(File crtFile) throws GeneralSecurityException, IOException {
    SSLContext sslContext = SSLContext.getInstance("SSL");

    // Create a new trust store, use getDefaultType for .jks files or "pkcs12" for .p12 files
    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
    // You can supply a FileInputStream to a .jks or .p12 file and the keystore password as an alternative to loading the crt file
    trustStore.load(null, null);

    // Read the certificate from disk
    X509Certificate result;
    try (InputStream input = new FileInputStream(crtFile)) {
        result = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(input);
    }
    // Add it to the trust store
    trustStore.setCertificateEntry(crtFile.getName(), result);

    // Convert the trust store to trust managers
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(trustStore);
    TrustManager[] trustManagers = tmf.getTrustManagers();
	
	SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(null, trustManagers, null);
    SSLContext.setDefault(sslContext);
    
    return sslContext.getSocketFactory();
}

```

If you need to connect two SSL enables servers - one server with trusted certificate from certificate authority and the other self signed certificate. Then you can create custom trust store and load both certificates.

#### References

* Using a custom truststore in java as well as the default one
https://stackoverflow.com/questions/24555890/using-a-custom-truststore-in-java-as-well-as-the-default-one

* Implementing X509TrustManager - passing on part of the verification to existing verifier
https://stackoverflow.com/questions/19005318/implementing-x509trustmanager-passing-on-part-of-the-verification-to-existing/19005844#19005844
