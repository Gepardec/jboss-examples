package com.gepardec.jbossexamples.camelhornetq;

import org.apache.camel.builder.RouteBuilder;
/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    public void configure() {


        from("activemq:personnel.records")
        	.to("log:INPUT")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .to("file:target/messages/uk")
                .otherwise()
                    .to("file:target/messages/others");
    }

}
