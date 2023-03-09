// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double trackWidth = Units.inchesToMeters(27.00);
    public static final double wheelBase = Units.inchesToMeters(25.00);

    public static final SerialPort.Port navXPort = SerialPort.Port.kMXP;

    public static final int frontLeftDrive = 40;
    public static final int frontLeftSteer = 41;
    public static final int frontLeftEncoder = 3;
    public static final double frontLeftOffset = -Math.toRadians(0.0);

    public static final int frontRightDrive = 31;
    public static final int frontRightSteer = 30;
    public static final int frontRightEncoder = 2;
    public static final double frontRightOffset = -Math.toRadians(0.0);

    public static final int backLeftDrive = 21;
    public static final int backLeftSteer = 20;
    public static final int backLeftEncoder = 1;
    public static final double backLeftOffset = -Math.toRadians(0.0);

    public static final int backRightDrive = 11;
    public static final int backRightSteer = 10;
    public static final int backRightEncoder = 0;
    public static final double backRightOffset = -Math.toRadians(0.0);
}
