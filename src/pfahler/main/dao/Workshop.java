package pfahler.main.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describes a workshop
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class Workshop {
	private String name;
	private int maxParticipants;
	private Set<Participant> participants;
	private Set<Participant> interestedParticipants;

	/**
	 * Constructor for a Workshop no fields get set
	 */
	public Workshop() {
		participants = new HashSet<Participant>();
		interestedParticipants = new HashSet<Participant>();
	}

	/**
	 * @return the maxParticipants
	 */
	public int getMaxParticipants() {
		return maxParticipants;
	}

	/**
	 * @param maxParticipants
	 *            the maxParticipants to set
	 */
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns <code>true</code> if the workshop has no places left
	 * 
	 * @return <code>true</code> if the workshop has no places left
	 */
	public boolean isFull() {
		return participants.size() > maxParticipants;
	}

	/**
	 * Add interested Participant to the workshop
	 * 
	 * @param p
	 *            the participant to get added
	 */
	public void addInterest(Participant p) {
		interestedParticipants.add(p);
	}

	/**
	 * Returns the interested Participants for this workshop ordered by votes
	 * 
	 * @return the interested Participants for this workshop ordered by votes
	 */
	public List<Participant> getOrderedInterestedParticipants() {
		List<Participant> l = new ArrayList<Participant>();
		for (Participant p : interestedParticipants) {
			int vote = p.getVotes().get(this);
			int pos = 0;
			while (l.size() > pos && vote < l.get(pos).getVotes().get(this)) {
				pos++;
			}
			l.add(pos, p);
		}
		return l;
	}

	/**
	 * Returns the interested Participants for this workshop
	 * 
	 * @return the interested Participants for this workshop
	 */
	public Set<Participant> getInterestedParticipants() {
		return interestedParticipants;
	}

	/**
	 * Add {@link Participant} to the workshop
	 * 
	 * @param p
	 *            the participant to get added
	 */
	public void addParticipant(Participant p) {
		participants.add(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Workshop [name=" + name + ", maxParticipants="
				+ maxParticipants + "]";
	}

	/**
	 * Returns true if there are still participants intereseted in this workshop
	 * 
	 * @return true if there are still participants intereseted in this workshop
	 */
	public boolean isInterest() {
		return interestedParticipants.size() > 0;
	}

}
