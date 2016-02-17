#!/bin/bash  
set -e
mvn -Pdocker clean install docker:build
docker-compose up

