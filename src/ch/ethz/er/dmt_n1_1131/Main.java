package ch.ethz.er.dmt_n1_1131;

import ch.ethz.er.dmt_n1_1131.data.Dataset;
import ch.ethz.er.dmt_n1_1131.exporter.CsvExporter;
import ch.ethz.er.dmt_n1_1131.exporter.TimescaleExporter;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static String folder;
    public static String file;
    public static String format = "csv";

    public static void main(String[] args) {
        Instant start = Instant.now();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-file":
                    file = args[++i];
                    break;
                case "-folder":
                    folder = args[++i];
                    break;
                case "-format":
                    format = args[++i];
                    break;
                default:
                    System.out.println("WARNING: unknown argument " + args[i] + ".");
                    break;
            }
        }

        try {
            Converter converter = new Converter(folder);
            Dataset dataset = converter.convert(file);
            switch (format) {
                case "csv": {
                    if (folder != null) {
                        CsvExporter exporter = new CsvExporter(folder);
                        exporter.export(dataset, file);
                    } else {
                        System.out.println("WARNING: specify output folder");
                    }
                    break;
                }
                case "timescale": {
                    TimescaleExporter timescaleExporter = new TimescaleExporter();
                    timescaleExporter.init();
                    timescaleExporter.export(dataset);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
