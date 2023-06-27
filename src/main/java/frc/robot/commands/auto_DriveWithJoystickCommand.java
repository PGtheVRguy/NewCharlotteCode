// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class auto_DriveWithJoystickCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrainSubsystem driveTrainSubsystem;
  private double amount;
  private double time;
  private double timer;
  private double setRot;
  //private double timeWhilePressed = 0.00;
  /**
   * Creates a new ExampleCommand.
   *
   *. @param subsystem The subsystem used by this command.
   */
  public auto_DriveWithJoystickCommand(DriveTrainSubsystem driveTrainSubsystem, double amount, double time, double setRot) {
    this.driveTrainSubsystem = driveTrainSubsystem;
    this.amount = amount;
    this.time = time;
    this.setRot = setRot;
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
    driveTrainSubsystem.arcadeDrive(-amount, setRot);
    if((time/0.2) > timer)
    {
      SmartDashboard.putNumber("autonDriverTimer", timer);
      System.out.println(timer);
      timer += 0.2;
      driveTrainSubsystem.arcadeDrive(-amount, 0.01);
  
    } 
    else{
      driveTrainSubsystem.arcadeDrive(0, 0.01);
    }
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
