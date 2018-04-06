# ai-spring-cloud
POC for spring cloud Netflix services

# myservice 9000
a sample micro service where provide a hello world: http://localhost:9000/hello

# eureka 9001 
Service Registration Server http://localhost:9001/

# router and filter zuul 9002
gateway zuul - http://localhost:9002/api/ms/hello
api/ms/** -> map to myservice/**
