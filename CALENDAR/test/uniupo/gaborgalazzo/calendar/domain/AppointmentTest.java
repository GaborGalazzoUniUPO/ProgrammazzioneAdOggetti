package uniupo.gaborgalazzo.calendar.domain;

import org.junit.Before;
import org.junit.Test;
import uniupo.gaborgalazzo.calendar.utils.TestUtils;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

public class AppointmentTest
{

	private Appointment appointment;

	@Before
	public void setUp()
	{
		appointment = TestUtils.randomAppointment();
	}

	@Test
	public void getDateTime()
	{
		LocalDateTime actual = appointment.getDateTime();
		assertEquals(appointment.getDate(),APPOINTMENT_DATE_FORMAT.format(actual));
		assertEquals(appointment.getTime(),APPOINTMENT_TIME_FORMAT.format(actual));
	}

	@Test
	public void getDate()
	{
		assertNotNull(appointment.getDate());
		assertNotNull(APPOINTMENT_DATE_FORMAT.parse(appointment.getDate()));
	}

	@Test
	public void testDate()
	{
		try
		{
			TestUtils.randomAppointment(TestUtils.randomString(), appointment.getTime());
			fail();
		} catch (DateTimeParseException ignored){}

		try
		{
			TestUtils.randomAppointment("31-02-2018", appointment.getTime());
			fail();
		} catch (DateTimeParseException ignored){}

		try
		{
			TestUtils.randomAppointment(TestUtils.randomInteger(31,100)+"-"+TestUtils.randomInteger(1,12)+"-"+TestUtils.randomInteger(2000,20010), appointment.getTime());
			fail();
		} catch (DateTimeParseException ignored){}

		try
		{
			TestUtils.randomAppointment(TestUtils.randomInteger(0,28)+"-"+TestUtils.randomInteger(13,100)+"-"+TestUtils.randomInteger(2000,20010), appointment.getTime());
			fail();
		} catch (DateTimeParseException ignored){}


	}

	@Test
	public void getTime()
	{
		assertNotNull(appointment.getTime());
		assertNotNull(APPOINTMENT_TIME_FORMAT.parse(appointment.getTime()));
	}

	@Test
	public void setTime()
	{

		try
		{
			TestUtils.randomAppointment(appointment.getDate(), TestUtils.randomString());
			fail();
		} catch (DateTimeParseException ignored){}
	}

	@Test
	public void getDuration()
	{
		assertTrue(appointment.getDuration()>=0);
	}

	@Test
	public void setDuration()
	{

		try
		{
			int duration = TestUtils.randomInteger(1,120);
			TestUtils.randomAppointment(appointment.getDate(), appointment.getTime(), -duration);
			fail();
		}catch (InvalidParameterException | DateTimeParseException e)
		{
			assertTrue(e instanceof InvalidParameterException);
		}
	}




	@Test
	public void compareTo()
	{
		String dateNext = APPOINTMENT_DATE_FORMAT.format(LocalDateTime.from(appointment.getDateTime()).plusDays(2));
		String datePrev = APPOINTMENT_DATE_FORMAT.format(LocalDateTime.from(appointment.getDateTime()).minusDays(2));
		Appointment aNext = TestUtils.randomAppointment(dateNext, appointment.getTime());
		Appointment aPrev = TestUtils.randomAppointment(datePrev, appointment.getTime());
		assertEquals(appointment.compareTo(aNext), appointment.getDateTime().compareTo(aNext.getDateTime()));
		assertTrue(appointment.compareTo(aNext)<0);
		assertEquals(appointment.compareTo(aPrev), appointment.getDateTime().compareTo(aPrev.getDateTime()));
		assertTrue(appointment.compareTo(aPrev)>0);
		assertEquals(appointment.compareTo(appointment), appointment.getDateTime().compareTo(appointment.getDateTime()));
		assertTrue(appointment.compareTo(appointment) == 0);
	}


	@Test
	public void equals()
	{

		assertEquals(appointment, appointment);
		Appointment clone =
				new Appointment(appointment.getDate(), appointment.getTime(),
						appointment.getDuration(), appointment.getWith(), appointment.getWhere());
		assertEquals(appointment, clone);

		Appointment notClone =
				new Appointment(appointment.getDateTime().plusDays(TestUtils.randomInteger(10,20)).format(APPOINTMENT_DATE_FORMAT),
						appointment.getTime(), appointment.getDuration(), appointment.getWith(), appointment.getWhere());
		assertNotEquals(appointment, notClone);

		notClone =
				new Appointment(appointment.getDate(), appointment.getDateTime().plusMinutes(TestUtils.randomInteger(10,20)).format(APPOINTMENT_TIME_FORMAT),
						appointment.getDuration(), appointment.getWith(), appointment.getWhere());
		assertNotEquals(appointment, notClone);

		notClone =
				new Appointment(appointment.getDate(), appointment.getTime(),
				appointment.getDuration() + TestUtils.randomInteger(10,20), appointment.getWith(), appointment.getWhere());
		assertNotEquals(appointment, notClone);

		notClone = new Appointment(appointment.getDate(), appointment.getTime(),
				appointment.getDuration(), appointment.getWith() + TestUtils.randomString(3), appointment.getWhere());
		assertNotEquals(appointment, notClone);

		notClone = new Appointment(appointment.getDate(), appointment.getTime(),
				appointment.getDuration(), appointment.getWith(), appointment.getWhere() + TestUtils.randomString(3));
		assertNotEquals(appointment, notClone);
	}


	@Test
	public void toStringT()
	{
		assertEquals( "\n" +
				String.format("%-12s", "Date:") + appointment.getDate() + "\n" +
				String.format("%-12s", "Time:") + appointment.getTime() + "\n" +
				String.format("%-12s", "Duration:") + appointment.getDuration() + "\n" +
				String.format("%-12s", "With Whom:") + appointment.getWith() + "\n" +
				String.format("%-12s", "Where:") + appointment.getWhere() + "\n" +
				"\n", appointment.toString());
	}



}