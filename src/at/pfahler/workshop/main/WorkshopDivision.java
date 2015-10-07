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
 * @author David Pfahler
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
		printWorkshops(workshops);

		System.out.println("Day 2:");
		service.diviseWorkshopsToParticipants(participants, workshops);
		printParticipants(participants);
		printWorkshops(workshops);

	}

	/**
	 * Prints the participants onto stdout
	 * 
	 * @param participants
	 *            the participants to print
	 */
	private static void printParticipants(SortedSet<Participant> participants) {
		System.out.printf("%-25s%-20s%-38s%-4s\n", "Name", "Trupp", "Workshop",
				"Vote");
		System.out
				.println("----------------------------------------------------------------------------------------");
		for (Participant p : participants) {
			String w = "noch nicht eingeteilt";
			int v = 0;
			if (p.getWorkshop() != null) {
				w = p.getWorkshop().getName();
				v = p.getVotes().get(p.getWorkshop());
			}
			System.out.printf("%-25s%-20s%-38s(%d)\n", p.getName(),
					p.getTrupp(), w, v);
		}
	}

	private static void printWorkshops(Set<Workshop> workshops) {
		for (Workshop w : workshops) {
			Set<Participant> participants = w.getParticipants();
			System.out.printf("Workshop: %s (%d/%d)\n", w.getName(),
					participants.size(), w.getMaxParticipants());
			for (Participant p : participants) {
				System.out.printf("\t%-20s %-15s (%d)\n", p.getName(),
						p.getTrupp(), p.getVoteOfWorkshop(w));
			}
		}
	}

}
