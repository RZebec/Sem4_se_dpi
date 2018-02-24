package implementation;

/**
 * Interface for the Lung Listener.
 */
public interface ILungListener {
	/**
	 * Notifies the Lung Listener if the cancer state of the Lung has changed.
	 * @param newState The new State of the Lung.
	 */
	void lungStatusChange(ICancerState newState);
}