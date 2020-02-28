/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallSubsystem;

public class TeleShootCommand extends CommandBase {
  /**
   * Creates a new TeleShootCommand.
   */

  public BallSubsystem m_ballSubsystem;
  public TeleShootCommand(BallSubsystem ballSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ballSubsystem = ballSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      while (SmartDashboard.getNumber("Flywheel Revolutions per minute", 0.0)>0) {
        while (!RobotContainer.m_ballSubsystem.indexHasBall()) {
          RobotContainer.m_ballSubsystem.setBallBeltSpeeds(0.4);
        }
        while (RobotContainer.m_ballSubsystem.indexHasBall()) {
          if (SmartDashboard.getNumber("Flywheel Revolutions per minute", 0.0) > 5500) {
            RobotContainer.m_ballSubsystem.indexOneBallMovement(0.5);
          }
        }
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (SmartDashboard.getNumber("Flywheel Revolutions per minute", 0.0)>0){
      return true;
    }
    else{
      return false;
    }
  }
}
