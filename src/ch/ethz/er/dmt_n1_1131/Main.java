package ch.ethz.er.dmt_n1_1131;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static boolean debug = true;
    public static String folder = "data";
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
                case "-silent":
                    debug = false;
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
            if (folder != null) {
                Converter converter = new Converter(folder);
                converter.convert(file);
            } else {
                System.out.println("WARNING: nothing to do for this combination of arguments.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
