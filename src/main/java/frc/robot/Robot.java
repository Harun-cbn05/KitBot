// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private final CANSparkMax rightFrontMotorController = new CANSparkMax(6, MotorType.kBrushed);
  private final CANSparkMax rightBackMotorController = new CANSparkMax(7, MotorType.kBrushed);
  private final CANSparkMax leftFrontMotorController = new CANSparkMax(8, MotorType.kBrushed);
  private final CANSparkMax leftBackMotorController = new CANSparkMax(9, MotorType.kBrushed);
  private final CANSparkMax topFrameMotorController = new CANSparkMax(10, MotorType.kBrushed);

  private final DifferentialDrive roboDrive = new DifferentialDrive(leftFrontMotorController, rightFrontMotorController);
  
  private final XboxController firstDriverController = new XboxController(0);
  private final Timer timer = new Timer();

  @Override
  public void robotInit() {
    leftBackMotorController.follow(leftFrontMotorController);
    rightBackMotorController.follow(rightFrontMotorController);

    rightFrontMotorController.setInverted(true);
    rightBackMotorController.setInverted(true);
  }
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 15.0) {
      roboDrive.arcadeDrive(0.5, 0.0, false);
    } else {
      roboDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    roboDrive.arcadeDrive(firstDriverController.getRawAxis(1), -firstDriverController.getRawAxis(0));
    if (firstDriverController.getRawButton(1)) {
      topFrameMotorController.set(0.5);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
