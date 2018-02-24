package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the Carbon Monoxide filter for the Cigarette Smoke.
 */
public class CarbonMonoxideFilter implements ICigaretteInhalationFilter {
	/**
	 * Filters the characters from the cigarette smoke for the symbol representing the Carbon Monoxide: `C`.
	 * @param cigaretteSmoke The list representing the smoke of a cigarette.
	 * @return The filtered list of objects.
	 */
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("C"))
				.collect(Collectors.toList());
	}
}