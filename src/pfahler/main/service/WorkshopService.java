package pfahler.main.service;

import java.util.Set;

import pfahler.main.dao.Participant;
import pfahler.main.dao.Workshop;

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
	Set<Workshop> getWorkshops();

	/**
	 * Give each participant a workshop
	 * @param participants the participants
	 * @param workshops the workshops
	 */
	void diviseWorkshopsToParticipants(Set<Participant> participants,
			Set<Workshop> workshops);

}
