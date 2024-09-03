/**
 * 
 */
package com.frontier.lib;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author mlcs05
 *
 */
public class RandomGenerationTest {

	@Test
	public void test_single_element() {
		String result = "test";
		assertEquals(result, RandomGeneration.selectRandom(Arrays.asList(result)));
		assertEquals(result, RandomGeneration.selectRandom(Arrays.asList(result)));
		assertEquals(result, RandomGeneration.selectRandom(Arrays.asList(result)));
		assertEquals(result, RandomGeneration.selectRandom(Arrays.asList(result)));
		assertEquals(result, RandomGeneration.selectRandom(Arrays.asList(result)));
	}

	@Test
	public void test_limited_range() {
		int result = RandomGeneration.random(1, 1);
		assertEquals(1, result);
		result = RandomGeneration.random(2, 2);
		assertEquals(2, result);
	}
	
}
