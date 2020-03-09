/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class WinchCommand extends CommandBase {
  
  private ClimberSubsystem m_climberSubsystem;

  public WinchCommand (ClimberSubsystem climberSubsystem) {
    m_climberSubsystem = climberSubsystem;
    addRequirements(m_climberSubsystem); 
  }

  @Override
  public void initialize() {
    m_climberSubsystem.setWinchSpeed(Constants.CLIMBER_WINCH_SPEED);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    m_climberSubsystem.setWinchSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
