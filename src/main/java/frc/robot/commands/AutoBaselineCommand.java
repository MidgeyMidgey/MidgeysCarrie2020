/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class AutoBaselineCommand extends SequentialCommandGroup {
  /**
   * Creates a new BaselineAuto.
   */
  public AutoBaselineCommand(DriveSubsystem drive, LimelightSubsystem limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    addCommands(
      new DriveDistanceCommand(48, -0.4, drive),
      new LimelightDistanceCommand(limelight, drive),
      new AutoShootCommand(3)
    );
  }
}
