package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S2 implements ICancerState {

	public S2(){
		this.print();
	}

	public float percentageNeededToPromoteLung() {
		return 0.30F;
	}

	public void print(){
		System.out.println("Lung has reached State S2!");
	}

	public float probabilityToInfectOtherCells() {
		return 0.20F;
	}

	public void promote(Lung lung){
		System.out.println(lung.getLungPosition() + " Lung is reaching cancer state S3!");
		lung.setCancerState(new S3());
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0F;
			case 1: return 0.70F;
			case 2: return 0.75F;
			case 3: return 0.80F;
		}
		return 0;
	}
}