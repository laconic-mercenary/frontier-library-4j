/**
 * 
 */
package com.frontier.lib;

import java.util.Random;
import java.util.List;

import com.frontier.lib.validation.NumberValidation;

/**
 * @author mlcs05
 *
 */
public abstract class RandomGeneration {

	public static int random(int base, int ceilingExclusive) {
		int originified = ceilingExclusive - base;
		if (originified == 0) {
			return base;
		} else {
			NumberValidation.raiseIfLessThanOrEqualTo(originified, 0);
			return new Random().nextInt(originified) + base;
		}
	}
	
	public static <T> T selectRandom(List<T> collection) {
		return collection.get(
			random(0, collection.size())
		);
	}
}
