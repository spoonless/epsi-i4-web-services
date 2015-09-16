#!/bin/bash -e

######################################################
# Un serveur de fichiers HTTP très simple
#
# Ce serveur distribue les fichiers disponibles dans
# le répertoire Public de l'utilisateur et ne
# supporte que la méthode HTTP GET.
######################################################

read STATUS_LINE
>&2 echo "Receiving request $STATUS_LINE"

read -a REQUEST <<< "$STATUS_LINE"

RESOURCE="$HOME/Public${REQUEST[1]}"

if [ ${REQUEST[0]} != "GET" ]
then
    MESSAGE="Method ${REQUEST[0]} not allowed"
    echo "HTTP/1.1 405 $MESSAGE"
    echo "Content-type: text/plain"
    echo "Content-length: ${#MESSAGE}"
    echo
    echo -n $MESSAGE
    exit 1
fi

if [ ! -f "$RESOURCE" ]
then
    MESSAGE="File ${REQUEST[1]} not found!"
    echo "HTTP/1.1 404 NOT FOUND"
    echo "Content-type: text/plain"
    echo "Content-length: ${#MESSAGE}"
    echo
    echo -n $MESSAGE
    exit 1
fi

# FIX : la commande file ne retourne pas le bon MIME-TYPE pour les fichiers css
if [[ "$RESOURCE" == *.css ]]
then
    MIME_TYPE="text/css"
else
    MIME_TYPE="$(file -i $RESOURCE | cut -d : -f 2)"
fi

echo "HTTP/1.1 200 OK"
echo "Content-type:$MIME_TYPE"
echo "Content-length: $(stat -c%s $RESOURCE)"
echo
cat $RESOURCE
