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
    a.) LOVER_API_KEY=
    b.) LOVER_API_HOST=

6.) On railway website click on Project(Workshopxx) > Settings > Environment > Generate Domain
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
