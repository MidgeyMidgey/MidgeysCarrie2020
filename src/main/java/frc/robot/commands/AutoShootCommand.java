/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.FlywheelStartCommand;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.commands.BallBeltEncoderCommand;

public class AutoShootCommand extends SequentialCommandGroup {
  public AutoShootCommand(int numBalls) {
    int m_numBalls = numBalls;
    BallSubsystem.powerCellsHeld = BallSubsystem.powerCellsHeld - m_numBalls;
    new ParallelDeadlineGroup(shootBall(m_numBalls), new FlywheelStartCommand(RobotContainer.m_ballSubsystem));
  }

  public static SequentialCommandGroup shootBall(int balls){
    int m_balls = balls;
    SequentialCommandGroup shootSequence = new SequentialCommandGroup();
    for(int i = m_balls; i > 0; i--){
      shootSequence.addCommands(
            new FlywheelStartCommand(RobotContainer.m_ballSubsystem),
            new BallBeltEncoderCommand(RobotContainer.m_ballSubsystem),
            new OneIndexBallCommand(RobotContainer.m_ballSubsystem)
      );
    }
    return shootSequence;
  }

}
