#!/bin/bash

######################################################
# Lance un serveur sur le port 8080.
#
# Pour chaque requête entrante, le serveur execute
# le script passé en paramètre.
######################################################

if [ -z "$1" ]
then
    echo "Script shell attentu comme premier paramètre"
    exit 1
fi

PORT=8080

echo "Starting ncat server on $PORT"
ncat -k -l $PORT --sh-exec "$(dirname $0)/$*"
