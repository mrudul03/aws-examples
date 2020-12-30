## Lambda with SSL

This example demonstrates Lambda with SSL connectivity to other AWS services suchas DocumentDb or RDS.

By default, TLS is enabled on Amazon DocumentDB clusters. Below are the steps to load the certificate in your own trust store.

* Download the Amazon RDS CA file from 
https://s3.amazonaws.com/rds-downloads/rds-combined-ca-bundle.pem
https://s3.amazonaws.com/rds-downloads/rds-ca-2019-root.pem

* Create a KeyStore with the CA certificate with below command

```
keytool -importcert -trustcacerts -file rds-ca-2019-root.pem -alias rds -keystore rds-ca-certs-2019.jks -storepass changeit 
```
* Create a custom trust manager 
* Load the certificate from the generated KeyStore reading it from S3


If you need to connect two SSL enables servers - one server with trusted certificate from certificate authority and the other self signed certificate. Then you can create custom trust store and load both certificates.

#### References

* Using a custom truststore in java as well as the default one
https://stackoverflow.com/questions/24555890/using-a-custom-truststore-in-java-as-well-as-the-default-one

* Implementing X509TrustManager - passing on part of the verification to existing verifier
https://stackoverflow.com/questions/19005318/implementing-x509trustmanager-passing-on-part-of-the-verification-to-existing/19005844#19005844
