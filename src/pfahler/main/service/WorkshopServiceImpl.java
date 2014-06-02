package pfahler.main.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pfahler.main.dao.Participant;
import pfahler.main.dao.Workshop;
import pfahler.main.persistence.CSVLoader;
import pfahler.main.persistence.PersistanceLoader;

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
		loader = new CSVLoader("files/workshops.csv");
	}

	@Override
	public Set<Participant> getParticipants() {
		return loader.getParticipants();
	}

	@Override
	public Set<Workshop> getWorkshops() {
		return loader.getWorkshops();
	}

	/**
	 * Returns the favorite workshop of the participant
	 * 
	 * @param participant
	 *            the participant
	 * @return the favorite workshop of the participant
	 */
	public Workshop getFavoriteWorkshop(Participant participant) {
		Map.Entry<Workshop, Integer> e = getHighestVoteWorkshop(participant);
		Workshop w = e.getKey();
		for (Participant p : w.getOrderedInterestedParticipants()) {
			if (p.getVoteOfWorkshop(w) > e.getValue()) {
				participant.getVotes().remove(w);
				return getFavoriteWorkshop(participant);
			}
		}
		if (!w.isFull()) {
			return w;
		} else {
			participant.getVotes().remove(w);
			return getFavoriteWorkshop(participant);
		}
	}

	private Map.Entry<Workshop, Integer> getHighestVoteWorkshop(
			Participant participant) {
		Map.Entry<Workshop, Integer> best = null;
		for (Map.Entry<Workshop, Integer> entry : participant.getVotes()
				.entrySet()) {
			if (best == null || entry.getValue() > best.getValue()) {
				best = entry;
			}
		}
		return best;
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
	public void diviseWorkshopsToParticipants(Set<Participant> participants,
			Set<Workshop> workshops) {
		setInterests(participants);
		while (participantWithNoWorkshopExists(participants)) {
			boolean check = false;
			for (Workshop w : workshops) {
				if (!w.isFull() && w.isInterest()) {
					Participant p = w.getOrderedInterestedParticipants().get(0);
					w.addParticipant(p);
					p.setWorkshop(w);
					removeParticipantFromEveryInterestList(p, workshops);
					check = true;
				}
			}
			if(!check){
				System.out.println("Cant find workshop for them!");
				for(Participant p : getParticipantsWithNoWorkshop(participants)){
					System.out.println(p);
				}
				break;
			}
		}
	}

	private Set<Participant> getParticipantsWithNoWorkshop(Set<Participant> participants) {
		Set<Participant> set = new HashSet<Participant>();
		for(Participant p : participants){
			if(p.getWorkshop() == null){
				set.add(p);
			}
		}
		return set;
	}

	private boolean participantWithNoWorkshopExists(
			Set<Participant> participants) {
		for(Participant p : participants){
			if(p.getWorkshop() == null){
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

	/**
	 * Set the interested participants for the workshops
	 * 
	 * @param participants
	 *            the participants
	 */
	private void setInterests(Set<Participant> participants) {
		for (Participant p : participants) {
			Set<Workshop> workshops = p.getVotes().keySet();
			for (Workshop w : workshops) {
				w.addInterest(p);
			}
		}
	}

}
