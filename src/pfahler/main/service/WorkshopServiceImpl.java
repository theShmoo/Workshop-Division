package pfahler.main.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import dao.Participant;
import dao.Workshop;
import pfahler.main.persistence.CSVLoader;
import pfahler.main.persistence.PersistanceLoader;

/**
 * Implementation of the workshop service layer
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class WorkshopServiceImpl implements WorkshopService{
	
	private PersistanceLoader loader;
	
	/**
	 * Initializes the persistence
	 */
	public WorkshopServiceImpl() {
		loader = new CSVLoader(
				"F:/Local Documents/Workspace/Workshop Division/files/workshops.csv");
	}

	@Override
	public Set<Participant> getParticipants() {
		return loader.getParticipants();
	}

	@Override
	public List<Workshop> getWorkshops() {
		return loader.getWorkshops();
	}
	
	/**
	 * Returns the favorite workshop of the participant
	 * 
	 * @param participant the participant
	 * @return the favorite workshop of the participant
	 */
	public Entry<Workshop, Integer> getFavoriteWorkshop(Participant participant) {
		Map.Entry<Workshop, Integer> bestFit = null;
		int max = 0;
		for (Map.Entry<Workshop, Integer> entry : participant.getVotes().entrySet()) {
			if (entry.getValue() > max) {
				if (!entry.getKey().isFull()) {
					max = entry.getValue();
					bestFit = entry;
				}
			}
		}
		if (bestFit == null) {
			// TODO
		}
		return bestFit;
	}

	@Override
	public void printFavoriteWorkshops(Set<Participant> participants) {
		for (Participant p : participants) {
			Map.Entry<Workshop, Integer> bestFit = getFavoriteWorkshop(p);
			System.out.println("Participant = " + p.getName() + ", Key = "
					+ bestFit.getKey().getName() + ", Value = "
					+ bestFit.getValue());
		}
	}

}
