package unittesting.mock;

import java.io.IOException;

public class ExampleClass {
	
	String signature;

	public ExampleClass() {
		super();
	}
	
	public ExampleClass(String signature) {
		super();
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String exampleFunctionality(String to) {
		return "Hello " + to;
	}
	
	public void doSomethingRisky() throws IOException {
		System.err.println("I'm doing something risky...");
	}
	
	public int return12() {
		return 12;
	}
	
}
