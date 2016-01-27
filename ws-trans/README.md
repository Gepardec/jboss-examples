Demo for using Webservice Transactions
======================================

    1. Install JBoss with Port-Offset 0
    2. Apply ws-trans-pkg/src/main/config/03_add_webservice_transactions.conf
	3. Run The Arquillian-Test ./ws-trans-impl/src/test/java/at/gepardec/demo/wstrans/impl/TransWsBeanIT.java

The test deploys a WAR and calls a transactional web service in the same application. 

You can also deploy the WAR ws-trans-impl-0.1.0-SNAPSHOT.war on another JBoss with port offset 100 
and use "http://localhost:8180/transws/TransWs" in
./ws-trans-impl/src/main/java/at/gepardec/demo/wstrans/impl/EJBWrapper4TransWsBean.java
