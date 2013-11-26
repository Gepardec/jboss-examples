echo "Teste Buecherliste mit Rest Webservice"
curl -s http://localhost:8080/jaxrs-ws-1.0.0-SNAPSHOT/rest/books | grep Judgment || exit -1
echo "Teste Soap Webservice"
curl -s http://localhost:8080/jaxrs-ws-1.0.0-SNAPSHOT/rest/person | grep Hans || exit -2
echo "Tests OK!"
