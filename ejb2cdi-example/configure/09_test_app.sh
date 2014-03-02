echo "Teste Buecherliste mit Rest Webservice"
curl -s http://localhost:8080/ws-client-1.0.0-SNAPSHOT/ejbws | grep Rufe || exit -2
echo "Tests OK!"
