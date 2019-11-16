RmiClientJar=../RmiClient/target/RmiClient-1.0.0-SNAPSHOT.jar
RmiServiceJar=../RmiService/target/RmiService-1.0.0-SNAPSHOT.jar
java -cp ..:$RmiClientJar:$RmiServiceJar:$JBOSS_HOME/bin/client/jboss-client.jar examples.rmi.RmiClient 'ejb:/RmiService-1.0.0//HelloServiceBean!examples.rmi.HelloService' | grep "Hello Client" && echo OK
