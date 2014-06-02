package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a workshop
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class Workshop {
	private String name;
	private int maxParticipants;
	private List<Participant> participants;

	/**
	 * Constructor for a Workshop no fields get set
	 */
	public Workshop() {
		participants = new ArrayList<Participant>();
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
}
