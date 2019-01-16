package uniupo.gaborgalazzo.calendar.domain;

import com.google.gson.*;
import uniupo.gaborgalazzo.calendar.exception.AppointmentParsingException;
import uniupo.gaborgalazzo.calendar.exception.AppointmentOverlapException;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Agenda. <br> This object allow you to manage {@link Appointment} by adding them, removing them and
 * searching for them.<br>
 *
 * @author Gabor Galazzo
 * @version 1.0.0
 * @see Iterable
 * @see Appointment
 */
public class Agenda implements Iterable<Appointment>
{
	private ArrayList<Appointment> appointments;

	/**
	 * Instantiates a new Agenda.
	 */
	public Agenda(){
		appointments = new ArrayList<>();
	}


	/**
	 * Add boolean an appointment.
	 *
	 * @param appointment the new appointment to add
	 * @return {@link List#add(Object)}
	 * @throws AppointmentOverlapException the new appointment overlap an existing appointment
	 * @see #overlaps(Appointment) #overlaps(Appointment)
	 */
	public boolean add(Appointment appointment) throws AppointmentOverlapException
	{
		Appointment collision = overlaps(appointment);
		if(collision!=null)
			throw new AppointmentOverlapException(collision);
		return appointments.add(appointment);
	}

	/**
	 * Remove an appointment
	 *
	 * @param appointment the appointment to remove
	 * @return {@link List#remove(Object)}
	 */
	public boolean remove(Appointment appointment){
		return appointments.remove(appointment);
	}

	/**
	 * Allow you to edit an appointment in this agenda
	 *
	 * @param old appointment to edit
	 * @param date new date
	 * @param time new time
	 * @param duration new duration
	 * @param with new with
	 * @param where new where
	 * @return the edited appointment, null if {@link #add(Appointment)} returns false
	 * @throws AppointmentOverlapException the new appointment overlap an existing one
	 * @throws DateTimeParseException date or time string has an invalid format
	 * @throws InvalidParameterException duration is invalid
	 * @throws InstanceNotFoundException old appointment not found in agenda
	 * @see Appointment#Appointment(String, String, int, String, String)
	 */
	public Appointment edit(Appointment old, String date, String time, int duration, String with, String where) throws AppointmentOverlapException, DateTimeParseException, InvalidParameterException, InstanceNotFoundException
	{
		if(!appointments.contains(old))
			throw new InstanceNotFoundException();
		remove(old);
		Appointment appointment = new Appointment(date, time, duration, with, where);
		try
		{
			if(add(appointment))
				return appointment;
			throw new RuntimeException("Add failed during appointment edit");
		} catch (AppointmentOverlapException e)
		{
			try
			{
				add(old);
			} catch (AppointmentOverlapException e1)
			{
				throw new RuntimeException(e1);
			}
			throw e;
		}
	}


	/**
	 * Find all appointment containing a determinate string in their {@link Appointment#with}.
	 *
	 * @param with the string to search
	 * @return an {@link ArrayList} containing all {@link Appointment} that containing the determinate
	 * 		string in their {@link Appointment#with}.
	 * @see #findByPredicate(Predicate) #findByPredicate(Predicate)
	 */
	public ArrayList<Appointment> findByWith(String with){

		return findByPredicate(
						appointment ->
								appointment.getWith().contains(with)
				);
	}

	/**
	 * Find all appointment of a determinate date. <br> this method doesn't check date's format because it use {@link
	 * Appointment#getDate()} equals
	 *
	 * @param date the date
	 * @return an {@link ArrayList} containing all {@link Appointment} of the specified date.
	 * @see #findByPredicate(Predicate) #findByPredicate(Predicate)
	 */
	public ArrayList<Appointment> findByDate(String date){

		return findByPredicate(
						appointment ->
								appointment.getDate().equals(date)
				);
	}

	/**
	 * Get all appointments sorted by date using {@link Stream}.
	 *
	 * @return an {@link ArrayList} containing all {@link Appointment} sorted by date.
	 * @see Stream
	 */
	public ArrayList<Appointment> getAll(){
		return  appointments
				.stream()
				.sorted()
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Find all appointment matching a determinate {@link Predicate}.
	 *
	 * @param predicate the predicate
	 * @return an {@link ArrayList} containing all {@link Appointment} matching the determinate predicate.
	 * @see Predicate
	 * @see Stream#filter(Predicate) Stream#filter(Predicate)
	 */
	public ArrayList<Appointment> findByPredicate(Predicate<Appointment> predicate){
		return appointments
				.stream()
				.filter(
						predicate
				)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Check if an appointment period overlaps an appointment period present in this agenda <br> An appointment period
	 * start at their {@link Appointment#date} and their {@link Appointment#time} and end after {@link
	 * Appointment#duration} minutes
	 *
	 * @param appointment the appointment to check
	 * @return the appointment that overlaps the appointment to check, null if there are no overlaps
	 */
	public Appointment overlaps(Appointment appointment)
	{
		for(Appointment a: appointments){
			LocalDateTime aDateEnd = LocalDateTime.from(a.getDateTime()).plusMinutes(a.getDuration());
			if(appointment.getDateTime().isBefore(aDateEnd) && (appointment.getDateTime().isAfter(a.getDateTime()) || appointment.getDateTime().isEqual(a.getDateTime())))
				return a;
		}
		return null;
	}

	/**
	 * Write all appointments in a writer as a {@link JsonArray}
	 *
	 * @param writer the writer
	 * @throws IOException writer IO errors on write
	 */
	public void writeAgenda(Writer writer) throws IOException
	{
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		writer.write(gson.toJson(appointments));
	}


	/**
	 * Try to add all appointment from a reader. <br> The source reader must contain an array of {@link Appointment} in
	 * Json format.
	 *
	 * @param reader the reader
	 * @return an {@link ArrayList} containing all Exceptions occurred during the
	 * 		loading process
	 * @throws IllegalStateException caused by a json parsing exception of the reader
	 * @see AppointmentParsingException
	 */
	public ArrayList<AppointmentParsingException> readAgenda(Reader reader) throws IllegalStateException{
		// Read from File to String
		ArrayList<AppointmentParsingException> exceptions = new ArrayList<AppointmentParsingException>();
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(reader);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		for(JsonElement element: jsonArray){
			try {
				JsonObject jsonObject = element.getAsJsonObject();
				String date = jsonObject.get("date").getAsString();
				String time = jsonObject.get("time").getAsString();
				int duration = jsonObject.get("duration").getAsInt();
				String with = jsonObject.get("with").getAsString();
				String where = jsonObject.get("where").getAsString();
				Appointment appointment = new Appointment(date, time, duration, with, where);
				add(appointment);
			} catch (Exception e) {
				exceptions.add(new AppointmentParsingException(element, e));
			}
		}
		return exceptions;
	}



	@Override
	public Iterator<Appointment> iterator()
	{
		return getAll().iterator();
	}






}
