// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.*;
import frc.robot.commands.IntakeCommand;




public class MoveArmSubsystem extends SubsystemBase {
  CANSparkMax armMotor = new CANSparkMax(MoveArmConstants.ArmMotorID, MotorType.kBrushless); //sets up Arm motor
  private SparkMaxPIDController armPID;
  
  public RelativeEncoder armEncoder = armMotor.getEncoder(); //sets armEncoder to the motors encoder

  private PIDController PID;


  public MoveArmSubsystem() {
    armPID = armMotor.getPIDController();
    armMotor.restoreFactoryDefaults(); //i dunno was told to do dis 
    armEncoder.setPosition(0); //Sets the current position of the arm to "0".

    PID = new PIDController(MoveArmConstants.armkP, MoveArmConstants.armkI, MoveArmConstants.armkD);
    PID.setTolerance(5);
    PID.setSetpoint(0);
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(armEncoder.getPosition());
    SmartDashboard.putNumber("Arm Pos", armEncoder.getPosition());

    /*if(startMovingMid = true){
      armPID.setReference(91, ControlType.kPosition);
      if(RobotContainer.between(90, 91, armEncoder.getPosition())) 
      {
        startMovingMid = false;
      }
    }*/

  }

  public void zeroArm() {
    armEncoder.setPosition(0);
  }
  public void armDown() {
   armMotor.set(0.5); //tells the motor "ayo start moving down" at 1/2 speed.
   
   //System.out.println("Moving down, pos: "+ armEncoder);
  }
  public void armUp() {
    armMotor.set(-0.5); //tells the motor "ayo start moving up"
    //System.out.println("Moving up, pos: "+ armEnco der);
   }
  public void forceStopArm() {
    armMotor.set(0);
  }

  //cool stuff that tells the arm to go to preset pos.
  public void armHigh() {
    IntakeCommand.shootPower = 1.2;
    armPID.setReference(0, ControlType.kPosition);
  }

  public void armMid() {
    IntakeCommand.shootPower = 0.6;
    armPID.setReference(91, ControlType.kPosition);
  }

  public void armLow() {
    IntakeCommand.shootPower = 0.5;
    armPID.setReference(269, ControlType.kPosition); //the set reference values are all preset to cool stuff, PLEASE SET CAREFULLY!
  }

  public void armSetPID(int ref, double power) {
    armPID.setReference(power, ControlType.kPosition);
    IntakeCommand.shootPower = ref;
  }

  public void moveArm(double val) {
    double currentArmPos = armMotor.get();

    double targetAngleClamped = MathUtil.clamp(val, 0, 269); //the clamped positions
    double targetAnglePID = MathUtil.clamp(PID.calculate(currentArmPos, targetAngleClamped), -MoveArmConstants.maxPIDArmSpeed,MoveArmConstants.maxPIDArmSpeed);
    armMotor.set(targetAnglePID); 
    
    //PID mostly stolen from TitaniumTitans 2023 code, thanks Rowan :3
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
}

}
