package ch.ethz.er.dmt_n1_1131;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import ch.ethz.er.dmt_n1_1131.data.DataRecord;

public class Converter {

    private String dataFolder;

    public Converter(String folder) {
        this.dataFolder = folder;
    }

    public void convert(String dataFilePath) throws Exception {
        FileInputStream fis = new FileInputStream(dataFilePath);
        BufferedInputStream bis = new BufferedInputStream(fis, DataRecord.DATA_RECORD_BYTE_LENGTH);
        try {
            byte[] dataBlock = new byte[DataRecord.DATA_RECORD_BYTE_LENGTH];
            while (bis.available() > 0) {
                int bytesRead = bis.read(dataBlock);
                if (bytesRead != -1) {
                    if (bytesRead == DataRecord.DATA_RECORD_BYTE_LENGTH) {
                        DataRecord record = new DataRecord(dataBlock);
                    } else {
                        throw new Exception("Data block is incomplete");
                    }
                }
            }
        } finally {
            bis.close();
            fis.close();
        }
    }
}
