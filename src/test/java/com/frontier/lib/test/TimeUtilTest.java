
package com.frontier.lib.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.frontier.lib.time.TimeUtil;

public final class TimeUtilTest {

	@Test
	public void testSubtractDaysFrom() {
		Date baseDate = new Date();
		Integer days = 1;
		Date result = null;

		result = TimeUtil.subtractDaysFrom(baseDate, days);

		Assert.assertTrue(baseDate.after(result));
		Assert.assertEquals(baseDate.getTime() - result.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY);

		baseDate = new Date();
		days = 2;
		result = null;

		result = TimeUtil.subtractDaysFrom(baseDate, days);

		Assert.assertTrue(baseDate.after(result));
		Assert.assertEquals(baseDate.getTime() - result.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY
				* days);

		baseDate = new Date();
		days = -2;
		result = null;

		result = TimeUtil.subtractDaysFrom(baseDate, days);

		Assert.assertTrue(baseDate.after(result));
		Assert.assertEquals(baseDate.getTime() - result.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY
				* Math.abs(days));
	}

	@Test
	public void testAddDaysTo() {
		Date baseDate = new Date();
		Integer days = 1;
		Date result = null;

		result = TimeUtil.addDaysTo(baseDate, days);

		Assert.assertTrue(baseDate.before(result));
		Assert.assertEquals(result.getTime() - baseDate.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY);

		baseDate = new Date();
		days = 5;
		result = null;

		result = TimeUtil.addDaysTo(baseDate, days);

		Assert.assertTrue(baseDate.before(result));
		Assert.assertEquals(result.getTime() - baseDate.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY
				* days);

		baseDate = new Date();
		days = -15;
		result = null;

		result = TimeUtil.addDaysTo(baseDate, days);

		Assert.assertTrue(baseDate.before(result));
		Assert.assertEquals(result.getTime() - baseDate.getTime(), TimeUtil.MILLISECONDS_PER_SECOND
				* TimeUtil.SECONDS_PER_MINUTE * TimeUtil.MINUTES_PER_HOUR * TimeUtil.HOURS_PER_FULL_DAY
				* Math.abs(days));
	}
}
