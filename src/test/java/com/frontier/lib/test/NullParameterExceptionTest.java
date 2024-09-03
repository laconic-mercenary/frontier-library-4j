package com.frontier.lib.test;

import org.junit.Assert;
import org.junit.Test;

import com.frontier.lib.NullParameterException;

public class NullParameterExceptionTest {

	@Test
	public void testInitialization() {
		try {
			throw new NullParameterException("testParam");
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("testParam"));
		}
	}
}
