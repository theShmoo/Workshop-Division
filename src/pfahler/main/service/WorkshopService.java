package pfahler.main.service;

import java.util.List;
import java.util.Set;

import dao.Participant;
import dao.Workshop;

/**
 * Interface for the service layer of the workshop
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public interface WorkshopService {

	/**
	 * Returns the participants loaded by the service layer
	 * @return the participants loaded by the service layer
	 */
	Set<Participant> getParticipants();

	/**
	 * Returns the workshops loaded by the service layer
	 * @return the workshops loaded by the service layer
	 */
	List<Workshop> getWorkshops();

	/**
	 * Prints the favorite workshops of the participants
	 * @param participants the participants
	 */
	void printFavoriteWorkshops(Set<Participant> participants);

}
