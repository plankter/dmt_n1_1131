package ch.ethz.er.dmt_n1_1131.exporter;

import ch.ethz.er.dmt_n1_1131.data.DataRecord;
import ch.ethz.er.dmt_n1_1131.data.Dataset;
import org.apache.commons.io.FilenameUtils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;

public class CsvExporter {

    private final String outputFolder;

    public CsvExporter(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public void export(Dataset dataset, String filename) throws Exception {
        final String outputFilename = FilenameUtils.concat(this.outputFolder, FilenameUtils.getBaseName(filename)) + ".csv";
        System.out.println("CSV file: " + outputFilename);
        try (ICsvListWriter writer = new CsvListWriter(new FileWriter(outputFilename), CsvPreference.TAB_PREFERENCE)) {
            writer.writeHeader(DataRecord.CSV_HEADER);
            for (DataRecord record : dataset.getRecords()) {
                writer.write(record.getValues());
            }
        }
    }
}
