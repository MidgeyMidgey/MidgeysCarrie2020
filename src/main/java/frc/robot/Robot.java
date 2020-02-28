/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoBaselineCommand;
import frc.robot.commands.AutoWallRightCommand;
import frc.robot.commands.DriveDistanceCommand;
import frc.robot.commands.SequentialDriveExampleCommand;

public class Robot extends TimedRobot {
  private Command m_autoCommand;
  public RobotContainer m_robotContainer;
  double autoVar = 0.0;
  public static double flyWheelRPM;


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    SmartDashboard.putNumber("Auto mode", autoVar);
  }

  public Command getAutoCommand(){
    if (SmartDashboard.getNumber("Auto mode", autoVar) == 1.0){
      return new AutoWallRightCommand(m_robotContainer.m_robotDrive);
    }
    return new AutoBaselineCommand(m_robotContainer.m_robotDrive, m_robotContainer.m_limelight);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    flyWheelRPM = (RobotContainer.m_ballSubsystem.flywheelEncoder.getRate()/2048)*60;

    SmartDashboard.putBoolean("limelight has target", RobotContainer.m_limelight.hasTarget());
    SmartDashboard.putNumber("Limelight tx", RobotContainer.m_limelight.getX());
    SmartDashboard.putNumber("Limelight ty", RobotContainer.m_limelight.getY());
    SmartDashboard.putNumber("Limelight ta", RobotContainer.m_limelight.getArea());
    
    SmartDashboard.putNumber("Flywheel Revolutions per min", (flyWheelRPM));
    SmartDashboard.putNumber("Flywheel distance", RobotContainer.m_ballSubsystem.flywheelEncoder.getDistance());

    SmartDashboard.putBoolean("Intake got ball", RobotContainer.m_ballSubsystem.intakeHasBall());
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autoCommand = getAutoCommand();
    System.out.println(m_autoCommand.toString());
    if(m_autoCommand != null){
      m_autoCommand.schedule();
      SmartDashboard.putString("temp1", "Auto init working");
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
     if (m_autoCommand != null) {
       m_autoCommand.cancel();
     }
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
