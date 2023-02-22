# Test Demo Project 

## Prerequisite
1. JDK 11
2. Stand-alone Git
3. Stand-alone Docker

## Pull  project at once
1. Create the `test` root folder in your working space. You can choose another name if you want.
2. Execute the following git command inside that folder
```shell
git clone  https://github.com/johnskyi/demo
```
3. Run in the root directory for start db server.
```shell
docker-compose up -d
```
4. Run in root directory for build and test.
```shell
./mvnw clean package
```
5. Run in root directory for start.
```shell
./mvnw spring-boot:run
```

