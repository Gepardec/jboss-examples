Testen mit Arquillian
=====================

I. Erster Test
--------------
1. Clonen des Projekts: `git clone https://github.com/Gepardec/jboss-examples.git`
2. Branch b_arquillian_demo_1 auschecken: git checkout b_arquillian_demo_1
3. Build und Test:
    cd jboss-examples/arquillian-example/
    mvn clean test

Aufgabe:
    Refactor die Klasse Greeter in ein Interface und eine Implementierung.
    

II. Verschiedene Container mit Profilen
---------------------------------------
1. Branch `b_arquillian_demo_2` auschecken
2. Ausführen des Projekts mit Profil
    mvn -P arquillian-wildfly-embedded clean install
Beachte:
    mvn -P arquillian-weld-ee-embedded clean install
schlägt fehl.


III. Managed JBoss Server
-------------------------
1. Branch `b_arquillian_demo_3` auschecken
2. Starte einen WildFly 8.1.0
3. Ausführen des Projekts mit Profil
    mvn -P arquillian-wildfly-managed clean install

Aufgabe:
    Erstelle in arquillian.xml einen Container, sodass ein Befehl analog zu Folgendem funktioniert:
    mvn -P arquillian-wildfly-managed -Darquillian.launch=wildfly-erhard install


IV. JUnit Rules
---------------
1. Branch `b_arquillian_demo_3` auschecken
2. Starte WildFly und dann den Test:
    mvn -s ~/.m2/settings_empty.xml test -Parquillian-wildfly-managed -Dtest=RuleTest
    
Beachte, die Ausgabe
-- starting: should_create_greeting(com.gepardec.examples.arquillian.RuleTest)
auf Client- und auf Server-Seite

Aufgabe: Ändere die Arquillian-Version von 1.1.4.Final auf 1.1.4.Final (oder 1.1.5.Final)
   und beobachte das Ergebnis des Tests.
   
