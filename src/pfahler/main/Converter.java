package pfahler.main;

import dao.Trupp;

/**
 * Converter class
 * 
 * @author David Pfahler <david@pfahler.at>
 */
public class Converter {

	/**
	 * Converts a string to a trupp
	 * @param string that gets converted to the enum Trupp
	 * @return the enum Trupp
	 */
	public static Trupp convertStringToTrupp(String string) {
		switch(string){
		case "Rauhenstein": 
			return Trupp.RAUHENSTEIN;
		case "Raueneck":
			return Trupp.RAUHENECK;
		case "Königshöhle":
			return Trupp.KOENIGSHOEHLE;
		case "Hainburg":
			return Trupp.HAINBURG;
		default:
			return null;
		}
	}

}
