package implementation;

/**
 * Class representing the Brain.
 */
public class Brain {
	/**
	 * Boolean representing if the brain has called for a total lung failure.
	 */
	private boolean totalLungFailure = false;
	/**
	 * Sends an information to the Brain for a total lung failure.
	 */
	public void totalLungFailure(){
		System.out.println("Both Lungs have reached State S3 and the Central Nervous System has called for a total Lung failure!");
		this.totalLungFailure = true;
	}
	/**
	 * Gets the boolean representing if the brain has called for a total lung failure.
	 * @return True if there is a total lung failure, false if not.
	 */
	public boolean isTotalLungFailure() {
		return totalLungFailure;
	}
}