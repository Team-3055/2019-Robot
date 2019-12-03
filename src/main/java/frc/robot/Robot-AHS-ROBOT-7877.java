/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.cscore.VideoSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import sun.font.TrueTypeFont;
//import edu.wpi.first.wpilibj.RobotDrive;
//import junit.framework.Test;
//import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
//import com.ctre.phoenix.motorcontrol.can.*;
//import edu.wpi.first.wpilibj.drive.*;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import edu.wpi.first.wpilibj.CameraServer;

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
  private XboxController xbox;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
 // private String m_autoSelected;
  //private static final String Vis1 = "vision";
  //static String Cam1 = "10.30.55.11";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //private DifferentialDrive T_robotDrive;
  Compressor c = new Compressor(0);
  DoubleSolenoid testDouble = new DoubleSolenoid(0, 1);
  Solenoid test = new Solenoid(4);
  Solenoid climbFeet = new Solenoid(5);
  Solenoid climbFeet2 = new Solenoid(6);
  WPI_TalonSRX frontLeftMotor = (new WPI_TalonSRX(2)); //FL 4, FR 3 RL 5 RR 2 
  WPI_TalonSRX frontRightMotor = (new WPI_TalonSRX(5));
  WPI_TalonSRX rearLeftMotor = (new WPI_TalonSRX(3));
  WPI_TalonSRX rearRightMotor = (new WPI_TalonSRX(4));
  WPI_VictorSPX winchLeft = (new WPI_VictorSPX(1));
  WPI_VictorSPX winchRight = (new WPI_VictorSPX(0));
  Spark intake = (new Spark(0));
  Talon climb1 = (new Talon(1));
  Spark climb2 = (new Spark(4));
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
    xbox = new XboxController(2);
    //CameraServer.getInstance().addAxisCamera("10.30.55.11");
   // CameraServer.getInstance().addAxisCamera("frcvision.local");
    CameraServer.getInstance().startAutomaticCapture();
    
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
    this.teleopInit();
  //   m_autoSelected = m_chooser.getSelected();
  //   // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
  //   System.out.println("Auto selected: " + m_autoSelected);
  //  /* m_timer.reset();
		
	// m_timer.start();
	
  // time0=0;
  // */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    this.teleopPeriodic();
  //   //robotDrive.driveCartesian(0, .5, 0);
  //   switch (m_autoSelected) {
  //     case kCustomAuto:
  //       // Put custom auto code here
  //   Double yspeed = 0.0;
  //   Double xspeed = 0.0;
  //   Double zspeed = 0.0;
  //   Double DZ = 0.1;

  //   Double winchValue = xbox.getRawAxis(1);
  //   Double intakeValue = xbox.getRawAxis(5) * .75;

  //   if(xbox.getRawButton(3))
  //     intakeValue = 0.3;

    
  //   //making a controller deadzone
  //   if(JoystickL.getRawAxis(1) > DZ || JoystickL.getRawAxis(1) < -DZ){
  //     yspeed = JoystickL.getRawAxis(1);
  //   }
  //   if(JoystickL.getRawAxis(0) > DZ || JoystickL.getRawAxis(0) < -DZ){
  //     zspeed = JoystickL.getRawAxis(0);
  //   }
  //   if(JoystickR.getRawAxis(0) > DZ || JoystickR.getRawAxis(0) < -DZ){
  //     xspeed = -JoystickR.getRawAxis(0);
  //   }

  // if(xbox.getRawButton(1)){
  //   intakeValue = 0.0;
  // }

  // robotDrive.driveCartesian(yspeed, xspeed, -zspeed); //sends x, y, and zspeed to motors
  // winchLeft.set(winchValue); 
  // winchRight.set(winchValue);
  // intake.set(intakeValue);
    
  

  //     //single solanoid
  //   if(xbox.getRawButton(6)){
  //   test.set(true);
  //   }
  //   else{
  //   test.set(false);
  //   }
 

  //   //potentiometer
  //  // Potentiometer pot = (new AnalogPotentiometer(0));
  //   /*pot = new AnalogPotentiometer(0,360,30); //(channel, fullrange, offset)
  //   AnalogInput ai = new AnalogInput(1);
  //   pot = new AnalogPotentiometer(ai, 360, 30);
  //   */
  //   //taking the value of the pot and making it an angle (easier to work with)
  //  /* Double angle = 0.0;
  //   Double degrees = pot.get() * 2.84;
  //   angle = degrees * 1 * 1023 / 360;

  //   //PID (this is the FUN part)
  //   double targetAngle = 90;
  //   Double e = targetAngle - angle;
  //   Double kP = 1.0; //Proportional constant
  //   Double kD = 1.0; //Derirative constant
  //   Double errorOld = 1.0;
  //   Double Time = 20.0; //this is how quickly the loop runs. timedRobot runs at 20ms
  //  // double integral = 1.0;

  //   evenSmallerArm.set((e*kP) + /*(integral * ke) +*/ //((e-errorOld)/(Time*kD)));
  //   //integral = e*time;
  //   //errorOld = e;

    
 // }
        
        
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

    //Double winchValue = 0.0;
    //Double intakeValue = 0.0;
    Double winchValue = xbox.getRawAxis(1);
    Double intakeValue = xbox.getRawAxis(5) * .65;
    /*if(JoystickL.getRawButton(5)){
      winchValue = -0.7;
    }
    else if(JoystickL.getRawButton(3)){
      winchValue = 0.7;
    }
    else{
      winchValue = 0.0;
    }

    if(JoystickR.getRawButton(5)){
      intakeValue = -0.6;
    }
    else if(JoystickR.getRawButton(3)){
      intakeValue = 0.6;
    }
    else{
      intakeValue = 0.0;
    }
    */
    //making a controller deadzone
    if(JoystickL.getRawAxis(1) > DZ || JoystickL.getRawAxis(1) < -DZ){
      yspeed = JoystickL.getRawAxis(1);
    }
    if(JoystickL.getRawAxis(0) > DZ || JoystickL.getRawAxis(0) < -DZ){
      zspeed = JoystickL.getRawAxis(0);
    }
    if(JoystickR.getRawAxis(0) > DZ || JoystickR.getRawAxis(0) < -DZ){
      xspeed = -JoystickR.getRawAxis(0);
    }

  if(xbox.getRawButton(1)){
    intakeValue = 0.0;
  }

  if(JoystickR.getRawButton(10)){
    climb1.set(-1);
    climb2.set(-1);
    }
  else if(JoystickR.getRawButton(12)){
    climb1.set(1);
    climb2.set(1);
  }
  else{
    climb1.set(0);
    climb2.set(0);
  }
  
  /*if(JoystickL.getRawButton(1)){
    climbFeet.set(true);
    }
    else{
    climbFeet.set(false);
    }
  
  if(JoystickR.getRawButton(1)){
    climbFeet2.set(true);
  }
  else{
    climbFeet2.set(false);
  }
   */ 
  
  robotDrive.driveCartesian(yspeed, xspeed, -zspeed); //sends x, y, and zspeed to motors
  winchLeft.set(winchValue); 
  winchRight.set(winchValue);
  intake.set(intakeValue);
    
  

      //single solanoid
    //if(JoystickR.getRawButton(1)){
    if(xbox.getRawButton(6)){
    test.set(true);
    }
    if(xbox.getRawButton(5)){
    test.set(false);
    }
 

    //potentiometer
   // Potentiometer pot = (new AnalogPotentiometer(0));
    /*pot = new AnalogPotentiometer(0,360,30); //(channel, fullrange, offset)
    AnalogInput ai = new AnalogInput(1);
    pot = new AnalogPotentiometer(ai, 360, 30);
    */
    //taking the value of the pot and making it an angle (easier to work with)
   /* Double angle = 0.0;
    Double degrees = pot.get() * 2.84;
    angle = degrees * 1 * 1023 / 360;

    //PID (this is the FUN part)
    double targetAngle = 90;
    Double e = targetAngle - angle;
    Double kP = 1.0; //Proportional constant
    Double kD = 1.0; //Derirative constant
    Double errorOld = 1.0;
    Double Time = 20.0; //this is how quickly the loop runs. timedRobot runs at 20ms
   // double integral = 1.0;

    evenSmallerArm.set((e*kP) + /*(integral * ke) +*/ //((e-errorOld)/(Time*kD)));
    //integral = e*time;
    //errorOld = e;

    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
