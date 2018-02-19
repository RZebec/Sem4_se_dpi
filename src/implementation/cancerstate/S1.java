package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S1 implements ICancerState {

	public S1(){
		this.print();
	}

	public float percentageNeededToPromoteLung() {
		return 0.20F;
	}

	public void print(){
		System.out.println("Lung has reached State S1!");
	}

	public void promote(Lung lung){
		System.out.println("Cancer state of Lungs is reaching S2!");
		lung.setCancerState(new S2());
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 1: return 0.30F;
			case 2: return 0.35F;
			case 3: return 0.50F;
			default:
				System.out.println("Cell cannot have 4 Tars!");
				return 0;
		}
	}
}