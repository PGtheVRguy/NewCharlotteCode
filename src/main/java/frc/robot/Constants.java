// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.PIDConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static final class DriveTrainConstants{
    public static final int leftMotor1ID = 11; //IDs for the Left and Right motors
    public static final int leftMotor2ID = 12;
    public static final int leftMotor3ID = 13;
    public static final int rightMotor1ID = 21;
    public static final int rightMotor2ID = 22;
    public static final int rightMotor3ID = 23;
    public static final int maxVoltage = 4; //If issues arise from recent upgrade, blame this.
  }
  public static final class IntakeConstants{
    public static final int intakeMotor1ID = 41;
    public static final int intakeMotor2ID = 42;
    public static final double outakeSpeed = 1.00; //-1=-100%(rev) , 0=0%, 1=100%(max). 
  }
  public static final class MoveArmConstants{
    public static final int ArmMotorID = 51;
    public static final double armkP = 0.03;
    public static final double armkI = 0.00;
    public static final double armkD = 0.00;
    public static final int minArmPos = 0;
    public static final int maxArmPos = 269;
    public static final double maxPIDArmSpeed = 0.2;
  }
  public static final class AutonStuff{
    public static final PIDConstants AUTO_TRANSLATION_CONSTANTS = new PIDConstants(0, 0, 0);
    public static final PIDConstants AUTO_ROTATION_CONSTANTS = new PIDConstants(0, 0, 0);
  }
  public static final double gripTriggerDeadzone = 0.05;
}
