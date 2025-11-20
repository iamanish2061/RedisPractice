Simple use of Redis : implemented in spring boot

Requirement:
Redis installed locally
wsl for starting redis server 

(Please start up the server and follow the code)

RedisConfig.java : declaring bean for RedisTemplate object
Student: entity that holds info of student
StudentController: contains end point that return Student info
StudentService: that contains method (also contains logic to decide where to fetch data from?)
                if in cache(redis) then fetch from cache, else from database
RedisService: method for setting and getting key, value to server
                uses ObjectMapper to map the returned object to the object of desired type