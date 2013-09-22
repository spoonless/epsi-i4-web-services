package epsi.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
	
	private static enum TestResult {success, failure, error}

	public int run(Object instance) {
		int returnCode = 0 ;
		for (Method method : instance.getClass().getMethods()) {
			Test testAnnotation = method.getAnnotation(Test.class);
			if (testAnnotation != null) {
				TestResult testResult = run(instance, method, testAnnotation);
				if (testResult != TestResult.success) {
					returnCode = 1;
				}
			}
		}
		return returnCode;
	}

	private TestResult run(Object instance, Method method, Test testAnnotation) {
		TestResult testResult = TestResult.success;
		try {
			System.out.println();
			System.out.println(">> Running test method: " + method.getName());
			System.out.print("    ");
			System.out.println(testAnnotation.description());
			method.invoke(instance);
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
			testResult = TestResult.failure;
		} catch (IllegalAccessException 
				| IllegalArgumentException e) {
			e.printStackTrace();
			testResult = TestResult.error;
		}
		System.out.println(">> test result: " + testResult);
		return testResult;
	}	
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Expected test class as first argument!");
			System.exit(1);
		}
		TestRunner testRunner = new TestRunner();
		int resultCode = testRunner.run(Class.forName(args[0]).newInstance());
		System.exit(resultCode);
	}
}
