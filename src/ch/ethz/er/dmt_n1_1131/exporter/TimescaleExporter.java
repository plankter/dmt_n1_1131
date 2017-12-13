package ch.ethz.er.dmt_n1_1131.exporter;

import ch.ethz.er.dmt_n1_1131.data.DataRecord;
import ch.ethz.er.dmt_n1_1131.data.Dataset;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class TimescaleExporter {

    private static String db;
    private static String host;
    private static String user;
    private static String password;

    private String url;


    public TimescaleExporter(String host, String db, String user, String password) {
        TimescaleExporter.host = host;
        TimescaleExporter.db = db;
        TimescaleExporter.user = user;
        TimescaleExporter.password = password;
        try {
            this.url = "jdbc:postgresql://" + TimescaleExporter.host + "/" + TimescaleExporter.db + "?rewriteBatchedStatements=true";
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void init() throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection(this.url, TimescaleExporter.user, TimescaleExporter.password);
             FileReader fileReader = new FileReader("./timescale.sql")
        ) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(fileReader);
        }
    }

    public void export(Dataset dataset) throws Exception {
        final String sqlInsertRecord = "INSERT INTO records (time, orbit_number, sub_orbit_type, tm_station, sft_version, sft_sub_version, " +
                "cal_version, cal_sub_version, geoc_lat, geoc_long, altitude, local_time, geomag_lat, geomag_long, mlt, inv_lat, mc_ilwain_l, " +
                "conjsat_geoc_lat, conjsat_geoc_long, nconj_110_geoc_lat, nconj_110_geoc_long, sconj_110_geoc_lat, sconj_110_geoc_long, " +
                "component, gyrofreq, solar_position, sft_version_1, sft_sub_version_1, aij, bij, quality, sft_version_2, sft_sub_version_2, " +
                "data_type, hk, coord_syst, data_unit, freq, ns, t_dur, nam1c, wf1)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(this.url, TimescaleExporter.user, TimescaleExporter.password)) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertRecord, Statement.SUCCESS_NO_INFO)) {
                for (DataRecord record: dataset.getRecords()) {
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(record.time));
                    preparedStatement.setShort(2, record.orbitNumber);
                    preparedStatement.setShort(3, record.subOrbitType);
                    preparedStatement.setString(4, record.tmStation);
                    preparedStatement.setShort(5, record.sftVersion);
                    preparedStatement.setShort(6, record.sftSubVersion);
                    preparedStatement.setShort(7, record.calVersion);
                    preparedStatement.setShort(8, record.calSubVersion);
                    preparedStatement.setFloat(9, record.geocLat);
                    preparedStatement.setFloat(10, record.geocLong);
                    preparedStatement.setFloat(11, record.altitude);
                    preparedStatement.setFloat(12, record.localTime);
                    preparedStatement.setFloat(13, record.geomagLat);
                    preparedStatement.setFloat(14, record.geomagLong);
                    preparedStatement.setFloat(15, record.mlt);
                    preparedStatement.setFloat(16, record.invLat);
                    preparedStatement.setFloat(17, record.mcIlwainL);
                    preparedStatement.setFloat(18, record.conjsatGeocLat);
                    preparedStatement.setFloat(19, record.conjsatGeocLong);
                    preparedStatement.setFloat(20, record.nconj110GeocLat);
                    preparedStatement.setFloat(21, record.nconj110GeocLong);
                    preparedStatement.setFloat(22, record.sconj110GeocLat);
                    preparedStatement.setFloat(23, record.sconj110GeocLong);
                    preparedStatement.setArray(24, connection.createArrayOf("FLOAT", ArrayUtils.toObject(record.component)));
                    preparedStatement.setFloat(25, record.gyrofreq);
                    preparedStatement.setArray(26, connection.createArrayOf("FLOAT", ArrayUtils.toObject(record.solarPosition)));
                    preparedStatement.setShort(27, record.sftVersion1);
                    preparedStatement.setShort(28, record.sftSubVersion1);
                    preparedStatement.setArray(29, connection.createArrayOf("FLOAT", ArrayUtils.toObject(record.aIJ)));
                    preparedStatement.setArray(30, connection.createArrayOf("FLOAT", ArrayUtils.toObject(record.bIJ)));
                    preparedStatement.setShort(31, record.quality);
                    preparedStatement.setShort(32, record.sftVersion2);
                    preparedStatement.setShort(33, record.sftSubVersion2);
                    preparedStatement.setString(34, record.dataType);
                    preparedStatement.setArray(35, connection.createArrayOf("SMALLINT", ArrayUtils.toObject(record.hk)));
                    preparedStatement.setString(36, record.coordSyst);
                    preparedStatement.setString(37, record.dataUnit);
                    preparedStatement.setFloat(38, record.freq);
                    preparedStatement.setShort(39, record.ns);
                    preparedStatement.setFloat(40, record.tDur);
                    preparedStatement.setString(41, record.nam1c);
                    preparedStatement.setArray(42, connection.createArrayOf("FLOAT", ArrayUtils.toObject(record.wf1)));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            }
        }
    }
}
