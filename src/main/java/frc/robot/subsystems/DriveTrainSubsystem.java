// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrainSubsystem extends SubsystemBase {

  CANSparkMax leftMotor1 = new CANSparkMax(Constants.DriveTrainConstants.leftMotor1ID, MotorType.kBrushed);
  CANSparkMax leftMotor2 = new CANSparkMax(Constants.DriveTrainConstants.leftMotor2ID, MotorType.kBrushed);
  CANSparkMax leftMotor3 = new CANSparkMax(Constants.DriveTrainConstants.leftMotor3ID, MotorType.kBrushed);
  CANSparkMax rightMotor1 = new CANSparkMax(Constants.DriveTrainConstants.rightMotor1ID, MotorType.kBrushed);
  CANSparkMax rightMotor2 = new CANSparkMax(Constants.DriveTrainConstants.rightMotor2ID, MotorType.kBrushed);
  CANSparkMax rightMotor3 = new CANSparkMax(Constants.DriveTrainConstants.rightMotor3ID, MotorType.kBrushed);

  //RelativeEncoder leftEncoder = leftMotor1.getEncoder();
  //RelativeEncoder rightEncoder = rightMotor1.getEncoder();


  MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftMotor1, leftMotor2, leftMotor3);
  MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotor1, rightMotor2, rightMotor3);

  DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);



  public DriveTrainSubsystem(){ 

    leftMotor1.restoreFactoryDefaults();
    leftMotor2.restoreFactoryDefaults();
    leftMotor3.restoreFactoryDefaults();
    rightMotor1.restoreFactoryDefaults();
    rightMotor2.restoreFactoryDefaults();
    rightMotor3.restoreFactoryDefaults();

   // leftMotor1.setVoltage(12);


   // leftEncoder.setPosition(0);
   // rightEncoder.setPosition(0);

    leftMotor2.follow(leftMotor1);
    leftMotor3.follow(leftMotor1);
  
    rightMotor2.follow(rightMotor1);
    rightMotor3.follow(rightMotor1);

    System.out.println("Assigning motors");
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
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void arcadeDrive(double fwd, double rot) {
    differentialDrive.arcadeDrive(fwd, rot);
  }
}
