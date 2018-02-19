package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S0 implements ICancerState {

	public S0(){
		this.print();
	}

	public float percentageNeededToPromoteLung() {
		return 0.10F;
	}

	public float probabilityToInfectOtherCells() {
		return 0.05F;
	}

	public void print(){
		System.out.println("Lung has State S0!");
	}

	public void promote(Lung lung){
		System.out.println("Cancer state of Lungs is reaching S1!");
		lung.setCancerState(new S1());
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		switch(numberOfTarsInCell) {
			case 0: return 0F;
			case 1: return 0.10F;
			case 2: return 0.15F;
			case 3: return 0.25F;
			default:
				System.out.println("Cell cannot have 4 Tars!");
				return 0;
		}
	}
}