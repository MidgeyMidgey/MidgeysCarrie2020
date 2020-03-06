/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.FlywheelStartCommand;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.commands.BallBeltEncoderCommand;
import frc.robot.subsystems.LimelightSubsystem;

public class MidgeShootBall extends CommandBase {
  BallSubsystem m_ballSubsystem;
  LimelightSubsystem m_limelightSubsystem;
  public MidgeShootBall(BallSubsystem ballSubsystem, LimelightSubsystem LimelightSubsystem) {
     m_ballSubsystem = ballSubsystem;
     m_limelightSubsystem = LimelightSubsystem;
    addRequirements(m_ballSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_ballSubsystem.setFlywheelSpeed(0.25);
    boolean flywheelAtSpeed = (m_ballSubsystem.flywheelEncoder.getRate()/2048)*60 > 6500;
    boolean limelightHasTarget = m_limelightSubsystem.hasTarget();
    if (flywheelAtSpeed && limelightHasTarget) {
      m_ballSubsystem.setBallBeltSpeeds(.15);
      double origin = m_ballSubsystem.indexEncoder.get();
      m_ballSubsystem.setIndexSpeed(Constants.BALL_INDEX_SPEED);
      while (m_ballSubsystem.indexEncoder.get() - origin < 1024) {
        m_ballSubsystem.setIndexSpeed(Constants.BALL_INDEX_SPEED);
      }
      m_ballSubsystem.setIndexSpeed(0.0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_ballSubsystem.setFlywheelSpeed(0.0);
    m_ballSubsystem.setBallBeltSpeeds(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
