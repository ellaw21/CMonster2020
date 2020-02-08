/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(2);
  public static WPI_VictorSPX leftBackVictor = new WPI_VictorSPX(3);
  public static WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(4); 
  public static WPI_VictorSPX rightBackVictor = new WPI_VictorSPX(5);

  public static WPI_TalonSRX controlWheel = new WPI_TalonSRX (6);
  public static WPI_TalonSRX tiltShooter = new WPI_TalonSRX (7);

  public static Spark intakeSpark = new Spark (1); 
  public static Spark shooterLeftSpark = new Spark(2);
  public static Spark shooterRightSpark = new Spark(3);
  public static Spark conveyorSpark = new Spark (4);
  public static Spark elevatorSpark = new Spark (5);
  public static Spark hookSpark = new Spark(6);

  public static DoubleSolenoid intakeSolenoid = new DoubleSolenoid (0,1);
  public static DoubleSolenoid controlWheelSolenoid = new DoubleSolenoid (2,3);

  public static Compressor robotCompressor = new Compressor(0);

  // The robot's subsystems and commands are defined here...

public static AutoCommand m_autoCommand = new AutoCommand(); 

public static DriveBase driveBase; 
public static DriveWithJoystick driveWithJoystick;
public static ControlWheelBase controlWheelBase; 
public static IntakeBase intakeBase; 
public static ShooterBase shooterBase; 
public static ClimberBase climberBase;

public static Joystick leftJoystick;
public static Joystick rightJoystick;
public static Joystick logitech; 
public static Joystick buttonBoard; 

  public static JoystickButton wheelControl;
  public static JoystickButton intakeButton;
  public static JoystickButton intakeUp;
  public static JoystickButton intakeDown; 
  public static JoystickButton upTilt;
  public static JoystickButton downTilt;
  public static JoystickButton controlWheelUp; 
  public static JoystickButton controlWheelDown;
  public static JoystickButton shootBall;
  public static JoystickButton conveyorBelt;
  public static JoystickButton elevatorUp;
  public static JoystickButton elevatorDown;
  public static JoystickButton hookUp;
  public static JoystickButton hookDown;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    leftJoystick = new Joystick (0);
    rightJoystick = new Joystick (1);
    logitech = new Joystick (2);
    buttonBoard = new Joystick (3);

    wheelControl = new JoystickButton (logitech, 1); 
    intakeButton = new JoystickButton(rightJoystick, 1);
    controlWheelUp = new JoystickButton(logitech, 2);
    controlWheelDown = new JoystickButton(logitech, 3);
    intakeUp = new JoystickButton(rightJoystick, 2);
    intakeDown = new JoystickButton(rightJoystick, 3);
    upTilt = new JoystickButton(logitech, 6);
    downTilt = new JoystickButton(logitech, 7);
    shootBall = new JoystickButton(logitech, 8);
    conveyorBelt = new JoystickButton(logitech, 9);
    elevatorUp = new JoystickButton(buttonBoard, 1);
    elevatorDown = new JoystickButton(buttonBoard, 2);
    hookUp = new JoystickButton(buttonBoard, 3);
    hookDown = new JoystickButton(buttonBoard, 4);

    driveBase = new DriveBase();
    driveWithJoystick = new DriveWithJoystick();
    CommandScheduler.getInstance().setDefaultCommand(driveBase, driveWithJoystick);


    wheelControl.whileHeld(new WheelStart());
    wheelControl.whenReleased(new WheelStop());
    
    intakeButton.whileHeld(new IntakeStart());
    intakeButton.whenReleased(new IntakeStop());

    intakeUp.whenPressed(new IntakeSolenoidUp()); 
    intakeDown.whenPressed(new IntakeSolenoidDown()); 

    upTilt.whileHeld(new TiltUp());
    upTilt.whenReleased(new TiltStop());
    downTilt.whileHeld(new TiltDown());
    downTilt.whenReleased(new TiltStop());

    controlWheelUp.whenPressed(new ControlWheelUp());
    controlWheelDown.whenPressed(new ControlWheelDown());
    
    shootBall.whileHeld(new ShootBall());
    shootBall.whenReleased(new StopBall());

    conveyorBelt.whileHeld(new ConveyorStart());
    conveyorBelt.whenReleased(new ConveyorStop());

    elevatorUp.whileHeld(new ElevatorUp());
    elevatorUp.whenReleased(new ElevatorStop());
    elevatorDown.whileHeld(new ElevatorDown());
    elevatorDown.whenReleased(new ElevatorStop());
    hookUp.whileHeld(new HookUp());
    hookUp.whenReleased(new HookStop());
    hookDown.whileHeld(new HookDown());
    hookDown.whenReleased(new HookStop());



    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public static Joystick getRightJoystick(){
    return rightJoystick;
  }

  public static Joystick getLeftJoystick(){
    return leftJoystick;
  }

  public static Joystick getLogitech(){
    return logitech;
  }

}
