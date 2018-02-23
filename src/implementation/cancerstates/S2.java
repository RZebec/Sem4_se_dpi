package implementation.cancerstates;

import implementation.ICancerState;
import implementation.Lung;

public class S2 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0.40f;
	}

	public float probabilityToInfectOtherCells() {
		return 0.20f;
	}

	public void promote(Lung lung){
		lung.setCancerState(new S3());
		System.out.println(lung.getLungPosition() + " Lung reached cancer state S3!");
	}

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