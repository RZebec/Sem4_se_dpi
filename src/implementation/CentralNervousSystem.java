package implementation;

import implementation.cancerstate.S3;

/**
 * @author RZE
 * @version 1.0
 * @created 19-Feb-2018 11:48:01
 */
public class CentralNervousSystem implements ILungListener {
	private Brain brain;
	private boolean oneLungHastAlreadyReachedS3 = false;

	public CentralNervousSystem(Brain brain) {
		this.brain = brain;
	}

	public void lungStatusChange(ICancerState newState) {
		if(newState instanceof S3) {
			if(oneLungHastAlreadyReachedS3)
				this.brain.totalLungFailure();
			else
				oneLungHastAlreadyReachedS3 = true;
		}

	}
}