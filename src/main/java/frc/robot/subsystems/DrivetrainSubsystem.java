// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.kauailabs.navx.frc.AHRS;
import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {
  public static final double maxVoltage = 10.0;
  public static final double maxTranslationVelocityMetersPerSecond = 6380.0 / 60.0 *
          SdsModuleConfigurations.MK3_STANDARD.getDriveReduction() *
          SdsModuleConfigurations.MK3_STANDARD.getWheelDiameter() * Math.PI;
  public static final double maxAngularVelocityRadiansPerSecond = maxTranslationVelocityMetersPerSecond /
          Math.hypot(trackWidth / 2.0, wheelBase / 2.0);

  private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
          // Front left
          new Translation2d(trackWidth / 2.0, wheelBase / 2.0),
          // Front right
          new Translation2d(trackWidth / 2.0, -wheelBase / 2.0),
          // Back left
          new Translation2d(-trackWidth / 2.0, wheelBase / 2.0),
          // Back right
          new Translation2d(-trackWidth / 2.0, -wheelBase / 2.0)
  );
  
  private final AHRS m_navx = new AHRS(Constants.navXPort); // NavX connected over MXP

  private final SwerveModule m_frontLeftModule;
  private final SwerveModule m_frontRightModule;
  private final SwerveModule m_backLeftModule;
  private final SwerveModule m_backRightModule;

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  public DrivetrainSubsystem() {

    m_frontLeftModule = Mk3SwerveModuleHelper.createNeo(
      Mk3SwerveModuleHelper.GearRatio.FAST, 
      frontLeftDrive, 
      frontLeftSteer, 
      frontLeftEncoder, 
      frontLeftOffset
    );

    m_frontRightModule = Mk3SwerveModuleHelper.createNeo(
      Mk3SwerveModuleHelper.GearRatio.FAST, 
      frontRightDrive, 
      frontRightSteer, 
      frontRightEncoder, 
      frontRightOffset
    );

    m_backLeftModule = Mk3SwerveModuleHelper.createNeo(
      Mk3SwerveModuleHelper.GearRatio.FAST, 
      backLeftDrive, 
      backLeftSteer, 
      backLeftEncoder, 
      backLeftOffset
    );

    m_backRightModule = Mk3SwerveModuleHelper.createNeo(
      Mk3SwerveModuleHelper.GearRatio.FAST, 
      backRightDrive, 
      backRightSteer, 
      backRightEncoder, 
      backRightOffset
    );
  }

  public void zeroGyroscope() {
    m_navx.zeroYaw();
  }

  public Rotation2d getGyroscopeRotation() {
    if (m_navx.isMagnetometerCalibrated()) {
      return Rotation2d.fromDegrees(m_navx.getFusedHeading());
    }
    return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  @Override
  public void periodic() {
    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, maxTranslationVelocityMetersPerSecond);

    m_frontLeftModule.set(states[0].speedMetersPerSecond / maxTranslationVelocityMetersPerSecond * maxVoltage, states[0].angle.getRadians());
    m_frontRightModule.set(states[1].speedMetersPerSecond / maxTranslationVelocityMetersPerSecond * maxVoltage, states[1].angle.getRadians());
    m_backLeftModule.set(states[2].speedMetersPerSecond / maxTranslationVelocityMetersPerSecond * maxVoltage, states[2].angle.getRadians());
    m_backRightModule.set(states[3].speedMetersPerSecond / maxTranslationVelocityMetersPerSecond * maxVoltage, states[3].angle.getRadians());
  }
}
