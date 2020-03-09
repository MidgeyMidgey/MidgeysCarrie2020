
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;

public class GetBall extends CommandBase {
  
  private static final double TARGET_AREA = 25;
  private static final double MAX_AREA_ABS_ERROR = 1;

  private DriveSubsystem m_robotDrive;
  private LimelightSubsystem m_limelight;

  public GetBall(LimelightSubsystem limelight, DriveSubsystem robotDrive) {
    m_limelight = limelight;
    m_robotDrive = robotDrive;
    addRequirements(m_limelight, m_robotDrive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double minValue = 0.008; //0.001
    double speed = 0.0;
    double heading = 0.0;
    if (m_limelight.hasTarget()){
        speed = (TARGET_AREA - m_limelight.getArea()) * 0.50;
        if(speed > 0){
          speed += minValue;
        } else if (speed < 0){
          speed -= minValue;
        }
        
        heading = m_limelight.getX() * 0.04;
        if (heading > 1){
          heading -= minValue;
          speed += 0.1;
        } else if (heading < -1) {
          heading += minValue;
          speed += 0.1;
        }
        speed = MathUtil.clamp(speed, -0.60, 0.60);
        heading = MathUtil.clamp(-heading, -1, 1);
    }
    m_robotDrive.arcadeDrive(speed, -heading);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
