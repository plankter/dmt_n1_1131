package ch.ethz.er.dmt_n1_1131;

import ch.ethz.er.dmt_n1_1131.data.Dataset;
import ch.ethz.er.dmt_n1_1131.exporter.CsvExporter;
import ch.ethz.er.dmt_n1_1131.exporter.TimescaleExporter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {

    private static String folder;
    private static String output;
    private static String file;
    private static String format = "csv";
    private static String host = "localhost";
    private static String db = "dmt_n1_1131";
    private static String user = "timescale";
    private static String password = "FCsGT9QCS8wXJtPeDwyw";

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
                case "-host":
                    host = args[++i];
                    break;
                case "-db":
                    db = args[++i];
                    break;
                case "-user":
                    user = args[++i];
                    break;
                case "-password":
                    password = args[++i];
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
                        final TimescaleExporter timescaleExporter = new TimescaleExporter(host, db, user, password);
                        timescaleExporter.init();
                        timescaleExporter.export(dataset);
                        break;
                    }
                }
            } else {
                File inputFolder = new File(folder);
                File[] listOfFiles = inputFolder.listFiles();
                Arrays.sort(listOfFiles);
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
                        final TimescaleExporter timescaleExporter = new TimescaleExporter(host, db, user, password);
                        timescaleExporter.init();
                        int index = 0;
                        for (File item : listOfFiles) {
                            index++;
                            if (item.isFile() && FilenameUtils.isExtension(item.getName(), "dat")) {
                                file = item.getPath();
                                System.out.println("Data File [" + index + "/" + listOfFiles.length + "]: " + file);
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
