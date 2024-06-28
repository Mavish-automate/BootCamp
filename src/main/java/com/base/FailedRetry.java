package com.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailedRetry implements IRetryAnalyzer {

	int count = 0;
	int max_count = 3;

	public boolean retry(ITestResult result) {
		while (count < max_count) {
			count++;
			return true;
		}
		return false;
	}

}
