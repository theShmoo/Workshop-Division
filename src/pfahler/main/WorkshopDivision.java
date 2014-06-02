package pfahler.main;

import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.Participant;
import dao.Workshop;
import pfahler.main.service.WorkshopService;
import pfahler.main.service.WorkshopServiceImpl;

/**
 * The main class of the project
 * 
 * Starts the calculation
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class WorkshopDivision {

	/**
	 * Starts the workshop
	 * 
	 * @param args
	 *            args[0] is the location of the csv file with the data of the
	 *            voting of the workshops args[1] is the location of the
	 *            outputfile
	 */
	public static void main(String[] args) {

		WorkshopService service = new WorkshopServiceImpl();
		
		Set<Participant> participants = service.getParticipants();
		List<Workshop> workshops = service.getWorkshops();
		service.printFavoriteWorkshops(participants);
	}

}
