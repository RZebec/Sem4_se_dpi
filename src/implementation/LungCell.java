package implementation;

import java.util.ArrayList;
import java.util.List;

public class LungCell {
    private String cellValue;
    private boolean hasCancer;
    private List<LungCell> neighbouringCells;

    public LungCell() {
        cellValue = "H";
        hasCancer = false;
        neighbouringCells = new ArrayList<>();
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public boolean isHasCancer() {
        return hasCancer;
    }

    public void setHasCancer(boolean hasCancer) {
        this.hasCancer = hasCancer;
    }

    public List<LungCell> getNeighbouringCells() {
        return neighbouringCells;
    }

    public void setNeighbouringCells(List<LungCell> neighbouringCells) {
        this.neighbouringCells = neighbouringCells;
    }
}
