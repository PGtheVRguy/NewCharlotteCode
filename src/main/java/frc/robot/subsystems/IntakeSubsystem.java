// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax IntakeMotor1 = new CANSparkMax(Constants.IntakeConstants.intakeMotor1ID, MotorType.kBrushed);
  CANSparkMax IntakeMotor2 = new CANSparkMax(Constants.IntakeConstants.intakeMotor2ID, MotorType.kBrushed);
  //RelativeEncoder IntakeEncoder = IntakeMotor1.getEncoder();
  //RelativeEncoder IntakeEncoder2 = IntakeMotor2.getEncoder();


  MotorControllerGroup IntakeControllerGroup = new MotorControllerGroup(IntakeMotor1, IntakeMotor2);
  //MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotor1, rightMotor2, rightMotor3);

  //DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

  public IntakeSubsystem(){ 

    IntakeMotor1.restoreFactoryDefaults();
    IntakeMotor2.restoreFactoryDefaults();
    IntakeControllerGroup.setInverted(false);
  }
  public CommandBase exampleMethodCommand() {

    return runOnce(
        () -> {

        });
  }

  public boolean exampleCondition() {
    return false;
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }

  public void runGripSpeed(double speed){
    IntakeMotor1.set(speed);
    IntakeMotor2.set(speed);
  }
}
