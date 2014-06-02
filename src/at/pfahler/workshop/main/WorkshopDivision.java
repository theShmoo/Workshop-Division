package at.pfahler.workshop.main;

import java.util.Set;
import java.util.SortedSet;

import at.pfahler.workshop.main.dao.Participant;
import at.pfahler.workshop.main.dao.Workshop;
import at.pfahler.workshop.main.service.WorkshopService;
import at.pfahler.workshop.main.service.WorkshopServiceImpl;

/**
 * The main class of the project
 * 
 * Starts the calculation
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class WorkshopDivision {

	/**
	 * Starts the Workshop
	 * 
	 * @param args
	 *            no arguments are required for this program
	 */
	public static void main(String[] args) {

		WorkshopService service = WorkshopServiceImpl.getInstance();

		SortedSet<Participant> participants = service.getParticipants();
		Set<Workshop> workshops = service.getWorkshops();
		
		System.out.println("Day 1:");
		service.diviseWorkshopsToParticipants(participants, workshops);
		printParticipants(participants);
		
		System.out.println("Day 2:");	
		service.diviseWorkshopsToParticipants(participants, workshops);
		printParticipants(participants);
		
	}

	/**
	 * Prints the participants onto stdout
	 * 
	 * @param participants
	 *            the participants to print
	 */
	private static void printParticipants(Set<Participant> participants) {
		for (Participant p : participants) {
			String w = "noch nicht eingeteilt";
			int v = 0;
			if (p.getWorkshop() != null) {
				w = p.getWorkshop().getName();
				v = p.getVotes().get(p.getWorkshop());
			}
			System.out.printf("Participant = %s (%s), %s (%d)\n", p.getName(),
					p.getTrupp(), w, v);
		}
	}

}
