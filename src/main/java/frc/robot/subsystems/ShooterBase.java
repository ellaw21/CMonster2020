/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotContainer;

public class ShooterBase extends SubsystemBase {

  public static WPI_TalonSRX TiltShooter = RobotContainer.tiltShooter;
  /**
   * Creates a new ShooterBase.
   */
  public ShooterBase() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tiltUp(){
    TiltShooter.set(0.75);
  }

  public void tiltStop(){
    TiltShooter.set(0);
  }
  public void tiltDown(){
    TiltShooter.set(-0.75);
  }
}
