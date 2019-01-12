package uniupo.gaborgalazzo.calendar.domain;

import com.google.gson.*;
import uniupo.gaborgalazzo.calendar.exception.AppointmentOverlapException;
import uniupo.gaborgalazzo.calendar.exception.AppointmentJsonParsingException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Agenda. <br/> This object allow you to manage {@link Appointment} by adding them, removing them and
 * searching for them.<br/>
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
	 * Find all appointment containing a determinate string in their {@link Appointment#with}.
	 *
	 * @param with the string to search
	 * @return an {@link ArrayList<Appointment>} containing all {@link Appointment} that containing the determinate
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
	 * Find all appointment of a determinate date. <br/> this method doesn't check date's format because it use {@link
	 * Appointment#getDate()#equals(Object)}
	 *
	 * @param date the date
	 * @return an {@link ArrayList<Appointment>} containing all {@link Appointment} of the specified date.
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
	 * @return an {@link ArrayList<Appointment>} containing all {@link Appointment} sorted by date.
	 * @see Stream
	 */
	public ArrayList<Appointment> getAll(){
		return  appointments
				.stream()
				.sorted()
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	/**
	 * Find all appointment matching a determinate {@link Predicate}.
	 *
	 * @param predicate the predicate
	 * @return an {@link ArrayList<Appointment>} containing all {@link Appointment} matching the determinate predicate.
	 * @see Predicate
	 * @see Stream#filter(Predicate) Stream#filter(Predicate)
	 */
	public ArrayList<Appointment> findByPredicate(Predicate<Appointment> predicate){
		return appointments
				.stream()
				.filter(
						predicate
				)
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	/**
	 * Check if an appointment period overlaps an appointment period present in this agenda <br/> An appointment period
	 * start at their {@link Appointment#date} and their {@link Appointment#time} and end after {@link
	 * Appointment#duration} minutes
	 *
	 * @param appointment the appointment to check
	 * @return the appointment that overlaps the appointment to check, null if there are no overlaps
	 */
	public Appointment overlaps(Appointment appointment)
	{
		for(Appointment a: appointments){
			LocalDateTime aDateEnd = LocalDateTime.from(a.getDateTime()).plusMinutes(TimeUnit.MINUTES.toMillis(a.getDuration()));
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
	 * Try to add all appointment from a reader. <br/> The source reader must contain an array of {@link Appointment} in
	 * Json format.
	 *
	 * @param reader the reader
	 * @return an {@link ArrayList< AppointmentJsonParsingException >} containing all Exceptions occurred during the
	 * 		loading process
	 * @throws IllegalStateException caused by a json parsing exception of the reader
	 * @see AppointmentJsonParsingException
	 */
	public ArrayList<AppointmentJsonParsingException> readAgenda(Reader reader) throws IllegalStateException{
		// Read from File to String
		ArrayList<AppointmentJsonParsingException> exceptions = new ArrayList<AppointmentJsonParsingException>();
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(reader);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		for(JsonElement element: jsonArray){
			JsonObject jsonObject = element.getAsJsonObject();
			try {
				String date = jsonObject.get("date").getAsString();
				String time = jsonObject.get("time").getAsString();
				int duration = jsonObject.get("duration").getAsInt();
				String with = jsonObject.get("with").getAsString();
				String where = jsonObject.get("where").getAsString();
				Appointment appointment = new Appointment(date, time, duration, with, where);
				add(appointment);
			} catch (Exception e) {
				exceptions.add(new AppointmentJsonParsingException(element, e));
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
