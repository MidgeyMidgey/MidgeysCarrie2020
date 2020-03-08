/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;

public class NavxSubsystem extends SubsystemBase {

  AHRS m_navx;

  public NavxSubsystem() {
    try {
      m_navx = new AHRS(SPI.Port.kMXP);
      m_navx.zeroYaw();
      register(); // let CommandScheduler know we exist
    } catch (RuntimeException ex ) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }

  public boolean haveNavx() {
    return m_navx != null;
  }

  @Override
  public void periodic() {
    if (!haveNavx()) {
      return;
    }

    SmartDashboard.putBoolean("navX_isConnected", m_navx.isConnected());
    SmartDashboard.putBoolean("navX_isCalibrating", m_navx.isCalibrating());

    // Compatible with wpilibj Gyro Class
    SmartDashboard.putNumber("navX_angle", m_navx.getAngle());
    SmartDashboard.putNumber("navX_yaw_DPS", m_navx.getRate());

    // procesed 6-axis data
    SmartDashboard.putNumber("navX_yaw", m_navx.getYaw());
    SmartDashboard.putNumber("navX_pitch", m_navx.getPitch());
    SmartDashboard.putNumber("navX_roll", m_navx.getRoll());

    // Acceleration data
    SmartDashboard.putNumber("navX_AccelX", m_navx.getWorldLinearAccelX());
    SmartDashboard.putNumber("navX_AccelY", m_navx.getWorldLinearAccelY());

    // Approximate integral & 2nd integral
    SmartDashboard.putNumber("navX_VelocityX", m_navx.getVelocityX());
    SmartDashboard.putNumber("navx_VelocityY", m_navx.getVelocityY());
    SmartDashboard.putNumber("navx_DisplacementX", m_navx.getDisplacementX());
    SmartDashboard.putNumber("navx_DisplacementY", m_navx.getDisplacementY());
  }
}