package implementation.organs;

import implementation.Configuration;
import implementation.ICancerState;
import implementation.ILungListener;
import implementation.organs.cancerstates.S0;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Lung.
 */
public class Lung {
	/**
	 * The position of the Lung.
	 */
	private String lungPosition;
	/**
	 * The Cancer State of the Lung.
	 */
	private ICancerState cancerState;
	/**
	 * The list of listeners for the Lung.
	 */
	private List<ILungListener> listeners;
	/**
	 * The list of Lung Cells.
	 * Represents the Lung Cell matrix of 500x500 cells.
	 */
	private List<LungCell> lungCells;
	/**
	 * Constructor for the Lung.
	 * @param lungPosition The position of the Lung.
	 */
	public Lung(String lungPosition){
		this.lungPosition = lungPosition;
		this.listeners = new ArrayList<>();
		this.lungCells = new ArrayList<>();
		for(int i=0; i<500*500; i++) {
			this.lungCells.add(new LungCell());
		}
		this.determineNeighboringCells();
		this.cancerState = new S0();
	}
	/**
	 * Gets the Position of the Lung.
	 * @return
	 */
	public String getLungPosition() {
		return lungPosition;
	}
	/**
	 * Gets the List of listeners for the Lung.
	 * @return The List of listeners.
	 */
	public List<ILungListener> getListeners() {
		return listeners;
	}
	/**
	 * Gets the cancer state of the Lung.
	 * @return The cancer state of the Lung.
	 */
	public ICancerState getCancerState() {
		return cancerState;
	}
	/**
	 * Sets the cancer state of the Lung.
	 * @param cancerState The cancer state.
	 */
	public void setCancerState(ICancerState cancerState) {
		this.cancerState = cancerState;
	}
	/**
	 * Places the tar value, represented with a `T`, on a random Lung Cell.
	 */
	private void placeTarValueToRandomLungCell() {
		int verticalCellPosition = Configuration.instance.mersenneTwister.nextInt(0, 499);
		int horizontalCellPosition = Configuration.instance.mersenneTwister.nextInt(0, 499);

		LungCell currentLungCell = getLungCellByPosition(verticalCellPosition, horizontalCellPosition);

		switch (currentLungCell.getCellValue()) {
			case "H":
				currentLungCell.setCellValue("1T");
				break;
			case "1T":
				currentLungCell.setCellValue("2T");
				break;
			case "2T":
				currentLungCell.setCellValue("3T");
				break;
		}
	}
	/**
	 * Gets a LungCell from the List of Lung Cells by their vertical and horizontal position in the Lung Cell matrix.
	 * @param verticalPosition The vertical position of the Lung Cell.
	 * @param horizontalPosition The horizontal position of the Lung Cell.
	 * @return The Lung Cell.
	 */
	public LungCell getLungCellByPosition(int verticalPosition, int horizontalPosition) {
		return this.lungCells.get(verticalPosition * 500 + horizontalPosition);
	}
	/**
	 * Sets the value is infected value on the Lung Cells based on the probability to catch cancer.
	 */
	public void setCancerCellsForPrepositionedCells(){
		for(LungCell lungCell : lungCells) {
			if(!lungCell.isHasCancer() && lungCell.numberOfTarsInCell() > 0)
				lungCell.setHasCancer(Configuration.instance.mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(lungCell.numberOfTarsInCell())));
			if(lungCell.isHasCancer() && Configuration.instance.mersenneTwister.nextBoolean(this.cancerState.probabilityToInfectOtherCells()))
				lungCell.getNeighbouringCells().get(Configuration.instance.mersenneTwister.nextInt(0, lungCell.getNeighbouringCells().size()-1)).setHasCancer(true);
		}
	}
	/**
	 * Places the tar value at a random Lung Cell in the Lung Cell matrix.
	 * @param value The value to place at the random Lung Cell.
	 */
	public void randomlyPlaceValueInLungCell(String value) {
		if(value.equals("T"))
			placeTarValueToRandomLungCell();
	}
	/**
	 * Promotes the Lung to another cancer state if the number of infected cells exceeds a certain percentage.
	 * S0 -> S1 = 10% of the LungCells are infected.
	 * S1 -> S2 = 20% of the LungCells are infected.
	 * S2 -> S3 = 30% of the LungCells are infected.
	 * Notifies the Central Nervous System that is set as a listener, if the cancer state was changed.
	 */
	public void promoteCancerStateIfTheNumberOfInfectedCellsIsReached() {
		float percentageOfInfectedCells = this.countInfectedCells()/(500.0f*500.0f);
		if (percentageOfInfectedCells >= this.cancerState.percentageNeededToPromoteLung()) {
			this.cancerState.promote(this);
			for(ILungListener listener : listeners) {
				listener.lungStatusChange(this.cancerState);
			}
		}
	}
	/**
	 * Adds a listener for the Lung.
	 * @param listener The Lung Listener.
	 */
	public void addListener(ILungListener listener){
		this.listeners.add(listener);
	}
	/**
	 * Removes a listener for the Lung.
	 * @param listener The Lung Listener.
	 */
	public void removeListener(ILungListener listener){
		this.listeners.remove(listener);
	}
	/**
	 * Counts the number of Lung Cells that are infected with cancer.
	 * @return The number of Lung Cells that are infected with cancer.
	 */
	public long countInfectedCells() {
		return this.lungCells.stream().filter(LungCell::isHasCancer).count();
	}
	/**
	 * Determines the neighbouring cells of a lung cell.
	 * The neighbouring cells are the cells: above, under, left and right of the current cell.
	 */
	private void determineNeighboringCells() {
		for(int verticalPosition=0; verticalPosition<500; verticalPosition++) {
			for(int horizontalPosition=0; horizontalPosition<500; horizontalPosition++) {
				LungCell lungCell = getLungCellByPosition(verticalPosition, horizontalPosition);
				if(verticalPosition !=0 && horizontalPosition !=0 && verticalPosition != 499 && horizontalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(verticalPosition == 0 && horizontalPosition != 0 && horizontalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(verticalPosition == 499 && horizontalPosition != 0 && horizontalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
				} else if(horizontalPosition == 0 && verticalPosition != 0 && verticalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(horizontalPosition == 499 && verticalPosition != 0 && verticalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(horizontalPosition == 0 && verticalPosition == 0) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(horizontalPosition == 499 && verticalPosition == 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
				} else if(horizontalPosition == 0) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
				} else {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
				}
			}
		}
	}
}