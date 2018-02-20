package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

public class NicotineFilter implements ICigaretteInhalationFilter {
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("N"))
				.collect(Collectors.toList());
	}
}