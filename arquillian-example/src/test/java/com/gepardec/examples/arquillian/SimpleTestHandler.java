package com.gepardec.examples.arquillian;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SimpleTestHandler extends TestWatcher {

	@Override
	protected void succeeded(Description description) {
		System.out.println("-- succeeded: " + description);
		super.succeeded(description);
	}

	@Override
	protected void failed(Throwable e, Description description) {
		System.out.println("-- failed: " + description);
		super.failed(e, description);
	}

	@Override
	protected void starting(Description description) {
		System.out.println("-- starting: " + description);
		super.starting(description);
	}

	@Override
	public Statement apply(Statement base, Description description) {
		System.out.println("-- apply Statement: " + description);
		return super.apply(base, description);
	}

	
	
	
}
