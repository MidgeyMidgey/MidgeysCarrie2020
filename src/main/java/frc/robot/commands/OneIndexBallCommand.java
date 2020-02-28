/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallSubsystem;

public class OneIndexBallCommand extends CommandBase {

  private BallSubsystem m_ballSubsystem;
  private double origin;
  private double target;

  public OneIndexBallCommand(BallSubsystem ballSubsystem) {
    m_ballSubsystem = ballSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    origin = RobotContainer.m_ballSubsystem.indexEncoder.get();
    target = origin + 1024; // half rotation; move to Constants
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ballSubsystem.setIndexSpeed(Constants.BALL_INDEX_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballSubsystem.setIndexSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double current = RobotContainer.m_ballSubsystem.indexEncoder.get();
    return current >= target;
    //return false;
  }
}

