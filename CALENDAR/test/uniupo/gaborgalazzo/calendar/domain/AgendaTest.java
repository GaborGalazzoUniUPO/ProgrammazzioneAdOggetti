package uniupo.gaborgalazzo.calendar.domain;

import org.junit.Before;
import org.junit.Test;
import uniupo.gaborgalazzo.calendar.exception.AppointmentParsingException;
import uniupo.gaborgalazzo.calendar.exception.AppointmentOverlapException;
import uniupo.gaborgalazzo.calendar.utils.TestUtils;

import javax.management.InstanceNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_DATE_FORMAT;
import static uniupo.gaborgalazzo.calendar.domain.Appointment.APPOINTMENT_TIME_FORMAT;

public class AgendaTest
{
	private Agenda agenda;

	@Before
	public void setUp()
	{
		agenda = new Agenda();
	}

	@Test
	public void add()
	{
		Appointment appointment = TestUtils.randomAppointment();
		try
		{
			assertTrue(agenda.add(appointment));
		} catch (AppointmentOverlapException e)
		{
			fail();
		}


		LocalDateTime overlap = LocalDateTime.from(appointment.getDateTime()).plusMinutes(TestUtils.randomInteger(0, appointment.getDuration()));
		Appointment collision = TestUtils.randomAppointment(APPOINTMENT_DATE_FORMAT.format(overlap), APPOINTMENT_TIME_FORMAT.format(overlap));
		try
		{
			agenda.add(collision);
			fail();
		} catch (AppointmentOverlapException e){
			assertEquals(appointment, e.getAppointment());
		}
	}

	@Test
	public void remove() throws AppointmentOverlapException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertTrue(agenda.remove(appointment));
		assertFalse(agenda.remove(appointment));
		assertTrue(agenda.add(appointment));
	}

	@Test
	public void findByWith() throws AppointmentOverlapException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertFalse(agenda.findByWith(appointment.getWith()).isEmpty());
		assertTrue(agenda.findByWith(appointment.getWith()+TestUtils.randomInteger(10,20)).isEmpty());
	}

	@Test
	public void findByDate() throws AppointmentOverlapException
	{
		Appointment appointment = TestUtils.randomAppointment();
		agenda.add(appointment);
		assertFalse(agenda.findByDate(appointment.getDate()).isEmpty());
		LocalDateTime different = LocalDateTime.from(appointment.getDateTime()).plusDays(2);
		assertTrue(agenda.findByWith(APPOINTMENT_DATE_FORMAT.format(different)).isEmpty());
	}

	@Test
	public void getAll()
	{
		for(int i = 0; i<TestUtils.randomInteger(10,20); i++){
			Appointment appointment = TestUtils.randomAppointment();
			try
			{
				agenda.add(appointment);
			} catch (AppointmentOverlapException ignored){}
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
	public void agendaWriteRead() throws IOException
	{
		for(int i = 0; i<TestUtils.randomInteger(10,20); i++){
			Appointment appointment = TestUtils.randomAppointment();
			try
			{
				agenda.add(appointment);
			} catch (AppointmentOverlapException ignored){}
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
		ArrayList<AppointmentParsingException> errors = agenda.readAgenda(fileReader);
		fileReader.close();

		assertTrue(errors.isEmpty());
		assertTrue(agenda.getAll().containsAll(oldData));

		fileReader = new FileReader(filename);
		errors = agenda.readAgenda(fileReader);
		fileReader.close();
		assertTrue(errors.size()>0);

		for(AppointmentParsingException exception: errors)
			assertEquals(exception.getException().getClass() , AppointmentOverlapException.class);

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

		Files.delete(Paths.get(filename));
	}

	@Test
	public void edit() throws AppointmentOverlapException, InstanceNotFoundException
	{
		Appointment appointment = TestUtils.randomAppointment();


		Appointment appointmentEdited = TestUtils.randomAppointment(appointment.getDate(), appointment.getTime(), appointment.getDuration()+1);
		agenda.add(appointment);

		assertNotEquals(appointment, appointmentEdited);
		Appointment newAppointmentEdited = agenda.edit(appointment, appointmentEdited.getDate(), appointmentEdited.getTime(), appointmentEdited.getDuration(), appointmentEdited.getWith(), appointmentEdited.getWhere());
		assertEquals(newAppointmentEdited, appointmentEdited);

		populateAgenda(3,4);


		Appointment appointmentEditOverlap = agenda.findByPredicate(x -> !x.equals(newAppointmentEdited)).get(0);

		try
		{
			agenda.edit(newAppointmentEdited, appointmentEditOverlap.getDate(), appointmentEditOverlap.getTime(), appointmentEditOverlap.getDuration(), appointmentEditOverlap.getWith(), appointmentEditOverlap.getWhere());
			fail();
		}catch (AppointmentOverlapException e){
			assertEquals(e.getAppointment(),appointmentEditOverlap );
		} catch (InstanceNotFoundException e)
		{
			fail();
		}

		try
		{
			agenda.edit(null, appointmentEditOverlap.getDate(), appointmentEditOverlap.getTime(), appointmentEditOverlap.getDuration(), appointmentEditOverlap.getWith(), appointmentEditOverlap.getWhere());
			fail();
		}catch (InstanceNotFoundException e){
			assertNotNull(e);
		}




	}

	@Test
	public void findByPredicate()
	{
		Predicate<Appointment> predicate = appointment -> true;

		agenda.findByPredicate(predicate).equals(agenda.getAll().stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new)));
	}

	@Test
	public void overlaps() throws AppointmentOverlapException
	{
		Appointment appointment = TestUtils.randomAppointment();
		assertNull(agenda.overlaps(appointment));
		agenda.add(appointment);
		assertEquals(appointment, agenda.overlaps(appointment));
	}

	@Test
	public void writeAgenda() throws IOException
	{
		agendaWriteRead();
	}

	@Test
	public void readAgenda() throws IOException
	{
		agendaWriteRead();
	}

	@Test
	public void iterator()
	{

		populateAgenda(5,10);

		Iterator<Appointment> iterator = agenda.iterator();
		assertNotNull(iterator);
		ArrayList<Appointment> allAppointments = agenda.getAll();
		Appointment old = iterator.next();

		while (iterator.hasNext()){
			Appointment appointment = iterator.next();
			assertTrue(old.getDateTime().isBefore(appointment.getDateTime()) || old.getDateTime().isEqual(appointment.getDateTime()));
			assertTrue(allAppointments.remove(appointment));
		}
		assertTrue(allAppointments.remove(old));
		assertTrue(allAppointments.isEmpty());
	}

	private void populateAgenda(int min, int max)
	{
		while (agenda.getAll().size()<TestUtils.randomInteger(min,max)){
			try
			{
				agenda.add(TestUtils.randomAppointment());
			} catch (AppointmentOverlapException ignored){}
		}
	}
}