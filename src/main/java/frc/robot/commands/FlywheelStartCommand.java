/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.Constants;


public class FlywheelStartCommand extends CommandBase {
 
  private BallSubsystem m_ballSubsystem;
  double percentOutput;
  double m_targetRPM;

  public FlywheelStartCommand (BallSubsystem ballSubsystem, double targetRPM) {
    m_ballSubsystem = ballSubsystem;
    percentOutput = 0.15;
    m_targetRPM = targetRPM;
  }

  @Override
  public void initialize() {
    m_ballSubsystem.setFlywheelSpeed(percentOutput);
  }

  @Override
  public void execute() {
    double rpm = m_ballSubsystem.getFlywheelRPM();
    double error = (m_targetRPM - rpm) * .0001;
    System.out.format("%.5f %.5f %.5f %.5f\n", rpm, m_targetRPM, error, percentOutput);
    percentOutput += error;
    percentOutput = MathUtil.clamp(percentOutput, 0, 0.3);
    m_ballSubsystem.setFlywheelSpeed(.85);
 
  }

  @Override
  public void end(boolean interrupted) {
    m_ballSubsystem.setFlywheelSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
