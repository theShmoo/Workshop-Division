package at.pfahler.workshop.main.service;

import java.util.Set;
import java.util.SortedSet;

import at.pfahler.workshop.main.dao.Participant;
import at.pfahler.workshop.main.dao.Workshop;

/**
 * Interface for the service layer of the {@link Workshop}
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public interface WorkshopService {

	/**
	 * Returns the {@link Participant}s loaded by the service layer
	 * 
	 * @return the participants loaded by the service layer
	 */
	SortedSet<Participant> getParticipants();

	/**
	 * Returns the {@link Workshop}s loaded by the service layer
	 * 
	 * @return the workshops loaded by the service layer
	 */
	Set<Workshop> getWorkshops();

	/**
	 * Give each {@link Participant} a {@link Workshop}
	 * 
	 * @param participants
	 *            the participants
	 * @param workshops
	 *            the workshops
	 */
	void diviseWorkshopsToParticipants(SortedSet<Participant> participants,
			Set<Workshop> workshops);

	/**
	 * Returns all {@link Participant}s which don't have a {@link Workshop} yet
	 * 
	 * @param participants
	 *            the participants to check
	 * @return all participants which don't have a workshop yet
	 */
	SortedSet<Participant> getParticipantsWithNoWorkshop(
			SortedSet<Participant> participants);

	/**
	 * Check if at least one of the given {@link Participant}s don't have a
	 * {@link Workshop} yet
	 * 
	 * @param participants
	 *            the participants to check
	 * @return <code>true</code> if at least one of the given participants don't
	 *         have a workshop yet
	 */
	boolean participantWithNoWorkshopExists(SortedSet<Participant> participants);

	/**
	 * Set the interested {@link Participant}s for the {@link Workshop}s
	 * 
	 * @param participants
	 *            the participants
	 */
	void setInterests(SortedSet<Participant> participants);

	/**
	 * Remove already chosen {@link Workshop}s from the interest list from the
	 * workshops
	 * 
	 * @param participants
	 */
	void removeChosenWorkshopsFromInterests(SortedSet<Participant> participants);

	/**
	 * Resets the {@link Participant} lists for the {@link Workshop}s
	 * 
	 * @param workshops
	 *            the workshops to get reseted
	 */
	void resetWorkshops(Set<Workshop> workshops);

}
