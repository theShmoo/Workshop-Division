package pfahler.main;

import java.util.Set;

import pfahler.main.dao.Participant;
import pfahler.main.dao.Workshop;
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

		WorkshopService service = WorkshopServiceImpl.getInstance();

		Set<Participant> participants = service.getParticipants();
		Set<Workshop> workshops = service.getWorkshops();
		service.diviseWorkshopsToParticipants(participants, workshops);
		printParticipants(participants);
	}

	private static void printParticipants(Set<Participant> participants) {
		for (Participant p : participants) {
			String w = "noch nicht eingeteilt";
			int v = 0;
			if(p.getWorkshop() != null){
				w = p.getWorkshop().getName();
				v = p.getVotes().get(p.getWorkshop());
			}
			System.out.printf("Participant = %s (%s), %s (%d)\n", p.getName(),p.getTrupp(), w, v);
		}
	}

}
