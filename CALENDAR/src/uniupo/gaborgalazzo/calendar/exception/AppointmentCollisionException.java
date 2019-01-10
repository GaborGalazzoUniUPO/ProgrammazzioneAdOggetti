package uniupo.gaborgalazzo.calendar.exception;

import uniupo.gaborgalazzo.calendar.domain.Appointment;

public class AppointmentCollisionException extends Exception
{
	private final Appointment collision;

	public AppointmentCollisionException(Appointment collision)
	{
		this.collision = collision;
	}

	public Appointment getAppointment()
	{
		return collision;
	}

	public String getMessage(){

		return "Your new appointment overlap this appointment:\n"+
		collision;
	}
}
