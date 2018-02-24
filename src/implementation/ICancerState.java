package implementation;

/**
 * Interface for the cancer state.
 */
public interface ICancerState {
	/**
	 * Promotes the Lung to another State.
	 * @param lung The Lung.
	 */
	void promote(Lung lung);
	/**
	 * Gets the probability for a cancer infection on a Lung Cell.
	 * @param numberOfTarsInCell The number of tars present in the current cell.
	 * @return The probability for a cancer infection.
	 */
	float cancerProbabilityForLung(int numberOfTarsInCell);
	/**
	 * Gets the percentage of cancer infected cells needed to promote Lungs.
	 * @return Percentage of cancer infected cells needed to promote Lungs.
	 */
	float percentageNeededToPromoteLung();
	/**
	 * Gets the probability of spreading the infection from one infected cell to the neighbouring cells.
	 * @return Probability of spreading the infection.
	 */
	float probabilityToInfectOtherCells();
}