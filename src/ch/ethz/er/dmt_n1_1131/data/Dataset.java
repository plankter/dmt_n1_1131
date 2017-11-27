package ch.ethz.er.dmt_n1_1131.data;

import java.util.ArrayList;

public class Dataset {

    private final ArrayList<DataRecord> dataRecords;

    public Dataset(int numberOfRecords) {
        this.dataRecords = new ArrayList<DataRecord>(numberOfRecords);
    }

    public boolean AddRecord(DataRecord record) {
        return this.dataRecords.add(record);
    }

    public ArrayList<DataRecord> getRecords() {
        return this.dataRecords;
    }

    @Override
    public String toString() {
        return "Number of records: " + dataRecords.size();
    }
}
