JBOSS_BIND_ADDRESS=192.168.105.133
curl -s http://$JBOSS_BIND_ADDRESS:$JBOSS_HTTP_PORT/jmsclient-0.0.1-SNAPSHOT/ClientServlet | grep "Message sent"
