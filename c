#!/bin/bash


dc(){
    # https://github.com/docker/compose/pull/5684
   docker-compose --compatibility \
        -f dc/dc.yml \
        -f dc/base.yml \
        "$@"
}

up(){
    dc up -d --build
}

down(){
    dc down 
}

term(){
   dc exec $1 bash -c "bash;"
}

permis(){
    sudo chmod 777 ./.data/imdb.rdf/*
}

"$@"