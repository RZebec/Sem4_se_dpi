package implementation.organs.cancerstates;

import implementation.ICancerState;
import implementation.organs.Lung;

/**
 * Class for the S1 CancerState.
 */
public class S1 implements ICancerState {
	/**
	 * Gets the percentage of cancer infected cells needed to promote Lungs to State S2.
	 * @return Percentage of cancer infected cells needed to promote Lungs.
	 */
	public float percentageNeededToPromoteLung() {
		return 0.20f;
	}
	/**
	 * Gets the probability of spreading the infection from one infected cell to the neighbouring cells.
	 * @return Probability of spreading the infection.
	 */
	public float probabilityToInfectOtherCells() {
		return 0.10f;
	}
	/**
	 * Promotes the Lung to State S2.
	 * @param lung The Lung.
	 */
	public void promote(Lung lung){
		lung.setCancerState(new S2());
		System.out.println(lung.getLungPosition() + " Lung reached cancer state S2!");
	}
	/**
	 * Gets the probability for a cancer infection on a Lung Cell.
	 * @param numberOfTarsInCell The number of tars present in the current cell.
	 * @return The probability for a cancer infection.
	 */
	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0f;
			case 1: return 0.15f;
			case 2: return 0.35f;
			case 3: return 0.75f;
		}
		return 0;
	}
}