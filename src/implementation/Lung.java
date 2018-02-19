package implementation;

import implementation.helpers.MersenneTwister;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lung {
	private ICancerState cancerState;
	private ICigaretteInhalationFilter cigaretteInhalationFilter;
	private List<ILungListener> listeners;
	private LungCell[][] lungCells;

	public Lung(){
		this.listeners = new ArrayList<>();
		this.lungCells = new LungCell[500][];
		Arrays.fill(lung);
		this.lungCells = new LungCell[500][500];
	}

	public void setCancerState(ICancerState cancerState) {
		this.cancerState = cancerState;
	}

	private void placeTarValueToRandomLungCell() {
		MersenneTwister mersenneTwister = new MersenneTwister();

		int verticalCellPosition = mersenneTwister.nextInt(0, 500);
		int horizontalCellPosition = mersenneTwister.nextInt(0, 500);

		String lungCellValue = this.lungCells[verticalCellPosition][horizontalCellPosition].getCellValue();

		switch (lungCellValue) {
			case "H":
				this.lungCells[verticalCellPosition][horizontalCellPosition].setCellValue("1T");
				this.lungCells[verticalCellPosition][horizontalCellPosition].setHasCancer(mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(1)));
				break;
			case "1T":
				this.lungCells[verticalCellPosition][horizontalCellPosition].setCellValue("2T");
				this.lungCells[verticalCellPosition][horizontalCellPosition].setHasCancer(mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(2)));
				break;
			case "2T":
				this.lungCells[verticalCellPosition][horizontalCellPosition].setCellValue("3T");
				this.lungCells[verticalCellPosition][horizontalCellPosition].setHasCancer(mersenneTwister.nextBoolean(this.cancerState.cancerProbabilityForLung(3)));
				break;
		}
	}

	public void randomlyPlaceValueInLungCell(String value) {
		if(value.equals("T")) {
			placeTarValueToRandomLungCell();
		}
	}

	public void promoteCancerStateIfTheNumberOfInfectedCellsIsReached() {
		if (this.countInfectedCells()/(500.0*500.0) >= this.cancerState.percentageNeededToPromoteLung()) {
			this.cancerState.promote(this);
			for(ILungListener listener : listeners) {
				listener.lungStatusChange(this.cancerState);
			}
		}
	}

	public void addListener(ILungListener listener){
		this.listeners.add(listener);
	}

	public void inhaleSmoke(List<String> cigaretteSmoke){
	}

	public void removeListener(ILungListener listener){
		this.listeners.remove(listener);
	}

	private long countInfectedCells() {
		return Arrays.stream(this.lungCells).flatMap(x -> Arrays.stream(x).filter(cell -> cell.isHasCancer())).count();
	}

	private void spreadingOfCancerCells(){

	}
}