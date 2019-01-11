package uniupo.gaborgalazzo.calendar.domain;


import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

public class Appointment implements Comparable<Appointment>, Serializable
{
	private final String date;
	private final String time;
	private final int duration;
	private final String with;
	private final String where;

	public static final DateTimeFormatter APPOINTMENT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
	public static final DateTimeFormatter APPOINTMENT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH-mm").withResolverStyle(ResolverStyle.STRICT);


	public Appointment(String date, String time, int duration, String with, String where) throws DateTimeParseException, InvalidParameterException
	{
		LocalDate.parse(date, APPOINTMENT_DATE_FORMAT);
		LocalTime.parse(time, APPOINTMENT_TIME_FORMAT);
		this.date = date;
		this.time = time;
		if (duration < 0)
			throw new InvalidParameterException("duration must be >= 0. " + duration + " received.");
		this.duration = duration;
		this.where = where;
		this.with = with;
	}


	public LocalDateTime getDateTime()
	{
		LocalDate datePart = LocalDate.parse(getDate(), APPOINTMENT_DATE_FORMAT);
		LocalTime timePart = LocalTime.parse(getTime(), APPOINTMENT_TIME_FORMAT);
		return LocalDateTime.of(datePart, timePart);
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
		return getDateTime().compareTo(o.getDateTime());
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
				String.format("%-12s", "Date:") + getDate() + "\n" +
				String.format("%-12s", "Time:") + getTime() + "\n" +
				String.format("%-12s", "Duration:") + getDuration() + "\n" +
				String.format("%-12s", "With Whom:") + getWith() + "\n" +
				String.format("%-12s", "Where:") + getWhere() + "\n" +
				"\n";
	}
}
