package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.IntakeCommand;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.Constants.*;

public class auto_IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private IntakeSubsystem m_intakeSubsystem;
  //private CommandXboxController m_controller;
  private int amount;
  public auto_IntakeCommand(IntakeSubsystem intakeSubsystem, int amount) {
    this.m_intakeSubsystem = intakeSubsystem;
    this.amount = amount;
    addRequirements();
  } 

  //public IntakeCommand(IntakeSubsystem intakeSubsystem, CommandXboxController m_driverController) {
  //}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(amount == -1)
    {
      IntakeCommand.shootPower = 1.2;
    //  m_intakeSubsystem.runGripOut();
    }
    if(amount == 0)
    {
      IntakeCommand.shootPower = 1;
     // m_intakeSubsystem.stopGrip();
    }
    if(amount == 1)
    {
    //  m_intakeSubsystem.runGripIn();
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
