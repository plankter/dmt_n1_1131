package ch.ethz.er.dmt_n1_1131;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import ch.ethz.er.dmt_n1_1131.data.DataRecord;
import ch.ethz.er.dmt_n1_1131.data.Dataset;

public class Converter {

    public Converter() {
    }

    public Dataset convert(String dataFilePath) throws Exception {
        try (FileInputStream fis = new FileInputStream(dataFilePath);
             BufferedInputStream bis = new BufferedInputStream(fis, DataRecord.DATA_RECORD_BYTE_LENGTH)
        ) {
            int numberOfRecords = bis.available() / DataRecord.DATA_RECORD_BYTE_LENGTH;
            Dataset dataset = new Dataset(numberOfRecords);
            byte[] dataBlock = new byte[DataRecord.DATA_RECORD_BYTE_LENGTH];
            while (bis.available() > 0) {
                int bytesRead = bis.read(dataBlock);
                if (bytesRead != -1) {
                    if (bytesRead == DataRecord.DATA_RECORD_BYTE_LENGTH) {
                        DataRecord record = new DataRecord(dataBlock);
                        dataset.AddRecord(record);
                    } else {
                        throw new Exception("Data block is incomplete");
                    }
                }
            }
            System.out.println(dataset);
            return dataset;
        }
    }
}
