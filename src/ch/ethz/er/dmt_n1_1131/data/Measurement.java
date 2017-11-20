package ch.ethz.er.dmt_n1_1131.data;

import java.util.Date;

public class Measurement {

    /*
    UTC time (calendar format) of the first point of the data array
     */
    Date date;

    /*
    Definition : Orbit number
    Type : Integer [1 .. 65535]
    Length : 16 bits
     */
    int orbitNumber;

    /*
    Definition : Sub orbit type : 0=downward ; 1= upward
    Type : Integer [0 .. 1]
    Length : 16 bits
     */
    byte subOrbitType;

    /*
    Definition : Telemetry station : "TOULOUSE"
    Type : 8 Characters
    Length : 64 bits
     */
    String tmStation;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftVersion;

    /*
    Definition : Sub-version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftSubVersion;

    /*
    Definition : Version (edition number) of the calibration file : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte calVersion;

    /*
    Definition : Sub-version (revision number) of the calibration file : from 0 to 63
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte calSubVersion;

    /*
    Definition : Geocentric latitude (-90° , +90°)
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float geocLat;

    /*
    Definition : Geocentric longtitude (0° , 360°)
    Unit :"deg"
    Type : Real [0 .. 360]
    Length : 32 bits
     */
    float geocLong;

    /*
    Definition : Altitude
    Unit :"km"
    Type : Real
    Length : 32 bits
     */
    float altitude;

    /*
    Definition : Local time of the first point of the data array (0 , 24 h)
    Unit :"h_(fractional_hour)"
    Type : Real [0.0 .. 24.0]
    Length : 32 bits
     */
    float localTime;

    /*
    Definition : Geomagnetic latitude (-90° , +90°)
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float geomagLat;

    /*
    Definition : Geomagnetic longitude (0° , +360°)
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    float geomagLong;

    /*
    Definition : Magnetic local time of the first point
    Unit :"h_(fractional_hour)"
    Type : Real [0.0 .. 24.0]
    Length : 32 bits
     */
    float mlt;

    /*
    Definition : Invariant latitude (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float invLat;

    /*
    Definition : Mc Ilwain parameter L (0 , 999)
    Type : Real
    Length : 32 bits
     */
    float mcIlwainL;

    /*
    Definition : Geocentric latitude of the conjugate point at the satellite altitude (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float conjsatGeocLat;

    /*
    Definition : Geocentric longitude of the conjugate point at the satellite altitude (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    float conjsatGeocLong;

    /*
    Definition : Geocentric latitude of North conjugate point at altitude 110 km (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float nconj110GeocLat;

    /*
    Definition : Geocentric longitude of North conjugate point at altitude 110 km (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    float nconj110GeocLong;

    /*
    Definition : Geocentric latitude of South conjugate point at altitude 110 km (-90° , +90°).
    Unit :"deg"
    Type : Real [-90.0 .. +90.0]
    Length : 32 bits
     */
    float sconj110GeocLat;

    /*
    Definition : Geocentric longitude of South conjugate point at altitude 110 km (0° , +360°).
    Unit :"deg"
    Type : Real [0.0 .. +360.0]
    Length : 32 bits
     */
    float sconj110GeocLong;

    /*
    Definition : Components (X, Y, Z) of the magnetic field model at the satellite point (geographic coordinate system)
    Unit :"nT"
    Type : ARRAY with 3 items
    Length : 96 bits
     */
    float[] component;

    /*
    Definition : Proton gyrofrequency at the satellite point
    Unit :"Hz"
    Type : Real
    Length : 32 bits
     */
    float gyrofreq;

    /*
    Definition : Solar position (Xs, Ys, Zs) in the geographic coordinate system
    Unit :"AU"
    Type : ARRAY with 3 items
    Length : 96 bits
     */
    float[] solarPosition;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftVersion1;

    /*
    Definition : Sub version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftSubVersion1;

    /*
    Definition : Matrix from satellite coordinate system to geographic coordinate system.
    Note : The elements a_ij of the matrix are stored as a_11, a_12, a_13, a_21, a_22, a_23, a_31, a_32, a_33 where i is the raw index and j the column index of the element a_ij.
    Type : ARRAY with 3","1 .. 3 items
    Length : 288 bits
     */
    float[] aIJ;

    /*
    Definition : Matrix from geographic coordinate system to local geomagnetic coordinate system.
    Note : The elements b_ij of the matrix are stored as b_11, b_12, b_13, b_21, b_22, b_23, b_31, b_32, b_33 where i is the raw index and j the column index of the element b_ij.
    Type : ARRAY with 3","1 .. 3 items
    Length : 288 bits
     */
    float[] bIJ;

    /*
    Definition : Quality index of the attitude parameters :
    0 = attitude not available (matrix coefficients = 99999.)
    1 = measured attitude (very rare occurrence)
    2 = computed attitude (normal occurrence)
    Type : Integer [0 .. 2]
    Length : 16 bits
     */
    byte quality;

    /*
    Definition : Version (edition number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftVersion2;

    /*
    Definition : Sub version (revision number) of the processing software : from 0 to 9
    Type : Integer [0 .. 9]
    Length : 8 bits
     */
    byte sftSubVersion2;

    public Measurement() {
    }
}
