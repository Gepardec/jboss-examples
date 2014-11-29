Example to invoke a EJB on EAP5 / JBoss AS5 from EAP6 
====================================================

Setup for the test
------------------

	JBOSS_5_HOME=$HOME/jboss-eap-5.1/jboss-as

	JBOSS_HOME=$HOME/jboss-eap-6.3.0

	mvn clean install

	mkdir -p $JBOSS_HOME/modules/org/jboss/eap5-client/main
	cp module.xml $JBOSS_HOME/modules/org/jboss/eap5-client/main
	cp $JBOSS_5_HOME/client/*.jar  $JBOSS_HOME/modules/org/jboss/eap5-client/main

	unzip -d  $JBOSS_HOME ./modules/mod-jboss-connectors-eap5/target/01_mod-jboss-connectors-eap5-1.0.0-SNAPSHOT-jboss-module.zip

	$JBOSS_5_HOME/bin/run.sh -Djboss.service.binding.set=ports-03 &

	$JBOSS_HOME/bin/standalone.sh  &

	cp ./remote-ejb/target/remote-ejb-0.1.0-SNAPSHOT.jar  $JBOSS_5_HOME/server/default/deploy

	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:9999  "deploy --force ./remote-ejb-client/target/remote-ejb-client-0.1.0-SNAPSHOT.war"

	curl http://localhost:8080/remote-ejb-client-0.1.0-SNAPSHOT/ClientServlet

You should get something like
Session ID: vAKtsJ0Ff0YGMPlY5YNRA7bB
Service returned SESSION ID: VAKTSJ0FF0YGMPLY5YNRA7BB VALUE:NULL
