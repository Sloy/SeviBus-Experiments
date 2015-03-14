#!/bin/bash
# This script initiates the Gradle publishing task when pushes to master occur.

echo -e "Starting beta publish...\n"

if [ "$TRAVIS_BRANCH" == "master" ]; then
    echo "Distributing betatesters beta"
    ./gradlew distributeBetatesterBeta
else
    if [ "$TRAVIS_BRANCH" == "develop" || "$TRAVIS_PULL_REQUEST" == "true" ]; then
        echo "Distributing developer beta"
        ./gradlew distributeDeveloperBeta
    fi
fi
RETVAL=$?

if [ $RETVAL -eq 0 ]; then
    echo 'Completed publish!'
else
    echo 'Publish failed.'
    return 1
fi