package frc.robot.commands;

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
/* 
    if(m_controller.povDown().getAsBoolean())
    {
      moveArmSubsystem.armDown();
    }
      else if(m_controller.povUp().getAsBoolean())
      {
        moveArmSubsystem.armUp();
      }
    else{
      moveArmSubsystem.forceStopArm();
    }
*/

//======THIS THING======//
    if(position == 2) 
    {
      moveArmSubsystem.armHigh();
    }
    if(position == 1)
    {
      moveArmSubsystem.armMid();
    }
    if(position == 0)
    {
      moveArmSubsystem.armLow();
    }

    if(position == 3)
    {
      moveArmSubsystem.forceStopArm();
    }
    if(position == 4)
    {
      moveArmSubsystem.zeroArm();
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
