package uniupo.gaborgalazzo.calendar.gui;

import uniupo.gaborgalazzo.calendar.domain.Agenda;
import uniupo.gaborgalazzo.calendar.domain.Appointment;
import uniupo.gaborgalazzo.calendar.exception.AppointmentCollisionException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;

public class GUI
{
	private Agenda agenda;

	public GUI(){
		this.agenda = new Agenda();
	}

	private void pressReturnToContinue(){
		System.out.println();
		Input.readString("Press RETURN to continue...");
	}


	public void start()
	{
		System.out.println("GABOR GALAZZO 20024195 2018-2019");
		System.out.println("OBJECT ORIENTED PROGRAMMING - AGENDA");
		System.out.println();
		pressReturnToContinue();
		while(true)
			performMenu();
	}

	private void performMenu()
	{
		System.out.println("__________________________________________");
		System.out.println("|                                        |");
		System.out.println("|   MENU SELECTION AGENDA                |");
		System.out.println("|                                        |");
		System.out.println("|   Options:                             |");
		System.out.println("|                                        |");
		System.out.println("|        1. Add appointment              |");
		System.out.println("|        2. Remove appointment           |");
		System.out.println("|        3. Find appointment by WITH     |");
		System.out.println("|        4. Find appointment by DATE     |");
		System.out.println("|        5. Get all appointment sorted   |");
		System.out.println("|        6. Save appointment to file     |");
		System.out.println("|        7. Load appointment from file   |");
		System.out.println("|                                        |");
		System.out.println("|        8. Exit                         |");
		System.out.println("|________________________________________|");
		int option = 0;
		try
		{
			option = Input.readInt(" Select option: ");
		}catch (NumberFormatException ignored){}
		switch (option){
			case 1:
				addAppointment();
				break;
			case 2:
				removeAppointment();
				break;
			case 3:
				findAppointmentByWith();
				break;
			case 4:
				findAppointmentByDate();
				break;
			case 5:
				getAllAppointmentSorted();
				break;
			case 6:
				saveAppointment();
				break;
			case 7:
				loadAppointment();
				break;
			case 8:
				exit();
				break;
		}
	}

	private void exit()
	{
		System.out.println("BYE!");
		System.exit(0);
	}

	private void loadAppointment()
	{
		System.out.println("LOAD APPOINTMENT ACTION");
		String filename = Input.readString("Insert the path of the file to load\n>");
		try
		{
			FileReader fileReader = new FileReader(filename);
			agenda.readAgenda(fileReader);
			fileReader.close();
			System.out.println("APPOINTMENT LOADED SUCCESSFULLY from "+filename+"!");
		} catch (IOException e)
		{
			System.out.println("Can't load appointment!");
			System.out.println("Error: "+e.getMessage());

		}
		pressReturnToContinue();
	}

	private void saveAppointment()
	{
		System.out.println("SAVE APPOINTMENT ACTION");
		String filename = Input.readString("Insert the path to the fail to save into\n>");
		try
		{
			FileWriter fileWriter = new FileWriter(filename);
			agenda.writeAgenda(fileWriter);
			fileWriter.close();
			System.out.println("APPOINTMENT SAVED SUCCESSFULLY in "+filename+"!");
		} catch (IOException e)
		{
			System.out.println("Can't save appointment!");
			System.out.println("Error: "+e.getMessage());

		}
		pressReturnToContinue();
	}

	private void getAllAppointmentSorted()
	{
		for(Appointment appointment: agenda.getAll())
			System.out.println(appointment);
		if(agenda.getAll().isEmpty())
			System.out.println("Your agenda is empty.");
		pressReturnToContinue();
	}

	private void findAppointmentByDate()
	{

	}

	private void findAppointmentByWith()
	{

	}

	private void removeAppointment()
	{

	}

	private void addAppointment()
	{
		System.out.println("ADD APPOINTMENT ACTION");
		String date = Input.readString("Date (dd-MM-yyyy): ");
		String time = Input.readString("Time (HH-mm): ");
		int duration = Input.readInt("Duration (min): ");
		String with = Input.readString("With whom: ");
		String where = Input.readString("Where: ");
		try
		{
			Appointment appointment = new Appointment(date, time, duration, with, where);
			agenda.add(appointment);

			System.out.println();
			System.out.println("APPOINTMENT ADDED SUCCESSFULLY!");
			System.out.println();
			pressReturnToContinue();

		} catch (ParseException | InvalidParameterException e)
		{
			System.out.println("Can't create appointment!");
			System.out.println("Error: "+e.getMessage());
			pressReturnToContinue();

		} catch (AppointmentCollisionException e)
		{
			System.out.println("Can't create appointment!");
			System.out.println("Your new appointment overlap this appointment:");
			System.out.println(e.getAppointment());
			pressReturnToContinue();
		}


	}
}
