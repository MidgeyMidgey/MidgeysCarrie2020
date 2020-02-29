/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallSubsystem;

public class BallBeltEncoderCommand extends CommandBase {
  /**
   * Creates a new BallBeltEncoderCommand.
   */
  public BallSubsystem m_ballSubsystem;
  public BallBeltEncoderCommand(BallSubsystem ballSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ballSubsystem = ballSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_ballSubsystem.ballBelt.set(ControlMode.PercentOutput, Constants.BELT_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_ballSubsystem.ballBelt.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(RobotContainer.m_ballSubsystem.ballBeltEncoder.get()) >= 5;
    //return false;
  }
}
