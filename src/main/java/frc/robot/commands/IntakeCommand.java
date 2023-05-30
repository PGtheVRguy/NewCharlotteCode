package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.Constants.*;

public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private IntakeSubsystem m_intakeSubsystem;
  private CommandXboxController m_controller;
  public IntakeCommand(IntakeSubsystem intakeSubsystem, CommandXboxController controller) {
    this.m_controller = controller;
    this.m_intakeSubsystem = intakeSubsystem;
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
    if(m_controller.getRightTriggerAxis() > 0.05){
      m_intakeSubsystem.runGripIn(m_controller.getRightTriggerAxis());
      
    } else if(m_controller.getLeftTriggerAxis() > 0.05){
      m_intakeSubsystem.runGripOut(m_controller.getLeftTriggerAxis());
    }
    else{
      //m_gripSubsystem.setGripOut();
      m_intakeSubsystem.stopGrip();
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
