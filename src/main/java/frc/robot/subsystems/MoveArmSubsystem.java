// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import frc.robot.commands.IntakeCommand;




public class MoveArmSubsystem extends SubsystemBase {
  CANSparkMax armMotor = new CANSparkMax(MoveArmConstants.ArmMotorID, MotorType.kBrushless); //sets up Arm motor
  public RelativeEncoder armEncoder = armMotor.getEncoder(); //sets armEncoder to the motors encoder
  private PIDController PID;


  public MoveArmSubsystem() {
    armMotor.restoreFactoryDefaults(); //i dunno was told to do dis 
    armEncoder.setPosition(0); //Sets the current position of the arm to "0".

    PID = new PIDController(MoveArmConstants.armkP, MoveArmConstants.armkI, MoveArmConstants.armkD);
    PID.setTolerance(5);
    //PID.setSetpoint(0);
    

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Pos", armEncoder.getPosition());
  }

  public void zeroArm() {
    armEncoder.setPosition(0);
  }
  public void armDown() {
   armMotor.set(0.5); //tells the motor "ayo start moving down"
  }
  public void armUp() {
    armMotor.set(-0.5); //tells the motor "ayo start moving up"
  }
  public void forceStopArm() {
    armMotor.set(0);
  }

  public void armSetPID(int ref, double power) { //gets the chosen reference destination and intake power for the arm and throws the ref des into a PID
    double currentArmPos = armEncoder.getPosition();

    double targetAngleClamped = MathUtil.clamp(ref, 5, 240); //the clamped positions
    double targetAnglePID = MathUtil.clamp(PID.calculate(currentArmPos, targetAngleClamped), -MoveArmConstants.maxPIDArmSpeed,MoveArmConstants.maxPIDArmSpeed);
    armMotor.set(targetAnglePID); //this is a percentage value of -1.0 to 1.0
    SmartDashboard.putNumber("ARM PID", targetAnglePID);
    IntakeCommand.shootPower = power;
    //armPID.setReference(ref, ControlType.kPosition);
    //PID mostly stolen from TitaniumTitans 2023 code, thanks Rowan :3
  }

  public void moveArm(double val) { //manually moving the arm
    armMotor.set(val);
  }

  @Override
  public void simulationPeriodic() {
}

}
