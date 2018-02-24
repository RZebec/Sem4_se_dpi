package implementation.organs;

import implementation.ICancerState;
import implementation.ILungListener;
import implementation.organs.cancerstates.S3;

/**
 * Class representing the Central Nervous System.
 */
public class CentralNervousSystem implements ILungListener {
	/**
	 * The Brain.
	 */
	private Brain brain;
	/**
	 * Boolean representing if one Lung already has reached state S3.
	 */
	private boolean oneLungHasAlreadyReachedS3 = false;
	/**
	 * Constructor for the Central Nervous System.
	 * @param brain The Brain.
	 */
	public CentralNervousSystem(Brain brain) {
		this.brain = brain;
	}
	/**
	 * Boolean representing if one Lung already has reached state S3.
	 * @return True if it a Lung has reached state S3, false if not.
	 */
	public boolean isOneLungHasAlreadyReachedS3() {
		return oneLungHasAlreadyReachedS3;
	}
	/**
	 * Notifies the Central Nervous System (Lung Listener) if the cancer state of the Lung has changed.
	 * @param newState The new State of the Lung.
	 */
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