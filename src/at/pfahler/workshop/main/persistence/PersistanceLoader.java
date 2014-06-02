package at.pfahler.workshop.main.persistence;

import java.util.Set;
import java.util.SortedSet;

import at.pfahler.workshop.main.dao.Participant;
import at.pfahler.workshop.main.dao.Workshop;

/**
 * Interface for the service layer of the workshop
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public interface PersistanceLoader {

	/**
	 * Returns the saved participants
	 * 
	 * @return the saved participants
	 */
	SortedSet<Participant> getParticipants();

	/**
	 * Returns the saved workshops
	 * 
	 * @return the saved workshops
	 */
	Set<Workshop> getWorkshops();

}
