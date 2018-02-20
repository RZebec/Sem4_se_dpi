package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S0 implements ICancerState {

	public float percentageNeededToPromoteLung() {
		return 0.10F;
	}

	public float probabilityToInfectOtherCells() {
		return 0.05F;
	}

	public void promote(Lung lung){
		System.out.println(lung.getLungPosition() + " Lung is reaching cancer state S1!");
		lung.setCancerState(new S1());
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0F;
			case 1: return 0.10F;
			case 2: return 0.30F;
			case 3: return 0.70F;
		}
		return 0;
	}
}