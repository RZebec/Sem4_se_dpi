package implementation.cancerstates;

import implementation.ICancerState;
import implementation.Lung;

public class S3 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0;
	}

	public float probabilityToInfectOtherCells() {
		return 0;
	}

	public void promote(Lung lung){
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		return 0;
	}
}