
Deployen Sie die Applikation RmiService-1.0.0.jar auf JBoss.
Beachten Sie im Logfile, welche Server-JNDI-Namen veröffentlicht werden. 
Die Clientapplikation benötigt den JNDI-Namen m Client-Context als Parameter

Rufen Sie den Client mit dem entsprechenden JNDI-Namen für das Service auf:
java -cp .:RmiClient-1.0.0.jar:RmiService-1.0.0.jar:$JBOSS_HOME/bin/client/jboss-client.jar examples.rmi.RmiClient 'ejb:/RmiService-1.0.0//HelloServiceBean!examples.rmi.HelloService' 

