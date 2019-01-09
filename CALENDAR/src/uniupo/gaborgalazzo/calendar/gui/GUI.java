package uniupo.gaborgalazzo.calendar.gui;

import uniupo.gaborgalazzo.calendar.domain.Agenda;
import uniupo.gaborgalazzo.calendar.domain.Appointment;
import uniupo.gaborgalazzo.calendar.exception.AppointmentCollisionException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.List;

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
		System.out.println("|        8. Modify appointment           |");
		System.out.println("|                                        |");
		System.out.println("|        9. Exit                         |");
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
				modifyAppointment();
				break;
			case 9:
				exit();
				break;
		}
	}

	private void modifyAppointment()
	{
		System.out.println("MODIFY APPOINTMENT ACTION");
		List<Appointment> res = agenda.getAll();
		if (agenda.getAll().isEmpty())
		{
			System.out.println("Your agenda is empty.");
			return;
		}
		for (int i = 0; i < res.size(); i++)
		{
			System.out.println("----- (" + (i + 1) + ") -----");
			System.out.println(res.get(i));
			System.out.println("----- (" + (i + 1) + ") -----");
		}
		Appointment old = null;
		try
		{
			int index = Input.readInt("Which one you want to modify? (number)\n>");
			old = res.get(index);

			String date = Input.readString("Date (dd-MM-yyyy)[default: " + old.getDate() + "]: ");
			if (date.isEmpty())
				date = old.getDate();
			String time = Input.readString("Time (HH-mm)[default: " + old.getTime() + "]: ");
			if (time.isEmpty())
				time = old.getTime();
			int duration = old.getDuration();
			String durationS = Input.readString("Duration (min)[default: " + old.getDuration() + "]: ");
			if (!durationS.isEmpty())
				duration = Integer.parseInt(durationS);
			String with = Input.readString("With whom [default: " + old.getWith() + "]: ");
			if (with.isEmpty())
				with = old.getWith();
			String where = Input.readString("Where [default: " + old.getWhere() + "]: ");
			if (where.isEmpty())
				where = old.getWhere();

			Appointment appointment = new Appointment(date, time, duration, with, where);

			agenda.remove(old);
			agenda.add(appointment);

			System.out.println();
			System.out.println("APPOINTMENT REMOVED SUCCESSFULLY!");
			System.out.println();
			pressReturnToContinue();
		} catch (Exception e)
		{
			handleError("Can't modify appointment!", e);
		} catch (AppointmentCollisionException e)
		{
			try
			{
				agenda.add(old);
			} catch (AppointmentCollisionException e1)
			{
				throw new RuntimeException(e1);
			}
			System.out.println("Can't create appointment!");
			System.out.println("Your new appointment overlap this appointment:");
			System.out.println(e.getAppointment());
			pressReturnToContinue();
		}

		pressReturnToContinue();
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
			handleError("Can't read appointment!", e);

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
			handleError("Can't write appointment!", e);

		}
		pressReturnToContinue();
	}

	private void getAllAppointmentSorted()
	{
		System.out.println("GET ALL SORTED APPOINTMENT ACTION");
		for(Appointment appointment: agenda.getAll())
			System.out.println(appointment);
		if(agenda.getAll().isEmpty())
			System.out.println("Your agenda is empty.");
		pressReturnToContinue();
	}

	private void findAppointmentByDate()
	{

		System.out.println("FIND APPOINTMENT BY DATE ACTION");
		String date = Input.readString("Insert the date\n>");
		List<Appointment> res = agenda.findByDate(date);
		for(Appointment appointment: res)
			System.out.println(appointment);
		if(res.isEmpty())
			System.out.println("You have no appointment saved for "+date);
		pressReturnToContinue();
	}

	private void findAppointmentByWith()
	{

		System.out.println("FIND APPOINTMENT BY WITH ACTION");
		String needle = Input.readString("Insert needle\n>");
		List<Appointment> res = agenda.findByWith(needle);
		for(Appointment appointment: res)
			System.out.println(appointment);
		if(res.isEmpty())
			System.out.println("You have no appointment saved containing \""+needle+"\"");
		pressReturnToContinue();
	}

	private void removeAppointment()
	{
		System.out.println("REMOVE APPOINTMENT ACTION");
		List<Appointment> res = agenda.getAll();
		if(agenda.getAll().isEmpty())
		{
			System.out.println("Your agenda is empty.");
			return;
		}
		for(int i = 0; i<res.size(); i++)
		{
			System.out.println("----- ("+(i+1)+") -----");
			System.out.println(res.get(i));
			System.out.println("----- ("+(i+1)+") -----");
		}
		try
		{
			int index = Input.readInt("Which one you want to remove? (number)\n>");
			agenda.remove(res.get(index - 1));
			System.out.println();
			System.out.println("APPOINTMENT REMOVED SUCCESSFULLY!");
			System.out.println();
			pressReturnToContinue();
		}catch (Exception e){
			handleError("Can't remove appointment!", e);
		}

		pressReturnToContinue();

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
			handleError("Can't create appointment!", e);
		} catch (AppointmentCollisionException e)
		{
			System.out.println("Can't create appointment!");
			System.out.println("Your new appointment overlap this appointment:");
			System.out.println(e.getAppointment());
			pressReturnToContinue();
		}


	}

	private void handleError(String message, Exception e){
		System.out.println(message);
		System.out.println("Error: "+e.getMessage());
		pressReturnToContinue();
	}
}
