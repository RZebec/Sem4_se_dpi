package implementation;
import java.util.List;

/**
 * Interface for the Cigarette Inhalation Filter.
 */
public interface ICigaretteInhalationFilter {
	/**
	 * Filters the objects from the cigarette smoke.
	 * @param cigaretteSmoke The list representing the smoke of a cigarette.
	 * @return The filtered list of objects.
	 */
	List<String> filterObjects(List<String> cigaretteSmoke);
}