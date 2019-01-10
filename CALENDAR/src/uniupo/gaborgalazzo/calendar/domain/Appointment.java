package uniupo.gaborgalazzo.calendar.domain;



import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Appointment implements Comparable<Appointment>, Serializable
{
	private final String date;
	private final String time;
	private final int duration;
	private final String with;
	private final String where;

	public static final SimpleDateFormat APPOINTMENT_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	public static final SimpleDateFormat APPOINTMENT_TIME_FORMAT = new SimpleDateFormat("HH-mm");


	public Appointment(String date, String time, int duration, String with, String where) throws ParseException, InvalidParameterException
	{
		APPOINTMENT_DATE_FORMAT.setLenient(false);
		APPOINTMENT_TIME_FORMAT.setLenient(false);
		APPOINTMENT_DATE_FORMAT.parse(date);
		APPOINTMENT_TIME_FORMAT.parse(time);
		this.date = date;
		this.time = time;
		if(duration<0)
			throw new InvalidParameterException("duration must be >= 0. "+duration+" received.");
		this.duration = duration;
		this.where = where;
		this.with = with;
	}


	public Date getDDate(){

		try
		{
			Date datePart = APPOINTMENT_DATE_FORMAT.parse(getDate());
			Date timePart = APPOINTMENT_TIME_FORMAT.parse(getTime());
			return new Date(datePart.getTime() + timePart.getTime() + TimeUnit.DAYS.toMillis(1));
		} catch (ParseException e)
		{
			return null;
		}
	}

	public String getDate()
	{
		return date;
	}

	public String getTime()
	{
		return time;
	}

	public int getDuration()
	{
		return duration;
	}

	public String getWith()
	{
		return with;
	}

	public String getWhere()
	{
		return where;
	}


	@Override
	public int compareTo(@NotNull Appointment o)
	{
		if( getDDate().getTime() - o.getDDate().getTime()<0)
			return -1;
		if( getDDate().getTime() - o.getDDate().getTime()==0)
			return 0;
		return 1;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Appointment)) return false;
		Appointment that = (Appointment) o;
		return getDuration() == that.getDuration() &&
				Objects.equals(getDate(), that.getDate()) &&
				Objects.equals(getTime(), that.getTime()) &&
				Objects.equals(getWith(), that.getWith()) &&
				Objects.equals(getWhere(), that.getWhere());
	}

	@Override
	public int hashCode()
	{

		return Objects.hash(getDate(), getTime(), getDuration(), getWith(), getWhere());
	}

	@Override
	public String toString()
	{
		return "\n" +
				String.format("%-12s", "Date:") + getDate() + "\n"+
				String.format("%-12s", "Time:") + getTime() + "\n"+
				String.format("%-12s", "Duration:") + getDuration() + "\n"+
				String.format("%-12s", "With Whom:") + getWith() + "\n"+
				String.format("%-12s", "Where:") + getWhere() + "\n"+
				"\n";
	}
}
