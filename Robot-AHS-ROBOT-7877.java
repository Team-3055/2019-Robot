/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PrintCommand;
//import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import sun.font.TrueTypeFont;

//import junit.framework.Test;
//import edu.wpi.first.wpilibj.PWMSpeedController;
//import edu.wpi.first.wpilibj.Talon;
//import com.ctre.phoenix.motorcontrol.can.*;
//import edu.wpi.first.wpilibj.drive.*;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.cscore.VideoSource;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 // private DifferentialDrive m_myRobot;
  private MecanumDrive robotDrive;
  private Joystick JoystickR;
  private Joystick JoystickL;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  //private static final String Vis1 = "vision";
  //static String Cam1 = "10.30.55.11";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //private DifferentialDrive T_robotDrive;
  Compressor c = new Compressor(0);
  DoubleSolenoid testDouble = new DoubleSolenoid(0, 1);
  Solenoid test = new Solenoid(2);
  WPI_TalonSRX frontLeftMotor = (new WPI_TalonSRX(4));
  WPI_TalonSRX frontRightMotor = (new WPI_TalonSRX(3));
  WPI_TalonSRX rearLeftMotor = (new WPI_TalonSRX(5));
  WPI_TalonSRX rearRightMotor = (new WPI_TalonSRX(2));
  WPI_VictorSPX littleArm = (new WPI_VictorSPX(1));
  WPI_VictorSPX bigArm = (new WPI_VictorSPX(0));
  DigitalInput bigArmSwitch = (new DigitalInput(0));
  //String vision1 = "vision1";
  //CameraServer.addAxisCamera(vision1, Cam1);
  //Joystick JoystickR = new Joystick(0);
  //Joystick JoystickL = new Joystick(1);

  //Timer m_timer = new Timer();
	//double time0;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    c.setClosedLoopControl(true);
    robotDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    JoystickR = new Joystick(0);
    JoystickL = new Joystick(1);
    CameraServer.getInstance().addAxisCamera("10.30.55.11");
    CameraServer.getInstance().addAxisCamera("frcvision.local");
    //CameraServer.getInstance().startAutomaticCapture();
    
    //T_robotDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
   /* m_timer.reset();
		
	m_timer.start();
	
  time0=0;
  */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //robotDrive.driveCartesian(0, .5, 0);
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        robotDrive.driveCartesian(0, .5, 0);
        break;
        
      }
        
      
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Double yspeed = 0.0;
    Double xspeed = 0.0;
    Double zspeed = 0.0;
    Double DZ = 0.1;
    

    if(JoystickL.getRawAxis(1) > DZ || JoystickL.getRawAxis(1) < -DZ){
      yspeed = JoystickL.getRawAxis(1);
    }
    if(JoystickL.getRawAxis(0) > DZ || JoystickL.getRawAxis(0) < -DZ){
      zspeed = JoystickL.getRawAxis(0);
    }
    if(JoystickR.getRawAxis(0) > DZ || JoystickR.getRawAxis(0) < -DZ){
      xspeed = -JoystickR.getRawAxis(0);
    }

  robotDrive.driveCartesian(yspeed, xspeed, -zspeed);
  /* if(JoystickL.getRawButton(5)){
     xspeed = .5;
   }
   else if (JoystickL.getRawButton(6)){
     xspeed = -.5;
   }
   else{
     xspeed = 0.0;
   }*/
   while (bigArmSwitch.get(0)){
     bigArm.set(.5);
   }
   
    if(JoystickL.getRawButton(3)){
      littleArm.set(-1);
    }
    else if (JoystickL.getRawButton(5)){
      littleArm.set(1);
    }
    else{
      littleArm.set(0);
    }

    if(JoystickL.getRawButton(4)){
      bigArm.set(-1);
    }
    else if (JoystickL.getRawButton(6)){
      bigArm.set(1);
    }
    else{
      bigArm.set(0);
    }
    //T_robotDrive.tankDrive(xspeed, yspeed);

    /*if(JoystickR.getRawButton(8)){ 
      robotDrive.driveCartesian(0, 0, .5);
      }
    if(JoystickR.getRawButton(10)){ 
      robotDrive.driveCartesian(0, .5, 0);
      }
    if(JoystickR.getRawButton(12)){ 
       robotDrive.driveCartesian(.5, 0, 0);
      }
    */
    //single solanoid
    /*if(JoystickR.getRawButton(1)){
    test.set(true);
    }
    else{
    test.set(false);
    }

    //double solanoid
    if(JoystickR.getRawButton(2)){ 
    testDouble.set(DoubleSolenoid.Value.kForward);
    }
    if(JoystickR.getRawButton(3)){
    testDouble.set(DoubleSolenoid.Value.kReverse);
    }
    else{
    testDouble.set(DoubleSolenoid.Value.kOff);
    }
    */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
