#!/bin/bash

mvn clean install
docker build -t messenger .
docker run -p 6969:8080 messenger