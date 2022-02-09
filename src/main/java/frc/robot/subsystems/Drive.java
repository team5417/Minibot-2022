// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private TalonSRX driveSlaveL = new TalonSRX(Constants.slaveLeftMotor);
  private TalonSRX driveSlaveR = new TalonSRX(Constants.slaveRightMotor);
  private TalonSRX driveMasterL = new TalonSRX(Constants.masterLeftMotor);
  private TalonSRX driveMasterR = new TalonSRX(Constants.masterRightMotor);

  public Drive() {
    wowow();
  }

  public void wowow(){
    driveSlaveL.setInverted(true);
    driveSlaveL.follow(driveMasterL);
    driveSlaveR.follow(driveMasterR);
  }

  public void rawMotorPower(double leftPower, double rightPower) {
    wowow();
    driveMasterL.set(ControlMode.PercentOutput, leftPower);
    driveMasterR.set(ControlMode.PercentOutput, rightPower);
    //System.out.println("left power" + leftPower);
    //System.out.println("right power" + rightPower);
  }

  public void setPower(double leftPower, double rightPower){
    rawMotorPower(-0.5*Math.pow(leftPower, 3), 0.5*Math.pow(rightPower, 3));
  }

  public void turnLimelight(double tx, boolean tv, boolean pressed){
    if(pressed && tv && Math.abs(tx) > 2){
      double power = tx * Constants.kPturn;
      rawMotorPower(power, 2 * power);
    }else{
      rawMotorPower(0, 0);
    }
  }
}
