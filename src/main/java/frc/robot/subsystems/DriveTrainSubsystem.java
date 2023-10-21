// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrainSubsystem extends SubsystemBase {

  //left motors
  CANSparkMax leftMotor1 = new CANSparkMax(Constants.DriveTrainConstants.leftMotor1ID, MotorType.kBrushless);
  CANSparkMax leftMotor2 = new CANSparkMax(Constants.DriveTrainConstants.leftMotor2ID, MotorType.kBrushless);
  //right motors
  CANSparkMax rightMotor1 = new CANSparkMax(Constants.DriveTrainConstants.rightMotor1ID, MotorType.kBrushless);
  CANSparkMax rightMotor2 = new CANSparkMax(Constants.DriveTrainConstants.rightMotor2ID, MotorType.kBrushless);
  //encoders
  RelativeEncoder leftEncoder = leftMotor1.getEncoder();
  RelativeEncoder rightEncoder = rightMotor1.getEncoder();
  //Controller groups
  MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftMotor1, leftMotor2/* , leftMotor3*/);
  MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotor1, rightMotor2/* , rightMotor3*/);
  //drive train
  DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);



  public DriveTrainSubsystem(){ 

    leftMotor1.restoreFactoryDefaults();
    leftMotor2.restoreFactoryDefaults();
    rightMotor1.restoreFactoryDefaults();
    rightMotor2.restoreFactoryDefaults();

    leftMotor1.setVoltage(Constants.DriveTrainConstants.maxVoltage);
    rightMotor1.setVoltage(Constants.DriveTrainConstants.maxVoltage);

    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);

    
    rightControllerGroup.setInverted(true);
    leftControllerGroup.setInverted(false);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {

    return runOnce(
        () -> {

        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.print(leftMotor1.getBusVoltage());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    SmartDashboard.putNumber("Speed", leftMotor1.getEncoder().getPosition());
  }
  public void arcadeDrive(double fwd, double rot) {
    differentialDrive.arcadeDrive(fwd, rot);
  }
  // Assuming this method is part of a drivetrain subsystem that provides the necessary methods
}
