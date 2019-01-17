package uniupo.gaborgalazzo.calendar.exception;

import uniupo.gaborgalazzo.calendar.domain.Appointment;


/**
 * Exception to handlewhen you are trying to insert two overlapping appointment in the same agenda
 */
public class AppointmentOverlapException extends Exception
{
	private final Appointment collision;

	/**
	 * Instantiates a new Appointment overlap exception.
	 *
	 * @param collision the overlapping appointment in the agenda
	 */
	public AppointmentOverlapException(Appointment collision)
	{
		this.collision = collision;
	}

	/**
	 * Gets appointment.
	 *
	 * @return the appointment
	 */
	public Appointment getAppointment()
	{
		return collision;
	}

	public String getMessage(){

		return "Your new appointment overlap this appointment:\n"+
		collision;
	}

}
