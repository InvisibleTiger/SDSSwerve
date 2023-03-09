// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double trackWidth = 1.0;
    public static final double wheelBase = 1.0;

    public static final SerialPort.Port navXPort = SerialPort.Port.kMXP;

    public static final int frontLeftDrive = 0;
    public static final int frontLeftSteer = 0;
    public static final int frontLeftEncoder = 0;
    public static final double frontLeftOffset = -Math.toRadians(0.0);

    public static final int frontRightDrive = 0;
    public static final int frontRightSteer = 0;
    public static final int frontRightEncoder = 0;
    public static final double frontRightOffset = -Math.toRadians(0.0);

    public static final int backLeftDrive = 0;
    public static final int backLeftSteer = 0;
    public static final int backLeftEncoder = 0;
    public static final double backLeftOffset = -Math.toRadians(0.0);

    public static final int backRightDrive = 0;
    public static final int backRightSteer = 0;
    public static final int backRightEncoder = 0;
    public static final double backRightOffset = -Math.toRadians(0.0);
}
