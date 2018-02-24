package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the Nicotine filter for the Cigarette Smoke.
 */
public class NicotineFilter implements ICigaretteInhalationFilter {
	/**
	 * Filters the characters from the cigarette smoke for the symbol representing the Nicotine: `N`.
	 * @param cigaretteSmoke The list representing the smoke of a cigarette.
	 * @return The filtered list of objects.
	 */
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("N"))
				.collect(Collectors.toList());
	}
}