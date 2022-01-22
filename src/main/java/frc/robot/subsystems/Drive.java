// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private CANSparkMax driveSlaveL = new CANSparkMax(Constants.slaveLeftMotor, MotorType.kBrushless);
  private CANSparkMax driveMasterR = new CANSparkMax(Constants.masterRightMotor, MotorType.kBrushless);
  private CANSparkMax driveSlaveR = new CANSparkMax(Constants.slaveRightMotor, MotorType.kBrushless);
  private CANSparkMax driveMasterL = new CANSparkMax(Constants.masterLeftMotor, MotorType.kBrushless);

  private void setIdleModes(IdleMode mode) {
    driveMasterL.setIdleMode(mode);
    driveMasterR.setIdleMode(mode);
    driveSlaveL.setIdleMode(mode);
    driveSlaveR.setIdleMode(mode);
  }

  public Drive() {
    driveSlaveL.follow(driveMasterL);
    driveSlaveR.follow(driveMasterR);
    setIdleModes(IdleMode.kCoast);
  }

  public void rawMotorPower(double leftPower, double rightPower) {
    driveMasterL.set(leftPower);
    driveMasterR.set(rightPower);
  }

  public void setPower(double leftPower, double rightPower){
    rawMotorPower(-Math.pow(leftPower, 3), Math.pow(rightPower, 3));
  }
}
