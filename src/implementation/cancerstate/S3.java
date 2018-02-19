package implementation.cancerstate;

import implementation.ICancerState;
import implementation.Lung;

public class S3 implements ICancerState {

	public S3(){
		this.print();
	}

	public float percentageNeededToPromoteLung() {
		return 0;
	}

	public void print(){
		System.out.println("Lung has reached State S3!");
	}


	public void promote(Lung lung){
	}

	public float cancerProbabilityForLung(int numberOfTarsInCell) {
		return 0;
	}
}