/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;

public class DriveBase extends SubsystemBase {

 WPI_TalonSRX leftTalon = RobotContainer.leftFrontTalon;
 WPI_TalonSRX rightTalon = RobotContainer.rightFrontTalon;
 WPI_VictorSPX leftVictor = RobotContainer.leftBackVictor;
 WPI_VictorSPX rightVictor = RobotContainer.rightBackVictor;

 double leftMotorSpeed = 0;
 double rightMotorSpeed = 0; 

  /**
   * Creates a new DriveBase.
   */
  public DriveBase() {

  }

  public void JoystickInputs(Joystick rightJoystick, Joystick leftJoystick){
    leftMotorSpeed = leftJoystick.getY()*-1;
    rightMotorSpeed = rightJoystick.getY();

    leftTalon.set(leftMotorSpeed);
    leftVictor.set(leftMotorSpeed);
    rightTalon.set(rightMotorSpeed);
    rightVictor.set(rightMotorSpeed);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
