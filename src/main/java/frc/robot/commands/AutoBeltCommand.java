/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBeltCommand extends CommandBase {
  
  BallSubsystem m_ballSubsystem;

  public AutoBeltCommand(BallSubsystem ballSubsystem) {
    m_ballSubsystem = ballSubsystem;
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}