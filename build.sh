#!/bin/bash  
set -e
mvn -Pdocker install docker:build
docker-compose up

