package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Describes a participant to the workshops who voted
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class Participant {

	private Date timeOfCreation;
	private String name;
	private Trupp trupp;
	private Map<Workshop, Integer> votes;
	private Workshop workshop1;
	private Workshop workshop2;

	/**
	 * Constructor of new participant
	 */
	public Participant() {
		votes = new HashMap<Workshop, Integer>();
	}

	/**
	 * @return the timeOfCreation
	 */
	public Date getTimeOfCreation() {
		return timeOfCreation;
	}

	/**
	 * @param timeOfCreation
	 *            the timeOfCreation to set
	 */
	public void setTimeOfCreation(Date timeOfCreation) {
		this.timeOfCreation = timeOfCreation;
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
	 * @return the trupp
	 */
	public Trupp getTrupp() {
		return trupp;
	}

	/**
	 * @param trupp
	 *            the trupp to set
	 */
	public void setTrupp(Trupp trupp) {
		this.trupp = trupp;
	}

	/**
	 * Add a new Vote
	 * 
	 * @param workshop
	 *            the workshop
	 * @param vote
	 *            the vote of the workshop
	 */
	public void addVote(Workshop workshop, int vote) {
		assert (vote > 0 && vote <= 10);
		getVotes().put(workshop, vote);
	}

	/**
	 * @return the votes
	 */
	public Map<Workshop, Integer> getVotes() {
		return votes;
	}

}
