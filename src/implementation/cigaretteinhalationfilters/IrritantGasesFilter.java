package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;

import java.util.List;
import java.util.stream.Collectors;

public class IrritantGasesFilter implements ICigaretteInhalationFilter {
	public List<String> filterObjects(List<String> cigaretteSmoke){
		return cigaretteSmoke.stream()
				.filter(symbol -> symbol == "G")
				.collect(Collectors.toList());
	}
}