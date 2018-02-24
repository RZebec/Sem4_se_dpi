package implementation.organs.cancerstates;

import implementation.ICancerState;
import implementation.organs.Lung;

/**
 * Class for the S0 CancerState.
 */
public class S0 implements ICancerState {
	/**
	 * Gets the percentage of cancer infected cells needed to promote Lungs to State S1.
	 * @return Percentage of cancer infected cells needed to promote Lungs.
	 */
	public float percentageNeededToPromoteLung() {
		return 0.10f;
	}
	/**
	 * Gets the probability of spreading the infection from one infected cell to the neighbouring cells.
	 * @return Probability of spreading the infection.
	 */
	public float probabilityToInfectOtherCells() {
		return 0.05f;
	}
	/**
	 * Promotes the Lung to State S1.
	 * @param lung The Lung.
	 */
	public void promote(Lung lung){
		lung.setCancerState(new S1());
		System.out.println(lung.getLungPosition() + " Lung is reaching cancer state S1!");
	}
	/**
	 * Gets the probability for a cancer infection on a Lung Cell.
	 * @param numberOfTarsInCell The number of tars present in the current cell.
	 * @return The probability for a cancer infection.
	 */
	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0f;
			case 1: return 0.10f;
			case 2: return 0.30f;
			case 3: return 0.70f;
		}
		return 0;
	}
}