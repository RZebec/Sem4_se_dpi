package implementation.cancerstates;

import implementation.ICancerState;
import implementation.Lung;

public class S1 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0.20f;
	}

	public float probabilityToInfectOtherCells() {
		return 0.10f;
	}

	public void promote(Lung lung){
		lung.setCancerState(new S2());
		System.out.println(lung.getLungPosition() + " Lung reached cancer state S2!");
	}

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