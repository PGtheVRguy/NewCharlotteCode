package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.RobotContainer;
import frc.robot.subsystems.MoveArmSubsystem;


public class auto_MoveArmCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MoveArmSubsystem moveArmSubsystem;
  private int position;
  public auto_MoveArmCommand(MoveArmSubsystem moveArmSubsystem, int position) {
    this.position = position;
    this.moveArmSubsystem = moveArmSubsystem;
    addRequirements(moveArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("autonArmEncoder", moveArmSubsystem.armEncoder.getPosition());
//======THIS THING======//

    if(moveArmSubsystem.armEncoder.getPosition() > 269);
    {
      moveArmSubsystem.armSetPID(269,0.5);
      //moveArmSubsystem.armLow();
    }
    if(moveArmSubsystem.armEncoder.getPosition() < 269);
    {
      isFinished();
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
