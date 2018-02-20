package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S2 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0.30F;
	}

	public float probabilityToInfectOtherCells() {
		return 0.20F;
	}

	public void promote(Lung lung){
		System.out.println(lung.getLungPosition() + " Lung reached cancer state S3!");
		lung.setCancerState(new S3());
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0F;
			case 1: return 0.25F;
			case 2: return 0.50F;
			case 3: return 0.80F;
		}
		return 0;
	}
}