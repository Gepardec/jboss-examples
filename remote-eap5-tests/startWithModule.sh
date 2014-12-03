#!/bin/sh

JBOSS_HOME=/home/esiegl/jboss-eap-6.3.0
java -jar $JBOSS_HOME/jboss-modules.jar -mp $JBOSS_HOME/modules at.gepardec.jboss_connectors.eap5
