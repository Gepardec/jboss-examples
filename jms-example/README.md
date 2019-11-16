JMS Examples
==============

Code to test JMS and MDBs, especially to test cluster configurations
jms-client is a simple web-application that inserts a message into a queue.
jms-listener is a MDB that reads from the queue.

Configuration
=============

The configuration tries to set-up a HA-cluster with two nodes.
It uses a symetric topology (live and backup on both nodes) and a shared file-system.

*Note* that you have to set system properties jboss.node.name and other.node.name for the configuration to work.
With the OBJ-JBoss-Tools, I set:

Node 1: .cl1rc
--------------
```
export JBOSS_HOME=$HOME/jboss/jboss-eap-cl1
export JBossPackage=$HOME/Downloads/jboss-eap-6.1.1.zip
PORT_OFFSET=100
OTHER=cl2
JBOSS_OPTS="-Djboss.bind.address=192.168.105.133 -Dother.node.name=$OTHER"
```

Node 2: .cl2rc
--------------
```
export JBOSS_HOME=$HOME/jboss/jboss-eap-cl2
export JBossPackage=$HOME/Downloads/jboss-eap-6.1.1.zip
PORT_OFFSET=200
OTHER=cl1
JBOSS_OPTS="-Djboss.bind.address=192.168.105.133 -Dother.node.name=$OTHER"
```
