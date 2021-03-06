// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */

  private NetworkTable limelight;
  private double tx, ty;
  private boolean tv;

  public Limelight() {
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public boolean getV(){
    return tv;
  }

  public double getX(){
    return tx;
  }

  public double getY(){
    return ty;
  }

  @Override
  public void periodic() {
    tx = limelight.getEntry("tx").getDouble(-1.0);
    ty = limelight.getEntry("ty").getDouble(-1.0);
    tv = (limelight.getEntry("tv").getDouble(0.0) != 0);
    System.out.println("tx: " + tx + ", ty: " + ty + ", tv: " + tv);
  }
}
