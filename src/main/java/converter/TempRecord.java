package converter;

public class TempRecord {
    private final int id;
    private final int unitId;
    private final double inputValue;
    private final double resultValue;

    public TempRecord(int id, int unitId, double inputValue, double resultValue) {
        this.id = id;
        this.unitId = unitId;
        this.inputValue = inputValue;
        this.resultValue = resultValue;
    }

    public TempRecord(int unitId, double inputValue, double resultValue) {
        this(0, unitId, inputValue, resultValue);
    }

    public int getId() { return id; }
    public int getUnitId() { return unitId; }
    public double getInputValue() { return inputValue; }
    public double getResultValue() { return resultValue; }

    @Override
    public String toString() {
        return "#" + id + ": " + inputValue + " -> " + resultValue;
    }
}