package pfahler.main.persistence;

import java.util.List;
import java.util.Set;

import dao.Participant;
import dao.Workshop;

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
	Set<Participant> getParticipants();

	/**
	 * Returns the saved workshops
	 * 
	 * @return the saved workshops
	 */
	List<Workshop> getWorkshops();

}
