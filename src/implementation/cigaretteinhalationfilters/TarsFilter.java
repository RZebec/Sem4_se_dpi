package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

public class TarsFilter implements ICigaretteInhalationFilter {
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol.equals("T"))
				.collect(Collectors.toList());
	}
}