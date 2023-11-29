// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.math.MathUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


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
    if(RobotContainer.m_driverController.rightBumper().getAsBoolean())
    {
      moveSpeed = 0.5;
    }
    else{
      moveSpeed = 1;
    }
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

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
