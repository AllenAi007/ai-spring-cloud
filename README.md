# ai-spring-cloud
POC for spring cloud Netflix services

# myservice 9000
a sample micro service where provide a hello world: http://localhost:9000/hello

# eureka 9001 
Service Registration Server http://localhost:9001/

# router and filter zuul 9002
gateway zuul - http://localhost:9002/api/ms/hello
api/ms/** -> map to myservice/**

-- without auth
curl localhost:9002/api/ms/hello
-- with aihua:abc123
curl --header "Authentication: BASIC YWlodWE6YWJjMTIz" localhost:9002/api/ms/hello
-- with admin:admin
curl --header "Authentication: BASIC YWRtaW46YWRtaW4=" localhost:9002/api/ms/hello

# configuration server 9003
