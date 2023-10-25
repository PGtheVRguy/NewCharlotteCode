package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.RobotContainer;
import frc.robot.subsystems.MoveArmSubsystem;


public class MoveArmPIDCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MoveArmSubsystem moveArmSubsystem;

  private final CommandXboxController m_controller;

  private final int pos;
  private final double power;
  public MoveArmPIDCommand(MoveArmSubsystem moveArmSubsystem, CommandXboxController controller, int Position, double Power) {
    this.m_controller = controller;
    this.moveArmSubsystem = moveArmSubsystem;
    this.pos = Position;
    this.power = Power;
    addRequirements(moveArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    moveArmSubsystem.armSetPID(pos, power);
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
