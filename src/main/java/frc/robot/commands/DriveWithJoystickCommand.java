// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** An example command that uses an example subsystem. */
public class DriveWithJoystickCommand extends CommandBase {
  double moveSpeed = 1;
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrainSubsystem driveTrainSubsystem;
  //private double timeWhilePressed = 0.00;
  /**
   * Creates a new ExampleCommand.
   *
   *. @param subsystem The subsystem used by this command.
   */
  public DriveWithJoystickCommand(DriveTrainSubsystem driveTrainSubsystem) {
    this.driveTrainSubsystem = driveTrainSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrainSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    System.out.println("Starting this really cool joystick command");
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    /*if((RobotContainer.m_driverController.getLeftY() != 0))
    {
      if(timeWhilePressed != 1)
      {
        timeWhilePressed += 0.05;
      }
    }
    else{
      timeWhilePressed = 0;
    }*/
    if(RobotContainer.m_driverController.rightBumper().getAsBoolean())
    {
      moveSpeed = 0.5;
    }
    else{
      moveSpeed = 1;
    }
    //THAT STUFF ADDED THAT REALLY COOL TURBO BUTTON!!!
    double forwardSpeed = RobotContainer.deadband(RobotContainer.m_driverController.getLeftY(),0.1);
    double turningSpeed = RobotContainer.m_driverController.getRightX()/2;
    driveTrainSubsystem.arcadeDrive(-forwardSpeed*moveSpeed, -turningSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
