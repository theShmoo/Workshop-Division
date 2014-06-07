package at.pfahler.workshop.main.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import at.pfahler.workshop.main.dao.Participant;
import at.pfahler.workshop.main.dao.Workshop;
import at.pfahler.workshop.main.persistence.PersistanceLoader;
import at.pfahler.workshop.main.persistence.PersistenceLoaderCSVImpl;

/**
 * Implementation of the workshop service layer
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class WorkshopServiceImpl implements WorkshopService {

	private PersistanceLoader loader;
	private static WorkshopService service;

	/**
	 * Initializes the persistence
	 */
	public WorkshopServiceImpl() {
		loader = new PersistenceLoaderCSVImpl("files/workshops.csv");
	}

	@Override
	public SortedSet<Participant> getParticipants() {
		return loader.getParticipants();
	}

	@Override
	public Set<Workshop> getWorkshops() {
		return loader.getWorkshops();
	}

	/**
	 * Returns an instance of the workshop service
	 * 
	 * @return an instance of the workshop service
	 */
	public static WorkshopService getInstance() {
		if (service == null) {
			service = new WorkshopServiceImpl();
		}
		return service;
	}

	@Override
	public void diviseWorkshopsToParticipants(
			SortedSet<Participant> participants, Set<Workshop> workshops) {
		resetWorkshops(workshops);
		setInterests(participants);
		removeChosenWorkshopsFromInterests(participants);
		while (participantWithNoWorkshopExists(participants)) {
			boolean check = false;
			for (Workshop w : workshops) {
				if (!w.isFull() && w.isInterest()) {
					int i = 0;
					Participant p = w.getOrderedInterestedParticipants().get(i);
					while (isMoreInterestedInDifferentWorkshop(p, w)) {
						List<Participant> parts = w
								.getOrderedInterestedParticipants();
						if (++i < parts.size()) {
							p = parts.get(i);
						} else {
							// no fit so take the best fit for the workshop
							p = w.getOrderedInterestedParticipants().get(0);
							break;
						}
					}
					w.addParticipant(p);
					p.setWorkshop(w);
					removeParticipantFromEveryInterestList(p, workshops);
					check = true;

				}
			}
			if (!check) {
				System.err
						.println("Cant find a workshop for these participants:");
				for (Participant p : getParticipantsWithNoWorkshop(participants)) {
					System.err.printf("%-25s %-20s\n",
							p.getName(), p.getTrupp());
				}
				System.err.println("Free Workshops:");
				for (Workshop free : getFreeWorkshops(workshops)) {
					System.err.printf("%s (%d/%d), ", free.getName(), free
							.getParticipants().size(), free
							.getMaxParticipants());
				}
				System.err.print("\n");
				break;
			}
		}
	}

	private Set<Workshop> getFreeWorkshops(Set<Workshop> workshops) {
		Set<Workshop> set = new HashSet<Workshop>();
		for(Workshop w : workshops){
			if(!w.isFull()){
				set.add(w);
			}
		}
		return set;
	}

	private boolean isMoreInterestedInDifferentWorkshop(Participant p,
			Workshop w) {
		int vote = p.getVoteOfWorkshop(w);
		for (Map.Entry<Workshop, Integer> e : p.getVotes().entrySet()) {
			if (e.getValue() > vote) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SortedSet<Participant> getParticipantsWithNoWorkshop(
			SortedSet<Participant> participants) {
		SortedSet<Participant> set = new TreeSet<Participant>();
		for (Participant p : participants) {
			if (p.getWorkshop() == null) {
				set.add(p);
			}
		}
		return set;
	}

	@Override
	public boolean participantWithNoWorkshopExists(
			SortedSet<Participant> participants) {
		for (Participant p : participants) {
			if (p.getWorkshop() == null) {
				return true;
			}
		}
		return false;
	}

	private void removeParticipantFromEveryInterestList(
			Participant participant, Set<Workshop> workshops) {
		for (Workshop w : workshops) {
			w.getInterestedParticipants().remove(participant);
		}
	}

	@Override
	public void setInterests(SortedSet<Participant> participants) {
		for (Participant p : participants) {
			Set<Workshop> workshops = p.getVotes().keySet();
			for (Workshop w : workshops) {
				w.addInterest(p);
			}
		}
	}

	@Override
	public void removeChosenWorkshopsFromInterests(
			SortedSet<Participant> participants) {
		for (Participant p : participants) {
			if (p.getWorkshop() != null)
				p.getWorkshop().getInterestedParticipants().remove(p);
			p.setWorkshop(null);
		}
	}

	@Override
	public void resetWorkshops(Set<Workshop> workshops) {
		for (Workshop w : workshops) {
			w.reset();
		}
	}

}
