/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.AutoBeltCommand;
import frc.robot.commands.BallBeltEncoderCommand;
import frc.robot.commands.BallEjectCommand;
import frc.robot.commands.BallIntakeCommand;
import frc.robot.commands.BeltOnlyTesterCommand;
import frc.robot.commands.LimelightDistanceCommand;
import frc.robot.commands.OneIndexBallCommand;
//import frc.robot.commands.OneIndexBallCommand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.FlywheelStartCommand;
import frc.robot.commands.GetBall;
//import frc.robot.commands.FlywheelStartCommand;
import frc.robot.commands.IRSensorCommand;
import frc.robot.commands.TurnInplaceCommand;
import frc.robot.commands.WinchCommand;
import frc.robot.commands.SequentialDriveExampleCommand;
import frc.robot.commands.ThrowUpCommand;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.NavxSubsystem;

public class RobotContainer {
  public static XboxController driverXBox = new XboxController(1);
  public static XboxController manipulatorXBox = new XboxController(2);

  public static DriveSubsystem m_robotDrive = new DriveSubsystem();
  public static LimelightSubsystem m_limelight = new LimelightSubsystem();
  public static BallSubsystem m_ballSubsystem = new BallSubsystem();
  public ClimberSubsystem m_climberSubsystem;
  public static NavxSubsystem m_navxSubsystem = new NavxSubsystem();
  // ^ This is where we make our subsystems into instances!

  public static AutoBeltCommand m_autoBeltCommand = new AutoBeltCommand(m_ballSubsystem);
  public static SequentialDriveExampleCommand m_DriveExampleCommand = new SequentialDriveExampleCommand(m_robotDrive, m_limelight);

  private static final int A_BUTTON_XBOX = 1;
  private static final int B_BUTTON_XBOX = 2;
  private static final int X_BUTTON_XBOX = 3;
  private static final int Y_BUTTON_XBOX = 4;
  private static final int LEFT_BUMPER_XBOX = 5;
  private static final int RIGHT_BUMPER_XBOX = 6;
  private static final int BACK_ARROW = 7;
  private static final int START_ARROW = 8;
  private static final int JOYSTICK_LEFT_CLICK = 9;
  private static final int JOYSTICK_RIGHT_CLICK = 10;

  public RobotContainer() {
    m_climberSubsystem = new ClimberSubsystem(manipulatorXBox);
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
        new RunCommand(() -> m_robotDrive.tankDrive(-driverXBox.getRawAxis(1), -driverXBox.getRawAxis(5)), m_robotDrive));
  }
        
    // ^ Setting the Default Command to m_robotDrive, meaning it will drive as long
    // as nothing else is scheduled

    //m_climberSubsystem.setDefaultCommand(
        //new RunCommand(() -> m_climberSubsystem.setClimberSpeed(-manipulatorXBox.getRawAxis(0)));
  //}

  private void configureButtonBindings() {

    JoystickButton ballIntakeCommandButton = new JoystickButton(driverXBox, LEFT_BUMPER_XBOX);
    ballIntakeCommandButton.toggleWhenPressed(new BallIntakeCommand(m_ballSubsystem));

    JoystickButton ballEjectCommandButton = new JoystickButton(driverXBox, RIGHT_BUMPER_XBOX);
    ballEjectCommandButton.toggleWhenPressed(new BallEjectCommand(m_ballSubsystem));

    JoystickButton autoDistanceButton = new JoystickButton(driverXBox, X_BUTTON_XBOX);
    autoDistanceButton.whileHeld(new LimelightDistanceCommand(m_limelight, m_robotDrive));

    JoystickButton limelightTurn = new JoystickButton(driverXBox, A_BUTTON_XBOX);
    limelightTurn.whileHeld(new GetBall(m_limelight, m_robotDrive));


    //JoystickButton driveDistanceCommandButton = new JoystickButton(driverXBox, X_BUTTON_XBOX);
    //driveDistanceCommandButton.whenPressed(new DriveDistanceCommand(60, 1, m_robotDrive));

    //JoystickButton turnInplaceCommandButton = new JoystickButton(driverXBox, Y_BUTTON_XBOX);
    //turnInplaceCommandButton.whenPressed(new TurnInplaceCommand(45, .25, m_robotDrive));
  
    JoystickButton flywheelStarButton = new JoystickButton(manipulatorXBox, X_BUTTON_XBOX);
    flywheelStarButton.toggleWhenPressed(new FlywheelStartCommand(m_ballSubsystem, 1000));

    JoystickButton oneIndexBallCommandButton = new JoystickButton(manipulatorXBox, A_BUTTON_XBOX);
    oneIndexBallCommandButton.whileHeld(new OneIndexBallCommand(m_ballSubsystem));

    //JoystickButton BeltOnlyTesterCommandButton = new JoystickButton(manipulatorXBox, X_BUTTON_XBOX);
    //BeltOnlyTesterCommandButton.whenPressed(new (m_ballSubsystem));

    JoystickButton IRSystemButton = new JoystickButton(manipulatorXBox, RIGHT_BUMPER_XBOX);
    IRSystemButton.whenPressed(new AutoBeltCommand(m_ballSubsystem));
 
    JoystickButton BeltTesterButton = new JoystickButton(manipulatorXBox, LEFT_BUMPER_XBOX);
    BeltTesterButton.whileHeld(new BeltOnlyTesterCommand(m_ballSubsystem));

    //JoystickButton ShootAllBalls = new JoystickButton(driverXBox, Y_BUTTON_XBOX);
    //ShootAllBalls.whenPressed(new TeleShootCommand(m_ballSubsystem));
    
    JoystickButton ThrowUpButton = new JoystickButton(manipulatorXBox, START_ARROW);
    ThrowUpButton.whileHeld(new ThrowUpCommand(m_ballSubsystem));

    JoystickButton BeltOneBall = new JoystickButton(manipulatorXBox, BACK_ARROW);
    BeltOneBall.whenPressed(new BallBeltEncoderCommand(m_ballSubsystem));

    //JoystickButton shootBalls = new JoystickButton(manipulatorXBox, )

    JoystickButton WinchGoButton = new JoystickButton(driverXBox, B_BUTTON_XBOX);
    WinchGoButton.whileHeld(new WinchCommand(m_climberSubsystem));
  }
}
