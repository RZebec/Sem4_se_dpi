package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the Tars filter for the Cigarette Smoke.
 */
public class TarsFilter implements ICigaretteInhalationFilter {
	/**
	 * Filters the characters from the cigarette smoke for the symbol representing the Tars: `T`.
	 * @param cigaretteSmoke The list representing the smoke of a cigarette.
	 * @return The filtered list of objects.
	 */
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("T"))
				.collect(Collectors.toList());
	}
}