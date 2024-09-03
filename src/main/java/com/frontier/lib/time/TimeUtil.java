package com.frontier.lib.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class TimeUtil {

	public static final Integer MILLISECONDS_PER_SECOND = 1000;

	public static final Integer SECONDS_PER_MINUTE = 60;

	public static final Integer MINUTES_PER_HOUR = 60;

	public static final Integer HOURS_PER_FULL_DAY = 24;

	private static final String TIMEZONE = "UTC";

	/**
	 * Subtracts the specified number of days from the given date
	 * 
	 * @param date
	 *            The date to subtract the amount of days from (the date in the
	 *            future from the result).
	 * @param days
	 *            The number of days to subtract from the date parameter. This
	 *            can be negative or positive, but all negative values will be
	 *            made positive.
	 * @return The resulting date from the subtraction of days.
	 */
	public static Date subtractDaysFrom(Date date, Integer days) {
		return subtractFieldFrom(date, Calendar.DATE, days);
	}

	/**
	 * Adds the specified number of days to the given date
	 * 
	 * @param date
	 *            The date to add the number of days to
	 * @param days
	 *            The number of days
	 * @return The resulting date from the addition of days.
	 */
	public static Date addDaysTo(Date date, Integer days) {
		return addFieldTo(date, Calendar.DATE, days);
	}

	/**
	 * Returns the current time as a Date object.
	 * 
	 * @return
	 */
	public static Date nowUTC() {
		Calendar utcCalendar = calendarUTC();
		long utcTimestamp = utcCalendar.getTimeInMillis();
		return new Date(utcTimestamp);
	}

	/**
	 * Gets a new random int
	 * 
	 * @param ceiling The maximum value allowed for the random
	 * @return A new random int
	 */
	public static int randomInt(int ceiling) {
		return new Random().nextInt(ceiling);
	}

	/**
	 * Gets a Calendar instance with a UTC timezone
	 * 
	 * @return
	 */
	public static Calendar calendarUTC() {
		return getCalendar();
	}

	private static Date subtractFieldFrom(Date baseDate, Integer unit,
			Integer amount) {
		Calendar calendar = getCalendar();
		calendar.setTime(baseDate);
		calendar.add(unit, Math.abs(amount) * -1);
		return calendar.getTime();
	}

	private static Date addFieldTo(Date baseDate, Integer unit, Integer amount) {
		Calendar calendar = getCalendar();
		calendar.setTime(baseDate);
		calendar.add(unit, Math.abs(amount));
		return calendar.getTime();
	}

	private static Calendar getCalendar() {
		return Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
	}
}
