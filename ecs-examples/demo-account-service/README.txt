
https://medium.com/@marcus.cavalcanti/deploying-microservices-with-aws-fargate-f0578d8199a3
https://github.com/aws-samples/aws-microservices-deploy-options
https://stackoverflow.com/questions/55513463/aws-application-load-balancer-health-checks-fail
*** https://www.youtube.com/watch?v=915ePPMwVCk
*** https://boby.com.au/technical/2018/08/30/Deploying-Microservices-Aws.html
*** https://github.com/bobypt/microservices-aws-fargate

mvn clean install
java -jar target/demo-account-service-0.0.1-SNAPSHOT.jar
docker image list | grep demo-account
docker run -p 9092:9092 docker-id/demo-account-service:v02
docker ps
aws ecr describe-repositories

docker build -t docker-id/demo-account-service:v02 .
docker push docker-id/demo-account-service:v02
docker tag docker-id/demo-account-service:v02 aws-acc.dkr.ecr.eu-west-1.amazonaws.com/demo-account-service:0.0.2
aws ecr get-login ––region eu-west-1 --no-include-email
docker push aws-acc.dkr.ecr.eu-west-1.amazonaws.com/demo-account-service:0.0.2



