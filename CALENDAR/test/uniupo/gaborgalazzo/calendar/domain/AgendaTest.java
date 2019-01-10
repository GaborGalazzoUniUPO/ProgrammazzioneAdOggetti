package uniupo.gaborgalazzo.calendar.domain;

import org.junit.Before;
import org.junit.Test;
import uniupo.gaborgalazzo.calendar.domain.Agenda;
import uniupo.gaborgalazzo.calendar.domain.Appointment;
import uniupo.gaborgalazzo.calendar.exception.AppointmentCollisionException;
import uniupo.gaborgalazzo.calendar.exception.ReadException;
import uniupo.gaborgalazzo.calendar.utils.TestUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

public class AgendaTest
{
	private Agenda agenda;

	@Before
	public void setUp() throws Exception{
		agenda = new Agenda();
	}

	@Test
	public void add() throws ParseException
	{
		Appointment appointment = TestUtils.randomAppointment();
		try
		{
			assertTrue(agenda.add(appointment));
		} catch (AppointmentCollisionException e)
		{
			fail();
		}


		Date overlap = new Date(appointment.getDDate().getTime() + TestUtils.randomInteger(0, appointment.getDuration())*TimeUnit.MINUTES.toMillis(1));
		Appointment collision = TestUtils.randomAppointment(APPOINTMENT_DATE_FORMAT.format(overlap), APPOINTMENT_TIME_FORMAT.format(overlap));
		try
		{
			agenda.add(collision);
			fail();
		} catch (AppointmentCollisionException e){
			assertEquals(appointment, e.getAppointment());
		}
	}

	@Test
	public void remove() throws ParseException, AppointmentCollisionException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertTrue(agenda.remove(appointment));
		assertTrue(agenda.add(appointment));
	}

	@Test
	public void findByWith() throws ParseException, AppointmentCollisionException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertFalse(agenda.findByWith(appointment.getWith()).isEmpty());
		assertTrue(agenda.findByWith(appointment.getWith()+TestUtils.randomInteger(10,20)).isEmpty());
	}

	@Test
	public void findByDate() throws ParseException, AppointmentCollisionException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertFalse(agenda.findByDate(appointment.getDate()).isEmpty());
		Date different = new Date(appointment.getDDate().getTime() + TimeUnit.DAYS.toMillis(2));
		assertTrue(agenda.findByWith(APPOINTMENT_DATE_FORMAT.format(different)).isEmpty());
	}

	@Test
	public void getAll() throws ParseException
	{
		for(int i = 0; i<TestUtils.randomInteger(10,20); i++){
			Appointment appointment = TestUtils.randomAppointment();
			try
			{
				agenda.add(appointment);
			} catch (AppointmentCollisionException ignored){}
		}

		Appointment prev = null;

		for(Appointment appointment: agenda.getAll()){
			if(prev == null)
			{
				prev = appointment;
				continue;
			}
			assertTrue(appointment.compareTo(prev)>0);
		}
	}

	@Test
	public void agendaWriteRead() throws IOException, ParseException
	{
		for(int i = 0; i<TestUtils.randomInteger(10,20); i++){
			Appointment appointment = TestUtils.randomAppointment();
			try
			{
				agenda.add(appointment);
			} catch (AppointmentCollisionException ignored){}
		}

		ArrayList<Appointment> oldData = agenda.getAll();

		String filename = TestUtils.randomString(20)+".json";
		FileWriter fileWriter = new FileWriter(filename);
		agenda.writeAgenda(fileWriter);
		fileWriter.close();
		assertTrue(Files.exists(Paths.get(filename)));

		for(Appointment appointment: oldData)
			agenda.remove(appointment);

		assertTrue(agenda.getAll().isEmpty());



		FileReader fileReader = new FileReader(filename);
		ArrayList<ReadException> errors = agenda.readAgenda(fileReader);
		fileReader.close();

		assertTrue(errors.isEmpty());
		assertTrue(agenda.getAll().containsAll(oldData));

		fileReader = new FileReader(filename);
		errors = agenda.readAgenda(fileReader);
		fileReader.close();
		assertTrue(errors.size()>0);

		fileReader = new FileReader("not_json_array.json");
		try {

			agenda.readAgenda(fileReader);
			fail();

		}catch (IllegalStateException ignored){}

		fileReader.close();

		oldData = agenda.getAll();
		for(Appointment appointment: oldData)
			agenda.remove(appointment);

		fileReader = new FileReader("incorrect_data.json");
		errors = agenda.readAgenda(fileReader);
		fileReader.close();
		assertTrue(errors.size()>0);
		for(ReadException exception: errors)
			assertFalse(exception.getE() instanceof AppointmentCollisionException);


		Files.delete(Paths.get(filename));
	}
}