
docker build -t mrudul03/helloworld-service:v01 .

docker run -d -p 5000:8080 -e "SPRING_PROFILES_ACTIVE=dev" mrudul03/helloworld-service:v01
http://localhost:5000/api/helloservice/mrudul12

docker tag mrudul03/helloworld-service2:v01 
===========
https://medium.com/javarevisited/kubernetes-step-by-step-with-spring-boot-docker-gke-35e9481f6d5f

docker build -t gcr.io/msakubernetes/helloworld-service:v01 .

docker push gcr.io/msakubernetes/helloworld-service:v01

