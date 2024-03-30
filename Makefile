#!make

compile:
	mvn clean package -DskipTests

run:
	mvn spring-boot:run

up:
	docker-compose up -d