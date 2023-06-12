// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class MoveArmSubsystem extends SubsystemBase {
  CANSparkMax armMotor = new CANSparkMax(Constants.MoveArmConstants.ArmMotorID, MotorType.kBrushless); //sets up Arm motor

  RelativeEncoder armEncoder = armMotor.getEncoder(); //sets armEncoder to the motors encoder

  public MoveArmSubsystem() {

    armMotor.restoreFactoryDefaults(); //i dunno was told to do dis 

    armEncoder.setPosition(0); //I dunno I'll figure that out.

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(armEncoder.getPosition());
    SmartDashboard.putNumber("Arm Pos", armEncoder.getPosition());
  }

  public void armDown() {
   armMotor.set(0.5); //tells the motor "ayo start moving down" at 1/2 speed.
   //System.out.println("Moving down, pos: "+ armEncoder);
  }
  public void armUp() {
    armMotor.set(-0.5); //tells the motor "ayo start moving up"
    //System.out.println("Moving up, pos: "+ armEncoder);
   }
  public void forceStopArm() {
    armMotor.set(0);
  }

  //cool stuff that tells the arm to go to preset pos.
  public void aimHigh() {
   // armEncoder.setPosition(0);
  }
  public void armMid() {
   // armEncoder.setPosition(-0.5);
  }
  public void armLow() {
   // armEncoder.setPosition(-1);
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
}

}
