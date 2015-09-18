#!/bin/bash -e

read STATUS_LINE

>&2 echo "Receiving request $STATUS_LINE"

read -a REQUEST <<< "$STATUS_LINE"

RESPONSE_MESSAGE="""
We have just received a request on the resource ${REQUEST[1]} for the methode ${REQUEST[0]}.
"""

cat << EOF
HTTP/1.1 200 OK
Content-type: text/plain
Content-length: $((${#RESPONSE_MESSAGE}+1))

${RESPONSE_MESSAGE}
EOF
