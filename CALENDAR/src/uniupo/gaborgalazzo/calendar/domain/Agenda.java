package uniupo.gaborgalazzo.calendar.domain;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import uniupo.gaborgalazzo.calendar.exception.AppointmentCollisionException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
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

		return appointments
				.stream()
				.filter(
						appointment ->
								appointment.getWith().contains(with)
				)
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	public ArrayList<Appointment> findByDate(String date){

		return appointments
				.stream()
				.filter(
						appointment ->
								appointment.getDate().equals(date)
				)
				.collect(Collectors.toCollection(ArrayList<Appointment>::new));
	}

	public ArrayList<Appointment> getAll(){
		return  appointments
				.stream()
				.sorted()
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

	public void readAgenda(Reader reader){
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		appointments = gson.fromJson(reader, new TypeToken<ArrayList<Appointment>>(){}.getType());
	}


	@Override
	public Iterator<Appointment> iterator()
	{
		return getAll().iterator();
	}


}
