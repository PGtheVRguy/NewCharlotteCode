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
  public static double shootPower = 1.00;
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
    
    IntakeMotor2.follow(IntakeMotor1);



    //IntakeEncoder1.setPosition(0);
    //IntakeEncoder2.setPosition(0);

    IntakeControllerGroup.setInverted(false);
    //leftControllerGroup.setInverted(false);
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

public void runGripIn(/*double input*/){
  IntakeMotor1.set(-0.25);
}
public void runGripOut(/*double input*/){
  IntakeMotor1.set((Constants.IntakeConstants.outakeSpeed)*shootPower);
}
public void stopGrip(){
  IntakeMotor1.set(0);
}
public void runGripMax(){
  IntakeMotor1.set(Constants.IntakeConstants.outakeSpeed);
}
//public double getGripPos(){
//  return IntakeMotor1.getEncoder().getPosition();
//}

  //public void arcadeDrive(double fwd, double rot) {
  //  differentialDrive.arcadeDrive(fwd, rot);
  //}
}
