// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.commands.MoveArmCommand;
import frc.robot.commands.MoveArmManualCommand;
import frc.robot.commands.IntakeCommand;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.MoveArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
//import frc.robot.subsystems.IntakeSubsystem;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  public static CommandXboxController m_driverController = new CommandXboxController(0);
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final MoveArmSubsystem moveArmSubsystem = new MoveArmSubsystem();
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final DriveWithJoystickCommand DriveWithJoystickCommand = new DriveWithJoystickCommand(driveTrainSubsystem);
  //private final MoveArmCommand MoveArmCommand = new MoveArmCommand(moveArmSubsystem, m_driverController);
  private final IntakeCommand intakeCommand = new IntakeCommand(intakeSubsystem, m_driverController);

 // public static Joystick joystick  = new Joystick(0);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    driveTrainSubsystem.setDefaultCommand(DriveWithJoystickCommand);
    //moveArmSubsystem.setDefaultCommand(MoveArmCommand);
  }

  
  private void configureBindings() {

    new Trigger(m_driverController.button(1)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.highArmPos, Constants.MoveArmConstants.highArmPow));
    new Trigger(m_driverController.button(3)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.midArmPos, Constants.MoveArmConstants.midArmPow));
    new Trigger(m_driverController.button(4)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.lowArmPos, Constants.MoveArmConstants.lowArmPow));

    new Trigger(m_driverController.button(2)).whileTrue(new MoveArmManualCommand(moveArmSubsystem, 0));
    new Trigger(m_driverController.povDown()).whileTrue(new MoveArmManualCommand(moveArmSubsystem, Constants.MoveArmConstants.manualSpeed));
    new Trigger(m_driverController.povUp()).whileTrue(new MoveArmManualCommand(moveArmSubsystem, -Constants.MoveArmConstants.manualSpeed));

    new Trigger(m_driverController.rightTrigger(0.1))
      .onTrue(intakeCommand);
      new Trigger(m_driverController.leftTrigger(0.1))
      .onTrue(intakeCommand);
      //new Trigger(m_driverController.pov(180)).onTrue(intakeCommand);
  }
  
  public static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  public static boolean between(double valueLeast, double valueMost, double value) {
    if ((value > valueLeast) && (value < valueMost))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return DriveWithJoystickCommand;
  }
  
}
