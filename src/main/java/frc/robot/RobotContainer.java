/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.BallEjectCommand;
import frc.robot.commands.BallIntakeCommand;
import frc.robot.commands.LimelightAutoTrackCommand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.TurnInplaceCommand;
import frc.robot.commands.SequentialDriveExampleCommand;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.command.SequentialDriveCommand;
import frc.robot.command.TurnInPlaceCommand;
import frc.robot.command.DistanceDriveCommand;

public class RobotContainer {
  public static XboxController driverXBox = new XboxController(1);
  public static XboxController manipulatorXBox = new XboxController(2);
  // ^ FIX: Check to make sure manipulatorXBox is on the right port

  public static LimelightSubsystem m_limelight = new LimelightSubsystem();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final BallSubsystem m_ballSubsystem = new BallSubsystem();
  // ^ This is where we make our subsystems into instances!

  private static final int A_BUTTON_XBOX = 1;
  private static final int B_BUTTON_XBOX = 2;
  private static final int X_BUTTON_XBOX = 3;
  private static final int Y_BUTTON_XBOX = 4;
  private static final int LEFT_BUMPER_XBOX = 5;
  private static final int RIGHT_BUMPER_XBOX = 6;
  private static final int BACK_ARROW = 7;
  private static final int START_ARROW = 8;
  private static final int JOYSTICK_RIGHT_CLICK = 10;
  private static final int JOYSTICK_LEFT_CLICK = 9;

  public RobotContainer() {
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
      new RunCommand(() -> m_robotDrive
        .tankDrive(driverXBox.getRawAxis(1), driverXBox.getRawAxis(5)),
          m_robotDrive));
    // ^ Setting the Default Command to m_robotDrive, meaning it will drive as long as nothing else is scheduled
  }

  private void configureButtonBindings() {

    JoystickButton ballEjectCommandButton = new JoystickButton(driverXBox, RIGHT_BUMPER_XBOX);
    ballEjectCommandButton.toggleWhenPressed(new BallEjectCommand(m_ballSubsystem));

    JoystickButton driveDistanceCommandButton = new JoystickButton(driverXBox, X_BUTTON_XBOX);
    driveDistanceCommandButton.whenPressed(new DriveDistanceCommand(10, 0.2, m_robotDrive));

    JoystickButton turnInplaceCommandButton = new JoystickButton(driverXBox, Y_BUTTON_XBOX);
    turnInplaceCommandButton.whenPressed(new TurnInplaceCommand(10, 0.2, m_robotDrive));

    JoystickButton sequentialDriveCommandButton = new JoystickButton(driverXBox, A_BUTTON_XBOX);
    sequentialDriveCommandButton.whenPressed(new SequentialDriveExampleCommand(m_robotDrive));
  }

  /*
   public Command getAutonomousCommand() {
     // An ExampleCommand will run in autonomous
     return m_autoCommand;
  }
  */

  // ^ Example of an Autonomous Command, could come in handy later! 
}
