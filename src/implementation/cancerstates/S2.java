package implementation.cancerstates;

import implementation.ICancerState;
import implementation.Lung;

/**
 * Class for the S2 CancerState.
 */
public class S2 implements ICancerState {
	/**
	 * Gets the percentage of cancer infected cells needed to promote Lungs to State S3.
	 * @return Percentage of cancer infected cells needed to promote Lungs.
	 */
	public float percentageNeededToPromoteLung() {
		return 0.30f;
	}
	/**
	 * Gets the probability of spreading the infection from one infected cell to the neighbouring cells.
	 * @return Probability of spreading the infection.
	 */
	public float probabilityToInfectOtherCells() {
		return 0.20f;
	}
	/**
	 * Promotes the Lung to State S3.
	 * @param lung The Lung.
	 */
	public void promote(Lung lung){
		lung.setCancerState(new S3());
		System.out.println(lung.getLungPosition() + " Lung reached cancer state S3!");
	}
	/**
	 * Gets the probability for a cancer infection on a Lung Cell.
	 * @param numberOfTarsInCell The number of tars present in the current cell.
	 * @return The probability for a cancer infection.
	 */
	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0f;
			case 1: return 0.25f;
			case 2: return 0.50f;
			case 3: return 0.80f;
		}
		return 0;
	}
}