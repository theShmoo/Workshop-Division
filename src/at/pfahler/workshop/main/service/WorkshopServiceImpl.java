package at.pfahler.workshop.main.service;

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
	public void diviseWorkshopsToParticipants(SortedSet<Participant> participants,
			Set<Workshop> workshops) {
		resetWorkshops(workshops);
		setInterests(participants);
		removeChosenWorkshopsFromInterests(participants);
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
				System.err.println("Cant find workshop for them!");
				for(Participant p : getParticipantsWithNoWorkshop(participants)){
					System.err.println(p);
				}
				break;
			}
		}
	}

	@Override
	public SortedSet<Participant> getParticipantsWithNoWorkshop(SortedSet<Participant> participants) {
		SortedSet<Participant> set = new TreeSet<Participant>();
		for(Participant p : participants){
			if(p.getWorkshop() == null){
				set.add(p);
			}
		}
		return set;
	}

	@Override
	public boolean participantWithNoWorkshopExists(
			SortedSet<Participant> participants) {
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
		for(Participant p : participants){
			if(p.getWorkshop() != null)
				p.getWorkshop().getInterestedParticipants().remove(p);
			p.setWorkshop(null);
		}
	}

	@Override
	public void resetWorkshops(Set<Workshop> workshops) {
		for(Workshop w : workshops){
			w.reset();
		}
	}

}
