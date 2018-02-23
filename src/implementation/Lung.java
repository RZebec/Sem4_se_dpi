package implementation;

import implementation.cancerstates.S0;

import java.util.ArrayList;
import java.util.List;

public class Lung {
	private String lungPosition;
	private ICancerState cancerState;
	private List<ILungListener> listeners;
	private List<LungCell> lungCells;

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

	public String getLungPosition() {
		return lungPosition;
	}

	public List<ILungListener> getListeners() {
		return listeners;
	}

	public ICancerState getCancerState() {
		return cancerState;
	}

	public void setCancerState(ICancerState cancerState) {
		this.cancerState = cancerState;
	}

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

	public LungCell getLungCellByPosition(int verticalPosition, int horizontalPosition) {
		return this.lungCells.get(verticalPosition * 500 + horizontalPosition);
	}

	public void setCancerCellsForPrepositionedCells(){
		for(LungCell lungCell : lungCells) {
			if(!lungCell.isHasCancer() && lungCell.numberOfTarsInCell() > 0)
				lungCell.setHasCancer(Configuration.instance.mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(lungCell.numberOfTarsInCell())));
			if(lungCell.isHasCancer() && Configuration.instance.mersenneTwister.nextBoolean(this.cancerState.probabilityToInfectOtherCells()))
				lungCell.getNeighbouringCells().get(Configuration.instance.mersenneTwister.nextInt(0, lungCell.getNeighbouringCells().size()-1)).setHasCancer(true);
		}
	}

	public void randomlyPlaceValueInLungCell(String value) {
		if(value.equals("T"))
			placeTarValueToRandomLungCell();
	}

	public void promoteCancerStateIfTheNumberOfInfectedCellsIsReached() {
		float percentageOfInfectedCells = this.countInfectedCells()/(500.0f*500.0f);
		if (percentageOfInfectedCells >= this.cancerState.percentageNeededToPromoteLung()) {
			this.cancerState.promote(this);
			for(ILungListener listener : listeners) {
				listener.lungStatusChange(this.cancerState);
			}
		}
	}

	public void addListener(ILungListener listener){
		this.listeners.add(listener);
	}

	public void removeListener(ILungListener listener){
		this.listeners.remove(listener);
	}

	public long countInfectedCells() {
		return this.lungCells.stream().filter(LungCell::isHasCancer).count();
	}

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