package implementation;

import implementation.cancerstates.S3;

public class CentralNervousSystem implements ILungListener {
	private Brain brain;
	private boolean oneLungHasAlreadyReachedS3 = false;

	public CentralNervousSystem(Brain brain) {
		this.brain = brain;
	}

	public boolean isOneLungHasAlreadyReachedS3() {
		return oneLungHasAlreadyReachedS3;
	}

	public void lungStatusChange(ICancerState newState) {
		if(newState instanceof S3) {
			if(oneLungHasAlreadyReachedS3) {
				this.brain.totalLungFailure();
				oneLungHasAlreadyReachedS3 = false;
			}
			else
				oneLungHasAlreadyReachedS3 = true;
		}

	}
}