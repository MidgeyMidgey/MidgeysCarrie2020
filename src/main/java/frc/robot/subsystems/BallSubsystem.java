/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class BallSubsystem extends SubsystemBase {
  public VictorSPX ballCollect = new VictorSPX(5);

  public VictorSPX ballBelt = new VictorSPX(6);

  public VictorSPX index = new VictorSPX(7);
  public VictorSPX flyWheel = new VictorSPX(8);

  public DigitalInput flywheelA = new DigitalInput(0);
  public DigitalInput flywheelB = new DigitalInput(1);

  public Encoder flywheelEncoder = new Encoder(flywheelA, flywheelB);

  public DigitalInput ballIntakeSensor = new DigitalInput(9);
  public DigitalInput indexSensor = new DigitalInput(6);

  public DigitalInput ballBeltA = new DigitalInput(4);
  public DigitalInput ballBeltB = new DigitalInput(5);

  public Encoder ballBeltEncoder = new Encoder(ballBeltA, ballBeltB);

  public DigitalInput indexA = new DigitalInput(2);
  public DigitalInput indexB = new DigitalInput(3);

  public Encoder indexEncoder = new Encoder(indexA, indexB);
  
  //public static int powerCellsHeld = 3;

  public BallSubsystem() {
    flywheelEncoder.reset();
  }

  public void setBallCollectSpeed(double speed){
    ballCollect.set(ControlMode.PercentOutput, speed);
  }

  public int getBallBeltEncoder(){
    return ballBeltEncoder.get();
  }

  public boolean intakeON(){
    if(ballCollect.getMotorOutputPercent() > 0){
      return true;
    } else{
      return false;
    }
  }

  public void setBallBeltSpeeds(double speed){
    ballBelt.set(ControlMode.PercentOutput, speed);
  }

  public void setFlywheelSpeed(double speed){
    flyWheel.set(ControlMode.PercentOutput, speed);
  }

  public boolean flywheelUpToSpeed(){
    return (flywheelEncoder.getRate()/2048)*60 > 6500;
  }

  public void setIndexSpeed(double speed){
    index.set(ControlMode.PercentOutput, speed);
  }

  public boolean indexON(){
    if (index.getMotorOutputPercent() > 0){
      return true;
    } else{
        return false;
    }
  }

  public void indexOneBallMovement(double speed){
    double initial = indexEncoder.get();
    double target = initial + 1024; // half rotation; move to constants
    while(indexEncoder.get()<target){
      index.set(ControlMode.PercentOutput, speed);
    }
  }

  public boolean intakeHasBall(){
    return !ballIntakeSensor.get();
  }

  public boolean indexHasBall(){
    return !indexSensor.get();
  }

  @Override
  public void periodic() {
    
  }
}
