URL=http://localhost:$JBOSS_HTTP_PORT/SecureApp/

curl --digest -s -u erhard:erhard@123 $URL | grep Start || exit 2
curl --digest -s $URL | grep Start 
if [ 0 == $? ]; then
	echo "Aufruf ohne Passwort sollte fehlschlagen"
	exit 3
fi
