
http://localhost:9099/api/helloservice/mrudul
http://localhost:9099/actuator/health

docker build -t mrudul03/helloworld-service:v01 .

docker run -p 9099:9099 mrudul03/helloworld-service:v01

docker tag mrudul03/helloworld-service:v01 <AWSACC>.dkr.ecr.us-east-2.amazonaws.com/account-service:0.0.1
$(aws ecr get-login --no-include-email --region us-east-2)

docker tag mrudul03/helloworld-service:v01 AWSACC.dkr.ecr.us-east-2.amazonaws.com/helloworld-service:v01
docker push AWSACC.dkr.ecr.us-east-2.amazonaws.com/helloworld-service:v01

http://dev-a-Publi-DBYFEEKK4W02-628737252.us-east-2.elb.amazonaws.com/api/helloservice/mrudul

http://dev-a-Publi-DBYFEEKK4W02-628737252.us-east-2.elb.amazonaws.com/actuator/health

---------------

docker build -t mrudul03/helloworld-service:v01 .
docker run -d -p 9099:9099 -e "SPRING_PROFILES_ACTIVE=dev" mrudul03/helloworld-service:v01