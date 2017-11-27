package ch.ethz.er.dmt_n1_1131.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

public final class DataRecord {

    /*
    Definition : A data record consists of 4 blocks. Block 4 contains experimental data (1 component waveform).
    Comment : A DMT_N1_1131 datafile consists of a number of data records.
    Type : STRUCTURE
    Length : 264504 bits
     */
    public static final int DATA_RECORD_BYTE_LENGTH = 33063;

    public static final Charset CHARSET = Charset.forName("ISO-8859-1");

    public static final String[] CSV_HEADER = new String[] {
            "Time", "OrbitNumber", "SubOrbitType", "TmStation", "SftVersion", "SftSubVersion",
            "CalVersion", "CalSubVersion", "GeocLat", "GeocLong", "Altitude", "LocalTime",
            "GeomagLat", "GeomagLong", "MLT", "InvLat", "McIlwainL", "ConjsatGeocLat", "ConjsatGeocLong",
            "Nconj110GeocLat", "Nconj110GeocLong", "Sconj110GeocLat", "Sconj110GeocLong", "Component", "Gyrofreq",
            "SolarPosition", "SftVersion1", "SftSubVersion1", "AIJ", "BIJ", "Quality", "SftVersion2", "SftSubVersion2",
            "DataType", "HK", "CoordSyst", "DataUnit", "Freq", "NS", "TDur", "Nam1c", "Wf1"
    };

    public Object[] getValues() {
        return new Object[] {
                time, orbitNumber, subOrbitType, tmStation, sftVersion, sftSubVersion,
                calVersion, calSubVersion, geocLat, geocLong, altitude, localTime,
                geomagLat, geomagLong, mlt, invLat, mcIlwainL, conjsatGeocLat, conjsatGeocLong,
                nconj110GeocLat, nconj110GeocLong, sconj110GeocLat, sconj110GeocLong, component, gyrofreq,
                solarPosition, sftVersion1, sftSubVersion1, aIJ, bIJ, quality, sftVersion2, sftSubVersion2,
                dataType, hk, coordSyst, dataUnit, freq, ns, tDur, nam1c, wf1
        };
    }

    /*
    UTC time (calendar format) of the first point of the data array
     */
    public LocalDateTime time;

    /*
    Definition : Orbit number
    Type : Integer [1 .. 65535]
    Length : 16 bits
     */
    public short orbitNumber;

    /*
    Definition : Sub orbit type : 0=downward ; 1= upward
    Type : Integer [0 .. 1]
    Length : 16 bits
     */
    public short subOrbitType;

    /*
    Definition : Telemetry station : "TOULOUSE"
    Type : 8 Characters
    Length : 64 bits
     */
    public String tmStation;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftVersion;

    /*
    Definition : Sub-version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftSubVersion;

    /*
    Definition : Version (edition number) of the calibration file : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte calVersion;

    /*
    Definition : Sub-version (revision number) of the calibration file : from 0 to 63
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte calSubVersion;

    /*
    Definition : Geocentric latitude (-90° , +90°)
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float geocLat;

    /*
    Definition : Geocentric longtitude (0° , 360°)
    Unit :"deg"
    Type : Real [0 .. 360]
    Length : 32 bits
     */
    public float geocLong;

    /*
    Definition : Altitude
    Unit :"km"
    Type : Real
    Length : 32 bits
     */
    public float altitude;

    /*
    Definition : Local time of the first point of the data array (0 , 24 h)
    Unit :"h_(fractional_hour)"
    Type : Real [0.0 .. 24.0]
    Length : 32 bits
     */
    public float localTime;

    /*
    Definition : Geomagnetic latitude (-90° , +90°)
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float geomagLat;

    /*
    Definition : Geomagnetic longitude (0° , +360°)
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    public float geomagLong;

    /*
    Definition : Magnetic local time of the first point
    Unit :"h_(fractional_hour)"
    Type : Real [0.0 .. 24.0]
    Length : 32 bits
     */
    public float mlt;

    /*
    Definition : Invariant latitude (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float invLat;

    /*
    Definition : Mc Ilwain parameter L (0 , 999)
    Type : Real
    Length : 32 bits
     */
    public float mcIlwainL;

    /*
    Definition : Geocentric latitude of the conjugate point at the satellite altitude (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float conjsatGeocLat;

    /*
    Definition : Geocentric longitude of the conjugate point at the satellite altitude (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    public float conjsatGeocLong;

    /*
    Definition : Geocentric latitude of North conjugate point at altitude 110 km (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float nconj110GeocLat;

    /*
    Definition : Geocentric longitude of North conjugate point at altitude 110 km (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    public float nconj110GeocLong;

    /*
    Definition : Geocentric latitude of South conjugate point at altitude 110 km (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    public float sconj110GeocLat;

    /*
    Definition : Geocentric longitude of South conjugate point at altitude 110 km (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    public float sconj110GeocLong;

    /*
    Definition : Components (X, Y, Z) of the magnetic field model at the satellite point (geographic coordinate system)
    Unit :"nT"
    Type : ARRAY with 3 items
    Length : 96 bits
     */
    public float[] component;

    /*
    Definition : Proton gyrofrequency at the satellite point
    Unit :"Hz"
    Type : Real
    Length : 32 bits
     */
    public float gyrofreq;

    /*
    Definition : Solar position (Xs, Ys, Zs) in the geographic coordinate system
    Unit :"AU"
    Type : ARRAY with 3 items
    Length : 96 bits
     */
    public float[] solarPosition;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftVersion1;

    /*
    Definition : Sub version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftSubVersion1;

    /*
    Definition : Matrix from satellite coordinate system to geographic coordinate system.
    Note : The elements a_ij of the matrix are stored as a_11, a_12, a_13, a_21, a_22, a_23, a_31, a_32, a_33 where i is the raw index and j the column index of the element a_ij.
    Type : ARRAY with 3","1 .. 3 items
    Length : 288 bits
     */
    public float[] aIJ;

    /*
    Definition : Matrix from geographic coordinate system to local geomagnetic coordinate system.
    Note : The elements b_ij of the matrix are stored as b_11, b_12, b_13, b_21, b_22, b_23, b_31, b_32, b_33 where i is the raw index and j the column index of the element b_ij.
    Type : ARRAY with 3","1 .. 3 items
    Length : 288 bits
     */
    public float[] bIJ;

    /*
    Definition : Quality index of the attitude parameters :
    0 = attitude not available (matrix coefficients = 99999.)
    1 = measured attitude (very rare occurrence)
    2 = computed attitude (normal occurrence)
    Type : Integer [0 .. 2]
    Length : 16 bits
     */
    public short quality;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftVersion2;

    /*
    Definition : Sub version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    public byte sftSubVersion2;

    /*
    Definition : Data type : "VLF ELECTRIC WAVEFORM"
    Type : 21 Characters
    Length : 168 bits
     */
    public String dataType;

    /*
    Definition : House-Keepings and Status
    Type : ARRAY with 32 items
    Length : 256 bits
     */
    public byte[] hk;

    /*
    Definition : Data coordinate system : "Sensor "
    Type : 9 Characters
    Length : 72 bits
     */
    public String coordSyst;

    /*
    Definition : Data unit : "µV/m"
    Type : 16 Characters
    Length : 128 bits
     */
    public String dataUnit;

    /*
    Definition : Sampling frequency (40000. Hz)
    Unit :"Hz"
    Type : Real
    Length : 32 bits
     */
    public float freq;

    /*
    Definition : Sample data number per component : 8192
    Type : Integer [8192 .. 8192]
    Length : 16 bits
     */
    public short ns;

    /*
    Definition : Time duration of one data array : 8192 / 40000
    Unit :"s"
    Type : Real
    Length : 32 bits
     */
    public float tDur;

    /*
    Definition : Component name : "Eij", i, j are the sensor numbers.
    Type : 3 Characters
    Length : 24 bits
     */
    public String nam1c;

    /*
    Definition : Waveform sample array.
    Unit :"µV/m"
    Type : ARRAY with 8192 items
    Length : 262144 bits
     */
    public float[] wf1;

    public DataRecord(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.BIG_ENDIAN);

        buffer.position(8); // Skip CCSDS-formated date

        short year = buffer.getShort();
        short month = buffer.getShort();
        short day = buffer.getShort();
        short hour = buffer.getShort();
        short minute = buffer.getShort();
        short second = buffer.getShort();
        short millisecond = buffer.getShort();

        this.time = LocalDateTime.of(year, month, day, hour, minute, second, millisecond * 1000000);
        this.orbitNumber = buffer.getShort();
        this.subOrbitType = buffer.getShort();

        byte[] tmStation = new byte[8];
        buffer.get(tmStation, 0, tmStation.length);
        this.tmStation = new String(tmStation, CHARSET);

        this.sftVersion = buffer.get();
        this.sftSubVersion = buffer.get();
        this.calVersion = buffer.get();
        this.calSubVersion = buffer.get();

        this.geocLat = buffer.getFloat();
        this.geocLong = buffer.getFloat();
        this.altitude = buffer.getFloat();
        this.localTime = buffer.getFloat();
        this.geomagLat = buffer.getFloat();
        this.geomagLong = buffer.getFloat();
        this.mlt = buffer.getFloat();
        this.invLat = buffer.getFloat();
        this.mcIlwainL = buffer.getFloat();
        this.conjsatGeocLat = buffer.getFloat();
        this.conjsatGeocLong = buffer.getFloat();
        this.nconj110GeocLat = buffer.getFloat();
        this.nconj110GeocLong = buffer.getFloat();
        this.sconj110GeocLat = buffer.getFloat();
        this.sconj110GeocLong = buffer.getFloat();
        this.component = new float[]{buffer.getFloat(), buffer.getFloat(), buffer.getFloat()};
        this.gyrofreq = buffer.getFloat();
        this.solarPosition = new float[]{buffer.getFloat(), buffer.getFloat(), buffer.getFloat()};

        this.sftVersion1 = buffer.get();
        this.sftSubVersion1 = buffer.get();

        this.aIJ = new float[]{buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat()};
        this.bIJ = new float[]{buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat(), buffer.getFloat()};

        this.quality = buffer.getShort();

        this.sftVersion2 = buffer.get();
        this.sftSubVersion2 = buffer.get();

        byte[] dataType = new byte[21];
        buffer.get(dataType, 0, dataType.length);
        this.dataType = new String(dataType, CHARSET);

        this.hk = new byte[32];
        buffer.get(this.hk, 0, this.hk.length);

        byte[] coordSyst = new byte[9];
        buffer.get(coordSyst, 0, coordSyst.length);
        this.coordSyst = new String(coordSyst, CHARSET);

        byte[] dataUnit = new byte[16];
        buffer.get(dataUnit, 0, dataUnit.length);
        this.dataUnit = new String(dataUnit, CHARSET);

        this.freq = buffer.getFloat();
        this.ns = buffer.getShort();
        this.tDur = buffer.getFloat();

        byte[] nam1c = new byte[3];
        buffer.get(nam1c, 0, nam1c.length);
        this.nam1c = new String(nam1c, CHARSET);

        this.wf1 = new float[8192];
        for (int i = 0; i < this.wf1.length; i++) {
            this.wf1[i] = buffer.getFloat();
        }
    }
}
