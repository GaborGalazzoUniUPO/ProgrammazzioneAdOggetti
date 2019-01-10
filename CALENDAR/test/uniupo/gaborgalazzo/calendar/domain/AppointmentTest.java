package uniupo.gaborgalazzo.calendar.domain;

import org.junit.Before;
import org.junit.Test;
import uniupo.gaborgalazzo.calendar.domain.Appointment;
import uniupo.gaborgalazzo.calendar.utils.TestUtils;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

public class AppointmentTest
{

	private Appointment appointment;

	@Before
	public void setUp() throws Exception
	{
		appointment = TestUtils.randomAppointment();
	}

	@Test
	public void getDDate()
	{
		Date actual = appointment.getDDate();
		assertEquals(appointment.getDate(),APPOINTMENT_DATE_FORMAT.format(actual));
		assertEquals(appointment.getTime(),APPOINTMENT_TIME_FORMAT.format(actual));
	}

	@Test
	public void getDate() throws ParseException
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
		} catch (ParseException ignored){}

		try
		{
			TestUtils.randomAppointment("31-02-2018", appointment.getTime());
			fail();
		} catch (ParseException ignored){}

		try
		{
			TestUtils.randomAppointment(TestUtils.randomInteger(31,100)+"-"+TestUtils.randomInteger(1,12)+"-"+TestUtils.randomInteger(2000,20010), appointment.getTime());
			fail();
		} catch (ParseException ignored){}

		try
		{
			TestUtils.randomAppointment(TestUtils.randomInteger(0,28)+"-"+TestUtils.randomInteger(13,100)+"-"+TestUtils.randomInteger(2000,20010), appointment.getTime());
			fail();
		} catch (ParseException ignored){}


	}

	@Test
	public void getTime() throws ParseException
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
		} catch (ParseException ignored){}
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
		}catch (InvalidParameterException | ParseException e)
		{
			assertTrue(e instanceof InvalidParameterException);
		}
	}




	@Test
	public void compareTo() throws ParseException
	{
		String dateNext = APPOINTMENT_DATE_FORMAT.format(appointment.getDDate().getTime() + TimeUnit.DAYS.toMillis(2));
		String datePrev = APPOINTMENT_DATE_FORMAT.format(appointment.getDDate().getTime() - TimeUnit.DAYS.toMillis(2));
		Appointment aNext = TestUtils.randomAppointment(dateNext, appointment.getTime());
		Appointment aPrev = TestUtils.randomAppointment(datePrev, appointment.getTime());
		assertEquals(appointment.compareTo(aNext), appointment.getDDate().compareTo(aNext.getDDate()));
		assertTrue(appointment.compareTo(aNext)<0);
		assertEquals(appointment.compareTo(aPrev), appointment.getDDate().compareTo(aPrev.getDDate()));
		assertTrue(appointment.compareTo(aPrev)>0);
		assertEquals(appointment.compareTo(appointment), appointment.getDDate().compareTo(appointment.getDDate()));
		assertTrue(appointment.compareTo(appointment) == 0);
	}


}