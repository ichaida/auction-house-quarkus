#!/bin/bash

echo "Client connection: "
echo "psql -h localhost -d auction -U auction"

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 \
           --name postgres10 -e POSTGRES_USER=auction \
           -e POSTGRES_PASSWORD=password -e POSTGRES_DB=auction \
           -p 5432:5432 postgres:10.5
