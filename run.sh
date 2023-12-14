#!/bin/bash

mvn clean install -DskipTests assembly:single -q
docker build -t messenger.jar .
docker run messenger.jar