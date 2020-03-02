/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallSubsystem;

public class ThrowUpCommand extends CommandBase {
  
  private BallSubsystem m_ballSubsystem;

  public ThrowUpCommand (BallSubsystem ballSubsystem) {
    m_ballSubsystem = ballSubsystem;
    addRequirements(m_ballSubsystem); 
  }

  @Override
  public void initialize() {
    m_ballSubsystem.setBallBeltSpeeds(Constants.BALL_THROW_UP_SPEED);
    //m_ballSubsystem.setIndexSpeed(Constants.BALL_THROW_UP_SPEED);
    m_ballSubsystem.setBallCollectSpeed(Constants.BALL_THROW_UP_SPEED);
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {
    m_ballSubsystem.setBallBeltSpeeds(0.0);
    //m_ballSubsystem.setIndexSpeed(0.0);
    m_ballSubsystem.setBallCollectSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
