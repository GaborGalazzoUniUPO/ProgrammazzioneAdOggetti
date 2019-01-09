package uniupo.gaborgalazzo.calendar.exception;

import uniupo.gaborgalazzo.calendar.domain.Appointment;

public class AppointmentCollisionException extends Throwable
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
}
