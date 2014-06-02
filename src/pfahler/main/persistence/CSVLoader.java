package pfahler.main.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.Participant;
import dao.Trupp;
import dao.Workshop;
import pfahler.main.Converter;

/**
 * Class for loading the data and save it to DAOs
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class CSVLoader implements PersistanceLoader{

	private String csvFile;

	/**
	 * Constructor
	 * 
	 * @param csvFile
	 *            the path to the csvFile
	 */
	public CSVLoader(String csvFile) {
		this.csvFile = csvFile;
	}

	/**
	 * Reads the Comma separeted data from the csv file. The file is structured
	 * like:
	 * <ol>
	 * <li>first line with name of workshops (ordered)</li>
	 * <li>second line with the max capacity of participants (ordered)</li>
	 * <li>then the data follows:
	 * <ol>
	 * <li>first element is the timestamp</li>
	 * <li>second element is the name</li>
	 * <li>third element is the trupp</li>
	 * <li>then the votes follow</li>
	 * <ol></li>
	 * </ol>
	 * 
	 * @return the parsed participants
	 */
	public Set<Participant> getParticipants() {

		Set<Participant> participants = new HashSet<Participant>();
		List<Workshop> workshops = new ArrayList<Workshop>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int numberOfWorkshops = 0;
			// initialize Workshops:
			if ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				numberOfWorkshops = entry.length;
				for (int i = 0; i < numberOfWorkshops; ++i) {
					Workshop w = new Workshop();
					w.setName(entry[i]);
					workshops.add(w);
				}
			}
			if ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				numberOfWorkshops = entry.length;
				for (int i = 0; i < numberOfWorkshops; ++i) {
					int maxPart = Integer.parseInt(entry[i]);
					workshops.get(i).setMaxParticipants(maxPart);
				}
			}

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] entry = line.split(cvsSplitBy);
				Participant participant = new Participant();
				participant.setName(entry[1]);
				Trupp trupp = Converter.convertStringToTrupp(entry[2]);
				participant.setTrupp(trupp);
				for (int i = 0; i < numberOfWorkshops; ++i) {
					int vote = Integer.parseInt(entry[3 + i]);
					if (vote > 0) {
						participant.addVote(workshops.get(i), vote);
					}
				}
				participants.add(participant);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return participants;
	}

	/**
	 * Returns the workshops of the chosen csv file. The file is structured
	 * like:
	 * <ol>
	 * <li>first line with name of workshops (ordered)</li>
	 * <li>second line with the max capacity of participants (ordered)</li>
	 * <li>then the data follows:
	 * <ol>
	 * <li>first element is the timestamp</li>
	 * <li>second element is the name</li>
	 * <li>third element is the trupp</li>
	 * <li>then the votes follow</li>
	 * <ol></li>
	 * </ol>
	 * 
	 * @return the workshops
	 */
	public List<Workshop> getWorkshops() {
		List<Workshop> workshops = new ArrayList<Workshop>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			int numberOfWorkshops = 0;
			// initialize Workshops:
			if ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				numberOfWorkshops = entry.length;
				for (int i = 0; i < numberOfWorkshops; ++i) {
					Workshop w = new Workshop();
					w.setName(entry[i]);
					workshops.add(w);
				}
			}
			if ((line = br.readLine()) != null) {
				String[] entry = line.split(cvsSplitBy);
				numberOfWorkshops = entry.length;
				for (int i = 0; i < numberOfWorkshops; ++i) {
					int maxPart = Integer.parseInt(entry[i]);
					workshops.get(i).setMaxParticipants(maxPart);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return workshops;
	}
}
