package com.frontier.lib.io.configuration.delim;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DelimConfigurationProcessorTest {

	@Test(expected = InvalidDelimConfigurationException.class)
	public void testAssertCompleteParamms_ThrowIfNull()
			throws InvalidDelimConfigurationException {
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(null);
		dcp.assertCompleteParameters();
	}

	@Test(expected = InvalidDelimConfigurationException.class)
	public void testAssertCompleteParamms_ThrowIfNoItemDelim()
			throws InvalidDelimConfigurationException {
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(
				new DelimConfigurationParameters());
		dcp.assertCompleteParameters();
	}

	@Test(expected = InvalidDelimConfigurationException.class)
	public void testAssertCompleteParamms_ThrowIfNoAttrDelim()
			throws InvalidDelimConfigurationException {
		DelimConfigurationParameters dcprm = new DelimConfigurationParameters();
		dcprm.setItemDelimeter("||");
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(dcprm);
		dcp.assertCompleteParameters();
	}

	@Test()
	public void testAssertCompleteParamms_Good()
			throws InvalidDelimConfigurationException {
		DelimConfigurationParameters dcprm = new DelimConfigurationParameters();
		dcprm.setItemDelimeter("||");
		dcprm.setAttributeDelimeter("|");
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(dcprm);
		dcp.assertCompleteParameters();
	}

	@Test
	public void testParseAsList() {
		DelimConfigurationParameters dcprm = new DelimConfigurationParameters();
		dcprm.setItemDelimeter("\\|\\|"); // these need to be regexes
		dcprm.setAttributeDelimeter("\\|");
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(dcprm);

		String test = "";
		List<List<String>> result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.get(0).get(0), "");

		test = "asdf";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.get(0).get(0), "asdf");

		test = "asdf|";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.get(0).get(0), "asdf");

		test = "asdf|fdsa";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(2, result.get(0).size());
		Assert.assertEquals(result.get(0).get(0), "asdf");
		Assert.assertEquals(result.get(0).get(1), "fdsa");

		test = "asdf|fdsa|zzz";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(3, result.get(0).size());
		Assert.assertEquals(result.get(0).get(0), "asdf");
		Assert.assertEquals(result.get(0).get(1), "fdsa");
		Assert.assertEquals(result.get(0).get(2), "zzz");

		test = "asdf|fdsa||zzz";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(2, result.get(0).size());
		Assert.assertEquals(1, result.get(1).size());
		Assert.assertEquals(result.get(0).get(0), "asdf");
		Assert.assertEquals(result.get(0).get(1), "fdsa");
		Assert.assertEquals(result.get(1).get(0), "zzz");

		test = "asdf|fdsa||zzz||1234|z";
		result = dcp.parseAsList(test);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(2, result.get(0).size());
		Assert.assertEquals(1, result.get(1).size());
		Assert.assertEquals(2, result.get(2).size());
		Assert.assertEquals(result.get(0).get(0), "asdf");
		Assert.assertEquals(result.get(0).get(1), "fdsa");
		Assert.assertEquals(result.get(1).get(0), "zzz");
		Assert.assertEquals(result.get(2).get(0), "1234");
		Assert.assertEquals(result.get(2).get(1), "z");
	}

	@Test
	public void testParseAsMap() {
		DelimConfigurationParameters dcprm = new DelimConfigurationParameters();
		dcprm.setItemDelimeter("\\|\\|"); // these need to be regexes
		dcprm.setAttributeDelimeter("\\|");
		DelimConfigurationProcessor dcp = new DelimConfigurationProcessor(dcprm);

		String test = "";
		Map<String, List<String>> result = dcp.parseAsMap(test, 0);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey(""));
		Assert.assertTrue(result.get("").isEmpty());

		test = "asdf";
		result = dcp.parseAsMap(test, 0);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("asdf"));
		Assert.assertTrue(result.get("asdf").isEmpty());

		test = "asdf|1|2|3";
		result = dcp.parseAsMap(test, 0);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("asdf"));
		Assert.assertEquals(3, result.get("asdf").size());

		test = "asdf|1|2|3||fdsa|9";
		result = dcp.parseAsMap(test, 0);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.containsKey("asdf"));
		Assert.assertTrue(result.containsKey("fdsa"));
		Assert.assertEquals(3, result.get("asdf").size());
		Assert.assertEquals("1", result.get("asdf").get(0));
		Assert.assertEquals("9", result.get("fdsa").get(0));
		
		test = "asdf|1|2|3||fdsa|9";
		result = dcp.parseAsMap(test, 1);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.containsKey("1"));
		Assert.assertTrue(result.containsKey("9"));
		Assert.assertEquals(3, result.get("1").size());
		Assert.assertEquals("asdf", result.get("1").get(0));
		Assert.assertEquals("fdsa", result.get("9").get(0));
		
		test = "asdf|1|2|3||fdsa|9";
		result = dcp.parseAsMap(test, 2); // even though the first one would work... it still breaks everything
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}
}
