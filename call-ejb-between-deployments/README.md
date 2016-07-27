Example to invoke an EJB between Deployments on the same Server
===============================================================

Setup with https://github.com/Gepardec/JBSS:

eap6 tear-down && mvn clean install && eap6 configure config && curl 'http://localhost:8080/call-ejb-client-0.1.0-SNAPSHOT/ClientServlet'

You should get something like
Session ID: 3zwyDEs1ZIhKPG52ly6G2O1w
Service1 returned with lookup:SESSION ID: 3ZWYDES1ZIHKPG52LY6G2O1W VALUE:NULL
Service1 returned with inject:SESSION ID: 3ZWYDES1ZIHKPG52LY6G2O1W VALUE:NULL

