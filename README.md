Workshop 17

OpenWeather API key (Macos)

```
export OPEN_WEATHER_MAP_API_KEY=<your api key>
```

OpenWeather API key (Windows)

```
set OPEN_WEATHER_MAP_API_KEY=766285a0d5d61b70ac27d4b6d7a7b64b
```

```
Railway settings
These values are stored in application.properties
spring.redis.host=${REDISHOST}
spring.redis.port=${REDISPORT}
spring.redis.username=${REDISUSER}
spring.redis.password=${REDISPASSWORD}
spring.redis.client.type=jedis

As the environment variables are stored on Railway Redis, no need to set environment variables

1.) railway login
2.) railway init
3.) On redis website "Add a service" > "Database" > "Add Redis"
4.) railway up
5.) In Railway (Workshopxx), add the following environment variables
    a.) LOVER_API_KEY=202353c439msh6cec7b7f626e686p167772jsn1311fbbd6c35
    b.) LOVER_API_HOST=love-calculator.p.rapidapi.com

6.) On railway website click on Project(Workshopxx) > Settings > Environment > Generate Domain
```

```
Redis DotCom

These values are stored in application.properties
spring.redis.password=${REDISDOTCOM_PASSWORD}
spring.redis.host=${REDISDOTCOM_HOST}
spring.redis.port=${REDISDOTCOM_PORT}
spring.redis.username=${REDISDOTCOM_USERNAME}

To run on Redis DotCom need to set environment variables before running program

1.) set REDISDOTCOM_PORT=14108
2.) set REDISDOTCOM_PASSWORD=QQYCrTcIYgdqsWFBGwec6bpkrCRqBtEW
3.) set REDISDOTCOM_HOST=redis-14108.c62.us-east-1-4.ec2.cloud.redislabs.com
4.) set REDISDOTCOM_USERNAME=default
5.) set LOVER_API_KEY=202353c439msh6cec7b7f626e686p167772jsn1311fbbd6c35
6.) set LOVER_API_HOST=love-calculator.p.rapidapi.com
7.) mvn spring-boot:run

To see if keys saved successfully in Redis dot com
1.) Command prompt
    a.) wsl
    b.) redis-cli -u redis://default:QQYCrTcIYgdqsWFBGwec6bpkrCRqBtEW@redis-14108.c62.us-east-1-4.ec2.cloud.redislabs.com:14108

2.) keys * (to see if saved successfully) (flushall to clear database)
```

```
Locally
1.) set REDISHOST=localhost
2.) set REDISPORT=6379
3.) On Command Prompt -> wsl
4.) On Command Prompt -> redis-server (to start the local redis server)
5.) On another Command Prompt Window -> redis-cli
6.) keys * (to see if saved successfully) (flushall to clear database)
7.) get {keys} (to see value of saved data)

Here are the commands to retrieve key value(s):
•	if value is of type string -> GET <key>
•	if value is of type hash -> HGET or HMGET or HGETALL <key>
•	if value is of type lists -> lrange <key> <start> <end>
•	if value is of type sets -> smembers <key>
•	if value is of type sorted sets -> ZRANGEBYSCORE <key> <min> <max>
•	if value is of type stream -> xread count <count> streams <key> <ID>. https://redis.io/commands/xread
Use the TYPE command to check the type of value a key is mapping to:
•	type <key>
```
