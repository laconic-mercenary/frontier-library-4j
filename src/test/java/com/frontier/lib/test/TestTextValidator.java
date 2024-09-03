
package com.frontier.lib.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.frontier.lib.validation.TextValidator;

public class TestTextValidator {

	private TextValidator v = null;

	@Before
	public void setup() {
		v = new TextValidator();
	}

	@Test
	public void testIsEmpty() {
		String entry = null;
		Assert.assertTrue(v.isEmpty(entry));

		entry = "";
		Assert.assertTrue(v.isEmpty(entry));

		entry = " ";
		Assert.assertTrue(v.isEmpty(entry));

		entry = "a";
		Assert.assertFalse(v.isEmpty(entry));

		entry = null;
		Assert.assertFalse(v.isEmpty(entry, false));

		entry = "    ";
		Assert.assertFalse(v.isEmpty(entry, true, false));
	}

	@Test
	public void testIsOverflow() {
		String entry = null;
		try {
			Assert.assertFalse(v.isOverflow(entry, 10));
			Assert.fail();
		} catch (NullPointerException e) {
		}

		entry = "";
		Assert.assertFalse(v.isOverflow(entry, 10));
		
		entry = "";
		Assert.assertFalse(v.isOverflow(entry, 0));
		
		entry = "a";
		Assert.assertFalse(v.isOverflow(entry, 1));
		
		entry = "asdfasdfsa";
		Assert.assertFalse(v.isOverflow(entry, 10));

		entry = "asdfasdfasdfasdasdfasdf";
		Assert.assertTrue(v.isOverflow(entry, 10));
		
		entry = "";
		Assert.assertTrue(v.isOverflow(entry, 5, 1));
		
		entry = "a";
		Assert.assertFalse(v.isOverflow(entry, 5, 1));
		
		entry = "12345";
		Assert.assertFalse(v.isOverflow(entry, 5, 5));
		
		entry = null;
		Assert.assertFalse(v.isOverflow(entry, 5, 0, false));
		
		entry = null;
		Assert.assertTrue(v.isOverflow(entry, 5, 1, false));
	}
	
	@Test
	public void testIsUpperCase() {
		String entry = "";
		Assert.assertTrue(v.isUpperCase(entry));
		
		entry = "A";
		Assert.assertTrue(v.isUpperCase(entry));
		
		entry = "ABSDF";
		Assert.assertTrue(v.isUpperCase(entry));
		
		entry = "AaAAAA";
		Assert.assertFalse(v.isUpperCase(entry));
	}
	
	@Test
	public void testIsLowerCase() {
		String entry = "";
		Assert.assertTrue(v.isLowerCase(entry));
		
		entry = "a";
		Assert.assertTrue(v.isLowerCase(entry));
		
		entry = "absdf";
		Assert.assertTrue(v.isLowerCase(entry));
		
		entry = "AaAAAA";
		Assert.assertFalse(v.isLowerCase(entry));
	}
}
