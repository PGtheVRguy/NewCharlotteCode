// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.commands.MoveArmCommand;
import frc.robot.commands.auto_DriveWithJoystickCommand;
import frc.robot.commands.auto_IntakeCommand;
import frc.robot.commands.auto_MoveArmCommand;
import frc.robot.commands.IntakeCommand;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.MoveArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
//import frc.robot.subsystems.IntakeSubsystem;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  //SendableChooser<Command> autonChooser = new SendableChooser<>();
  // The robot's subsystems and commands are defined here...


  public static CommandXboxController m_driverController = new CommandXboxController(0);
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final MoveArmSubsystem moveArmSubsystem = new MoveArmSubsystem();
  public final static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final DriveWithJoystickCommand DriveWithJoystickCommand = new DriveWithJoystickCommand(driveTrainSubsystem);
  private final MoveArmCommand MoveArmCommand = new MoveArmCommand(moveArmSubsystem, m_driverController);
  private final IntakeCommand intakeCommand = new IntakeCommand(intakeSubsystem, m_driverController);
  public double autonTime = 0;
    /*private static final SendableChooser<CommandBase> autoChooser = new SendableChooser<>();

    public static void init() {
      autoChooser.setDefaultOption("Example Auto", Autos.exampleAuto());
      autoChooser.addOption("None", Autos.none());
    }*/


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    driveTrainSubsystem.setDefaultCommand(DriveWithJoystickCommand);
    moveArmSubsystem.setDefaultCommand(MoveArmCommand);


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
    new Trigger(m_driverController.button(1))
    .onTrue(MoveArmCommand); //Low
    new Trigger(m_driverController.button(2))
    .onTrue(MoveArmCommand); //Force stop?
    new Trigger(m_driverController.button(4))
    .onTrue(MoveArmCommand); //High
    new Trigger(m_driverController.button(7))
    .onTrue(MoveArmCommand); //Zero
    

    new Trigger(m_driverController.povDown()).onTrue(MoveArmCommand);
    new Trigger(m_driverController.povUp()).onTrue(MoveArmCommand);

    new Trigger(m_driverController.rightTrigger(0.1))
      .onTrue(intakeCommand);
      new Trigger(m_driverController.leftTrigger(0.1))
      .onTrue(intakeCommand);
      new Trigger(m_driverController.button(5)).toggleOnTrue(intakeCommand); //Intake MAX
      //new Trigger(m_driverController.pov(180)).onTrue(intakeCommand);
  }

    

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

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

  public Command getAutonomousCommand() {
    //return DriveWithJoystickCommand;
    System.out.println("Starting auto stuff");
    
    autonTime += 0.1;
    return new SequentialCommandGroup(
      new auto_IntakeCommand(intakeSubsystem, -1),
      new WaitCommand(1),
      new auto_IntakeCommand(intakeSubsystem, 0),
      new auto_DriveWithJoystickCommand(driveTrainSubsystem,0.5,7.5, 0),
      //new WaitCommand(6.685), //FYI to future me, if you are doing auton, and your thingy does weird stops and stutters, round your seconds to tenths. It works :)
      new auto_MoveArmCommand(moveArmSubsystem, 0),
      new WaitCommand(3)
      //new auto_DriveWithJoystickCommand(driveTrainSubsystem,0.25,3,1),
      //new WaitCommand(3),
      //new auto_DriveWithJoystickCommand(driveTrainSubsystem,0.001,0.001,1)
      //new SequentialCommandGroup(
      
      //)
      
    //holy crap, auton :)
    );

  }
}


