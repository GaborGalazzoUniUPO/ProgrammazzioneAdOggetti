package uniupo.gaborgalazzo.calendar.utils;

import uniupo.gaborgalazzo.calendar.domain.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Random;

import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

/**
 * Class used during testing
 */
public class TestUtils
{
	/**
	 * Generate a random alphabetic string.
	 *
	 * @param numChars the num chars
	 * @return the random string
	 */
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

	/**
	 * Generate a random 24 character string
	 *
	 * @return the string
	 */
	public static String randomString(){
		return randomString(24);
	}

	/**
	 * Generate a random int between 0 and 1024
	 *
	 * @return the int
	 */
	public static int randomInteger() {
		return randomInteger(0,1024);
	}

	/**
	 * Generate a random int between min and max
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randomInteger(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * Genarate a random LocalDateTime from 2018-01-01 00:00 to 2020-12-31 23:59
	 *
	 * @return the random localDateDime
	 */
	public static LocalDateTime randomDate()
	{
		return LocalDateTime.of(randomInteger(2018,2020),randomInteger(1, 12), randomInteger(1,28), randomInteger(0,23), randomInteger(0,59) );

	}

	/**
	 * Generate a random appointment
	 *
	 * @return the appointment
	 */
	public static Appointment randomAppointment()
	{
		LocalDateTime localDateTime = TestUtils.randomDate();
		String date = APPOINTMENT_DATE_FORMAT.format(localDateTime);
		String time = APPOINTMENT_TIME_FORMAT.format(localDateTime);
		return randomAppointment(date, time);
	}

	/**
	 * Generate a random appointment specifying date and time
	 *
	 * @param date the date
	 * @param time the time
	 * @return the appointment
	 * @throws DateTimeParseException the date time parse exception
	 */
	public static Appointment randomAppointment(String date, String time) throws DateTimeParseException
	{
		int duration = TestUtils.randomInteger(30,120);
		return randomAppointment(date, time, duration);
	}

	/**
	 *  Generate a random appointment specifying date, time and duration
	 *
	 * @param date     the date
	 * @param time     the time
	 * @param duration the duration
	 * @return the appointment
	 * @throws DateTimeParseException the date time parse exception
	 */
	public static Appointment randomAppointment(String date, String time, int duration) throws DateTimeParseException
	{
		String with = TestUtils.randomString(20);
		String where = TestUtils.randomString(20);
		return new Appointment(date, time, duration, with, where);
	}


}
