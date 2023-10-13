public class Cell {

    private int rowPlace;
    private int colPlace;

    public Cell(int rowPlace, int colPlace) {
        this.rowPlace = rowPlace;
        this.colPlace = colPlace;
    }

    public int getRowPlace() {
        return rowPlace;
    }

    public void setRowPlace(int rowPlace) {
        this.rowPlace = rowPlace;
    }

    public int getColPlace() {
        return colPlace;
    }

    public void setColPlace(int colPlace) {
        this.colPlace = colPlace;
    }
}
