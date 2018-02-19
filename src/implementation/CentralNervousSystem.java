package implementation;

/**
 * @author RZE
 * @version 1.0
 * @created 19-Feb-2018 11:48:01
 */
public class CentralNervousSystem implements ILungListener {
	private Brain brain;
	private String leftLungState;
	private String rightLungState;

	public CentralNervousSystem(Brain brain) {
		this.brain = brain;
		this.leftLungState = "S0";
		this.rightLungState = "S0";
	}

	public void lungStatusChange(ICancerState newState) {

	}
}