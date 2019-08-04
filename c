#!/bin/bash


dc(){
    # https://github.com/docker/compose/pull/5684

#    docker-compose --compatibility \
#         -f dc/dc.yml \
#         -f dc/stable.yml \
#         -f dc/base.yml \
#         -f dc/dgraph.yml \
#         -f dc/psql.yml \
#         -f dc/datomic.yml \
#         -f dc/ui.yml \
#         "$@"

    docker-compose --compatibility \
        -f dc/base.yml \
        -f dc/dc.yml \
        -f dc/stable.yml \
        -f dc/datomic.yml \
        -f dc/ui.yml \
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

link_vscode(){
    # ln -s .vscode src/ui/wui/.vscode
    ln -s .vscode src/ui/.vscode

}

"$@"