#!/bin/bash -e

##########################################################
# Un serveur de fichiers HTTP très simple
# Auteur : David Gayerie
#
# Ce serveur distribue les fichiers disponibles dans
# le répertoire Public de l'utilisateur et ne
# supporte que la méthode HTTP GET.
#
# Le serveur peut être lancé avec ncat sur le port 8080
#
#  ncat -k -l 8080 --sh-exec "$(pwd)/http_file_server.sh"
#
#########################################################

function send_http_error ()
{
  local STATUS_CODE=$1
  local MESSAGE="$2"

  echo "HTTP/1.1 $STATUS_CODE $MESSAGE"
  echo "Content-type: text/plain"
  echo "Content-length: ${#MESSAGE}"
  echo
  echo -n "$MESSAGE"
  exit 1
}

# Analyse de la requête entrante
read STATUS_LINE
>&2 echo "$(date -uR) - $STATUS_LINE"

read -a REQUEST <<< "$STATUS_LINE"

RESOURCE=$(readlink -f "$HOME/Public${REQUEST[1]}")

# Vérification des erreurs

if [ ${REQUEST[0]} != "GET" ]
then
  send_http_error 405 "Method ${REQUEST[0]} not allowed"
fi

if [[ "$RESOURCE" != $HOME\/Public\/* ]]
then
  send_http_error 403 "File access forbidden"
fi

if [ ! -f "$RESOURCE" ]
then
  send_http_error 404 "File ${REQUEST[1]} not found!"
fi

# Envoi du fichier de réponse

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
echo "Date: $(date -uR)"
echo
cat $RESOURCE
