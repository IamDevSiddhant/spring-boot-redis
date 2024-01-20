# spring-boot-redis

### This Project is demonstration of How to use redis as in memory database and Cache

## Redis as In-Memory Database

* Dependency - please refer pom.xml
* Downloaded Redis Server From Below Link
* <href>https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100</href>
* working on localhost:9091 port number
* can check all keys stored on Redis Server, Use <b>KEYS *</b> to get all <b>HASH</b> keys


## Redis as Cache
* Dependency - please refer pom.xml
* Downloaded Redis Server From Below Link
* <href>https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100</href>
* Please first Refer <b>REDIS CONFIGURATION</b> from src/main/config/RedisConfig file.
* Basically, Cache mechanism is Implemented to avoid frequent Calls to your, SQL No-SQL DB
* So you can Annotate respective methods with caching annotations & instruct Spring boot to store data in Cache Memory
* for verification i have added logs



