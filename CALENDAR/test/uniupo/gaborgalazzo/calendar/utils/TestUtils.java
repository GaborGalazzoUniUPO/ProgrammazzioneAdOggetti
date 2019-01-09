package uniupo.gaborgalazzo.calendar.utils;

import uniupo.gaborgalazzo.calendar.domain.Appointment;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Random;

import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

public class TestUtils
{
	public static String randomString(int numChars){
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(numChars);
		for (int i = 0; i < numChars; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}

	public static String randomString(){
		return randomString(24);
	}

	public static int randomInteger() {
		return randomInteger(0,1024);
	}

	public static int randomInteger(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static Calendar randomDate()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, randomInteger(2018,2020));
		calendar.set(Calendar.DAY_OF_YEAR, randomInteger(1, calendar.getActualMaximum(Calendar.DAY_OF_YEAR)));
		calendar.set(Calendar.HOUR_OF_DAY, randomInteger(0, calendar.getActualMaximum(Calendar.HOUR_OF_DAY)));
		calendar.set(Calendar.MINUTE, randomInteger(0,60));
		return calendar;
	}

	public static Appointment randomAppointment() throws ParseException
	{
		Calendar calendar = TestUtils.randomDate();
		String date = APPOINTMENT_DATE_FORMAT.format(calendar.getTimeInMillis());
		String time = APPOINTMENT_TIME_FORMAT.format(calendar.getTimeInMillis());
		return randomAppointment(date, time);
	}

	public static Appointment randomAppointment(String date, String time) throws ParseException
	{
		int duration = TestUtils.randomInteger(30,120);
		return randomAppointment(date, time, duration);
	}

	public static Appointment randomAppointment(String date, String time, int duration) throws ParseException
	{
		String with = TestUtils.randomString(20);
		String where = TestUtils.randomString(20);
		return new Appointment(date, time, duration, with, where);
	}
}
