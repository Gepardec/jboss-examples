package com.gepardec.examples.arquillian;

import java.io.PrintStream;

public interface Greeter {

	public abstract void greet(PrintStream to, String name);

	public abstract String createGreeting(String name);

}