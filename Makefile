#Makefile

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

test:
	./gradlew test

run:
	./gradlew clean
	./gradlew run

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

h:
	./build/install/app/bin/app -h

.PHONY: build