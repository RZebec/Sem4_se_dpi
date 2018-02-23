package implementation.cancerstates;

import implementation.ICancerState;
import implementation.Lung;

public class S0 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0.10f;
	}

	public float probabilityToInfectOtherCells() {
		return 0.05f;
	}

	public void promote(Lung lung){
		lung.setCancerState(new S1());
		System.out.println(lung.getLungPosition() + " Lung is reaching cancer state S1!");
	}

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