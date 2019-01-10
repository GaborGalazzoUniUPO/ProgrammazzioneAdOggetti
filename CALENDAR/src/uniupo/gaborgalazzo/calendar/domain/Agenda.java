package uniupo.gaborgalazzo.calendar.domain;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uniupo.gaborgalazzo.calendar.exception.AppointmentCollisionException;
import uniupo.gaborgalazzo.calendar.exception.ReadException;
import uniupo.gaborgalazzo.calendar.gui.Input;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Agenda implements Serializable, Iterable<Appointment>
{
	private ArrayList<Appointment> appointments;

	public Agenda(){
		appointments = new ArrayList<>();
	}


	public boolean add(Appointment appointment) throws AppointmentCollisionException
	{
		Appointment collision = overlaps(appointment);
		if(collision!=null)
			throw new AppointmentCollisionException(collision);
		return appointments.add(appointment);
	}

	public boolean remove(Appointment appointment){
		return appointments.remove(appointment);
	}


	public ArrayList<Appointment> findByWith(String with){

		return findByPredicate(
						appointment ->
								appointment.getWith().contains(with)
				);
	}

	public ArrayList<Appointment> findByDate(String date){

		return findByPredicate(
						appointment ->
								appointment.getDate().equals(date)
				);
	}

	public ArrayList<Appointment> getAll(){
		return  appointments
				.stream()
				.sorted()
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	public ArrayList<Appointment> findByPredicate(Predicate<Appointment> predicate){
		return appointments
				.stream()
				.filter(
						predicate
				)
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	private Appointment overlaps(Appointment appointment)
	{
		for(Appointment a: appointments){
			Date aDateEnd = new Date(a.getDDate().getTime() + TimeUnit.MINUTES.toMillis(1) *a.getDuration());
			if(appointment.getDDate().before(aDateEnd) && (appointment.getDDate().after(a.getDDate()) || appointment.getDDate().equals(a.getDDate())))
				return a;
		}
		return null;
	}

	public void writeAgenda(Writer writer) throws IOException
	{
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		writer.write(gson.toJson(appointments));
	}

	public ArrayList<ReadException> readAgenda(Reader reader) throws IllegalStateException{
		// Read from File to String
		ArrayList<ReadException> exceptions = new ArrayList<ReadException>();
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
				exceptions.add(new ReadException(jsonObject, e));
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
