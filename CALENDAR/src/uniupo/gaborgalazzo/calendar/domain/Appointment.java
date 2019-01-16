package uniupo.gaborgalazzo.calendar.domain;


import com.sun.istack.internal.NotNull;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * The type Appointment.
 * <br>
 * All fields of this class are finals, so they cannot be modified.
 * @author Gabor Galazzo
 * @version 1.0.0
 */
public class Appointment implements Comparable<Appointment>
{

	private final String date;
	private final String time;
	private final int duration;
	private final String with;
	private final String where;

	/**
	 * The constant APPOINTMENT_DATE_FORMAT.
	 * <br>
	 * Used to check correct format for date string to store.<br><br>
	 * <b>Format: dd-MM-uuuu</b>
	 */
	public static final DateTimeFormatter APPOINTMENT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
	/**
	 * The constant APPOINTMENT_TIME_FORMAT.
	 * <br>
	 * Used to check correct format for time string to store.<br><br>
	 * <b>Format: HH-mm</b>
	 */
	public static final DateTimeFormatter APPOINTMENT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH-mm").withResolverStyle(ResolverStyle.STRICT);


	/**
	 * Instantiates a new Appointment.
	 * @param date     the date of the appointment matching {@link #APPOINTMENT_DATE_FORMAT}
	 * @param time     the time of the appointment matching {@link #APPOINTMENT_TIME_FORMAT}
	 * @param duration the duration of the appointment in minutes
	 * @param with     a string where you can specify persons involved in the appointment
	 * @param where    a string where you can specify the place of the appointment
	 * @throws DateTimeParseException    the <b>date</b> parameter doesn't match {@link #APPOINTMENT_DATE_FORMAT} or the <b>time</b> parameter does't match {@link #APPOINTMENT_TIME_FORMAT}
	 * @throws InvalidParameterException the duration is not a valid amount of minutes
	 */
	public Appointment(String date, String time, int duration, String with, String where) throws DateTimeParseException, InvalidParameterException
	{
		LocalDate.parse(date, APPOINTMENT_DATE_FORMAT); // used to validate date
		LocalTime.parse(time, APPOINTMENT_TIME_FORMAT); // used to validate time
		this.date = date;
		this.time = time;
		if (duration < 0)
			throw new InvalidParameterException("duration must be >= 0. " + duration + " received.");
		this.duration = duration;
		this.where = where;
		this.with = with;
	}


	/**
	 * Return a {@link LocalDateTime} representation of date and time of this appointment
	 * @return a LocalDateTime object representing date and time of this appointment
	 * @see LocalDateTime
	 */
	public LocalDateTime getDateTime()
	{
		LocalDate datePart = LocalDate.parse(getDate(), APPOINTMENT_DATE_FORMAT);
		LocalTime timePart = LocalTime.parse(getTime(), APPOINTMENT_TIME_FORMAT);
		return LocalDateTime.of(datePart, timePart);
	}

	/**
	 * Gets date.
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Gets time.
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * Gets duration.
	 * @return the duration
	 */
	public int getDuration()
	{
		return duration;
	}

	/**
	 * Gets with.
	 * @return the with
	 */
	public String getWith()
	{
		return with;
	}

	/**
	 * Gets where.
	 * @return the where
	 */
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
