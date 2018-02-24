package implementation.organs.cancerstates;

import implementation.ICancerState;
import implementation.organs.Lung;

/**
 * Class for the S3 CancerState.
 */
public class S3 implements ICancerState {
	/**
	 * State S3 is the final state for a Cancerous Lung infection.
	 */
	public float percentageNeededToPromoteLung() {
		return 0;
	}
	/**
	 * State S3 is the final state for a Cancerous Lung infection.
	 */
	public float probabilityToInfectOtherCells() {
		return 0;
	}
	/**
	 * State S3 is the final state for a Cancerous Lung infection.
	 */
	public void promote(Lung lung){
	}
	/**
	 * State S3 is the final state for a Cancerous Lung infection.
	 */
	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		return 0;
	}
}