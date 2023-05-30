// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class MoveArmSubsystem extends SubsystemBase {
  CANSparkMax armMotor = new CANSparkMax(Constants.MoveArmConstants.ArmMotorID, MotorType.kBrushed);
  //Encoder encoder = new Encoder(0, 1);
  //RelativeEncoder armEncoder = armMotor.getEncoder();

  public MoveArmSubsystem() {

    armMotor.restoreFactoryDefaults();

    //armEncoder.setPosition(0);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(armEncoder.getPosition());
  }

  public void armDown() {
   // armEncoder.setPosition(-1);
   armMotor.set(0.5);
  }
  public void armUp() {
    // armEncoder.setPosition(-1);
    armMotor.set(-0.5);
   }
  public void forceStopArm() {
    armMotor.set(0);
   // armEncoder.setPosition(-1);
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
