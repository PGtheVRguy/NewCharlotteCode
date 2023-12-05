// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.commands.MoveArmCommand;
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

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
  private void configureBindings() {

    new Trigger(m_driverController.button(1)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.highArmPos, Constants.MoveArmConstants.highArmPow));
    new Trigger(m_driverController.button(2)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.midArmPos, Constants.MoveArmConstants.midArmPow));
    new Trigger(m_driverController.button(4)).whileTrue(new MoveArmCommand(moveArmSubsystem, Constants.MoveArmConstants.lowArmPos, Constants.MoveArmConstants.lowArmPow));

    new Trigger(m_driverController.rightTrigger(0.1))
      .onTrue(intakeCommand);
      new Trigger(m_driverController.leftTrigger(0.1))
      .onTrue(intakeCommand);
      //new Trigger(m_driverController.pov(180)).onTrue(intakeCommand);
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
