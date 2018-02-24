package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Lung Cell.
 */
public class LungCell {
    /**
     * The value of the Lung Cell.
     */
    private String cellValue;
    /**
     * Boolean representing if the current Lung Cell has cancer or not.
     */
    private boolean hasCancer;
    /**
     * List of neighboring Lung Cells.
     */
    private List<LungCell> neighbouringCells;
    /**
     * Constructor for the Lung Cell.
     */
    public LungCell() {
        cellValue = "H";
        hasCancer = false;
        neighbouringCells = new ArrayList<>();
    }
    /**
     * Gets the value of the Lung Cell.
     * @return The value of the Lung Cell.
     */
    public String getCellValue() {
        return cellValue;
    }
    /**
     * Sets the value of the Lung Cell.
     * @param cellValue The value of the Lung Cell.
     */
    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }
    /**
     * Gets the boolean representing if the current Lung Cell has cancer or not.
     * @return True if it has cancer, false if not.
     */
    public boolean isHasCancer() {
        return hasCancer;
    }
    /**
     * Gets the number of tars, represented with a `T`, present if the LungCell.
     * @return The number of tars in the Cell.
     */
    public int numberOfTarsInCell() {
        switch(cellValue){
            case "1T": return 1;
            case "2T": return 2;
            case "3T": return 3;
            default: return 0;
        }
    }
    /**
     * Sets the boolean representing if the current Lung Cell has cancer or not.
     * @param hasCancer True if it has cancer, false if not.
     */
    public void setHasCancer(boolean hasCancer) {
        this.hasCancer = hasCancer;
    }
    /**
     * Gets the neighbouring calls of the current Lung Cell.
     * @return The neighbouring cells of the current Lung Cell.
     */
    public List<LungCell> getNeighbouringCells() {
        return neighbouringCells;
    }
    /**
     * Add a Lung Cell to the list of the neighbouring cells for the current Lung Cell.
     * @param neighbouringCell The neighbouring call of the current Lung Cell.
     */
    public void addNeighbouringCell(LungCell neighbouringCell) {
        this.neighbouringCells.add(neighbouringCell);
    }
}
