// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc253.Code2017;

import org.usfirst.frc253.Code2017.subsystems.*;
import org.usfirst.frc253.Code2017.commands.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {
	
	Command autonomousCommand;
    SendableChooser autoChooser;
    
    //Declaring subsystems
    public static OI oi;
    public static Drivetrain drivetraintank;
    public static GearFlip gearFlip;
    public static Servo servo;
    public static Climber climber;
    public static SensorData sensorData;
    
    CameraServer camera;
    //These are referenced in robotInit()
    
    public void robotInit() {
    	LiveWindow.run();
    	RobotMap.init();
    	
    	//enables camera
    	camera.getInstance().startAutomaticCapture();
    	
    	//Subsystems
    	drivetraintank = new Drivetrain();
    	gearFlip = new GearFlip();
        climber = new Climber();
        servo = new Servo();
        sensorData = new SensorData();
        
        //multiple autonomous chooser
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Center Position", new AutoSmoothCenter()); // add command as default
        autoChooser.addObject("Right Position", new AutoSmoothRight());	// add another command
        autoChooser.addObject("Left Position", new AutoSmoothLeft());
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);

        oi = new OI();
     }
    public void disabledInit(){
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	autonomousCommand = (Command) autoChooser.getSelected();
    	if(autonomousCommand != null){
    		System.out.println(autonomousCommand.toString());
    		autonomousCommand.start();
    	}
    }

    public void autonomousPeriodic() {
    	 Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	LiveWindow.run();
//        autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}