package implementation;

public class Brain {
	private boolean totalLungFailure = false;

	public void totalLungFailure(){
		System.out.println("Both Lungs have reached State S3 and the Central Nervous System has called for a total Lung failure!");
		System.out.println("WASTED!");
		this.totalLungFailure = true;
	}

	public boolean isTotalLungFailure() {
		return totalLungFailure;
	}
}