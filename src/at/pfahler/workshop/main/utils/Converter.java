package at.pfahler.workshop.main.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import at.pfahler.workshop.main.dao.Trupp;

/**
 * Converter class
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class Converter {

	/**
	 * Converts a string to a trupp
	 * 
	 * @param string
	 *            that gets converted to the enum Trupp
	 * @return the enum Trupp
	 */
	public static Trupp convertStringToTrupp(String string) {
		switch (string) {
		case "Rauhenstein":
			return Trupp.RAUHENSTEIN;
		case "Rauheneck":
			return Trupp.RAUHENECK;
		case "Königshöhle":
			return Trupp.KOENIGSHOEHLE;
		case "Hainburg":
			return Trupp.HAINBURG;
		default:
			return null;
		}
	}

	/**
	 * Converts a string to a date object
	 * 
	 * @param string
	 *            the string to get converted should have the format:
	 *            "MM/dd/yyyy HH:mm:ss"
	 * @return the date
	 */
	public static Date convertStringToDate(String string) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH)
					.parse(string);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
