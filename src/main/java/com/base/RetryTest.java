package com.base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest 
{
	@Test(retryAnalyzer = FailedRetry.class)
	public void tc() 
	{
		Assert.assertTrue(false);
	}
}

