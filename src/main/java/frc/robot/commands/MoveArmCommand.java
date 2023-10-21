package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.RobotContainer;
import frc.robot.subsystems.MoveArmSubsystem;


public class MoveArmCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MoveArmSubsystem moveArmSubsystem;

  private final CommandXboxController m_controller;
  public MoveArmCommand(MoveArmSubsystem moveArmSubsystem, CommandXboxController controller) {
    this.m_controller = controller;
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
    //Manual mode!
    if(m_controller.povDown().getAsBoolean())
    {
      moveArmSubsystem.moveArm(0.5);
    }
      else if(m_controller.povUp().getAsBoolean())
      {
        moveArmSubsystem.moveArm(-0.5);
      }
    else{
      moveArmSubsystem.forceStopArm();
    }


    //auto mode
    if(m_controller.button(4).getAsBoolean()) //======THIS THING======//
    {
      moveArmSubsystem.armSetPID(0,1.2);
      //moveArmSubsystem.armHigh();
    }
    if(m_controller.button(3).getAsBoolean())
    {
      moveArmSubsystem.armSetPID(91,0.6);
      //moveArmSubsystem.armMid();
    }
    if(m_controller.button(1).getAsBoolean())
    {
      moveArmSubsystem.armSetPID(269,0.5);
      //moveArmSubsystem.armLow();
    }

    if(m_controller.button(2).getAsBoolean())
    {
      moveArmSubsystem.forceStopArm();
    }
    if(m_controller.button(7).getAsBoolean())
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
    return false;
  }
}
