package implementation;

public interface ICancerState {
	void print();
	void promote(Lung lung);
	float cancerProbabilityForLung(int numberOfTarsInCell);
	float percentageNeededToPromoteLung();
	float probabilityToInfectOtherCells();
}