package ch.ethz.er.dmt_n1_1131;

import ch.ethz.er.dmt_n1_1131.data.Dataset;
import ch.ethz.er.dmt_n1_1131.exporter.CsvExporter;
import ch.ethz.er.dmt_n1_1131.exporter.TimescaleExporter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Main {

    private static String folder;
    private static String output;
    private static String file;
    private static String format = "csv";

    private static boolean processArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-file":
                    file = args[++i];
                    break;
                case "-folder":
                    folder = args[++i];
                    break;
                case "-output":
                    output = args[++i];
                    break;
                case "-format":
                    format = args[++i];
                    break;
                default:
                    System.out.println("WARNING: unknown argument " + args[i] + ".");
                    break;
            }
        }

        if (file == null && folder == null) {
            System.out.println("WARNING: specify input file or folder");
            return false;
        }

        if (format.equals("csv") && output == null) {
            System.out.println("WARNING: specify output folder");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        if (!processArgs(args)) return;

        Instant start = Instant.now();
        try {
            final Converter converter = new Converter();

            if (file != null) {
                final Dataset dataset = converter.convert(file);
                switch (format) {
                    case "csv": {
                        final CsvExporter exporter = new CsvExporter(output);
                        exporter.export(dataset, file);
                        break;
                    }
                    case "timescale": {
                        final TimescaleExporter timescaleExporter = new TimescaleExporter();
                        timescaleExporter.init();
                        timescaleExporter.export(dataset);
                        break;
                    }
                }
            } else {
                File inputFolder = new File(folder);
                File[] listOfFiles = inputFolder.listFiles();
                switch (format) {
                    case "csv": {
                        final CsvExporter exporter = new CsvExporter(output);
                        for (File item : listOfFiles) {
                            if (item.isFile() && FilenameUtils.isExtension(item.getName(), "dat")) {
                                file = item.getPath();
                                System.out.println("Data File: " + file);
                                final Dataset dataset = converter.convert(file);
                                exporter.export(dataset, file);
                            }
                        }
                        break;
                    }
                    case "timescale": {
                        final TimescaleExporter timescaleExporter = new TimescaleExporter();
                        timescaleExporter.init();
                        for (File item : listOfFiles) {
                            if (item.isFile() && FilenameUtils.isExtension(item.getName(), "dat")) {
                                file = item.getPath();
                                System.out.println("Data File: " + file);
                                final Dataset dataset = converter.convert(file);
                                timescaleExporter.export(dataset);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        Instant end = Instant.now();
        System.out.println("Duration: " + Duration.between(start, end));
    }
}
