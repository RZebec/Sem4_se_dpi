package implementation;

import implementation.cancerstate.S0;
import implementation.helpers.MersenneTwister;

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
		cancerState = new S0();
	}

	public String getLungPosition() {
		return lungPosition;
	}

	public void setCancerState(ICancerState cancerState) {
		this.cancerState = cancerState;
	}

	private void placeTarValueToRandomLungCell() {
		MersenneTwister mersenneTwister = new MersenneTwister();

		int verticalCellPosition = mersenneTwister.nextInt(0, 499);
		int horizontalCellPosition = mersenneTwister.nextInt(0, 499);

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

	private LungCell getLungCellByPosition(int verticalPosition, int horizontalPosition) {
		return this.lungCells.get(verticalPosition * 500 + horizontalPosition);
	}

	public void setCancerCellsForPrepositionedCells(){
		MersenneTwister mersenneTwister = new MersenneTwister();
		for(LungCell lungCell : lungCells) {
			if(!lungCell.isHasCancer())
				lungCell.setHasCancer(mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(lungCell.numberOfTarsInCell())));
			else
				if(mersenneTwister.nextBoolean(this.cancerState.probabilityToInfectOtherCells()))
					lungCell.getNeighbouringCells().get(mersenneTwister.nextInt(0, lungCell.getNeighbouringCells().size()-1)).setHasCancer(true);
		}
	}

	public void randomlyPlaceValueInLungCell(String value) {
		if(value.equals("T")) {
			placeTarValueToRandomLungCell();
		}
	}

	public void promoteCancerStateIfTheNumberOfInfectedCellsIsReached() {
		float percentageOfInfectedCells = this.countInfectedCells()/(500.0F*500.0F);
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
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition + 1));
				} else if(verticalPosition == 0 && horizontalPosition != 0 && horizontalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition + 1));
				} else if(verticalPosition == 499 && horizontalPosition != 0 && horizontalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
				} else if(horizontalPosition == 0 && verticalPosition != 0 && verticalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition + 1));
				} else if(horizontalPosition == 499 && verticalPosition != 0 && verticalPosition != 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
				} else if(horizontalPosition == 0 && verticalPosition == 0) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition + 1));
				} else if(horizontalPosition == 499 && verticalPosition == 499) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition - 1));
				} else if(horizontalPosition == 0) {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition + 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition - 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition + 1));
				} else {
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition, horizontalPosition - 1));
					lungCell.addNeighbouringCell(getLungCellByPosition(verticalPosition + 1, horizontalPosition - 1));
				}
			}
		}
	}
}