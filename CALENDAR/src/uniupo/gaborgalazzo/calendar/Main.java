package uniupo.gaborgalazzo.calendar;

import uniupo.gaborgalazzo.calendar.gui.GUI;

/**
 * Main Class to run a GUI to for {@link uniupo.gaborgalazzo.calendar.domain.Agenda}
 */
public class Main
{
	/**
	 * Instantiate a console GUI to interact with the agenda
	 *
	 * @param args NO ARGS REQUIRED
	 */
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		gui.start();
	}

}
