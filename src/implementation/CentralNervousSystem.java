package implementation;

import implementation.cancerstate.S3;

public class CentralNervousSystem implements ILungListener {
	private Brain brain;
	private boolean oneLungHastAlreadyReachedS3 = false;

	public CentralNervousSystem(Brain brain) {
		this.brain = brain;
	}

	public void lungStatusChange(ICancerState newState) {
		if(newState instanceof S3) {
			if(oneLungHastAlreadyReachedS3) {
				this.brain.totalLungFailure();
				oneLungHastAlreadyReachedS3 = false;
			}
			else
				oneLungHastAlreadyReachedS3 = true;
		}

	}
}