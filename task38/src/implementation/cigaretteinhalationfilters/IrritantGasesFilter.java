package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the Irritant Gases filter for the Cigarette Smoke.
 */
public class IrritantGasesFilter implements ICigaretteInhalationFilter {
	/**
	 * Filters the characters from the cigarette smoke for the symbol representing the Irritant Gases: `G`.
	 * @param cigaretteSmoke The list representing the smoke of a cigarette.
	 * @return The filtered list of objects.
	 */
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("G"))
				.collect(Collectors.toList());
	}
}