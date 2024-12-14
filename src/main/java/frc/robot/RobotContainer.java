// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.subsystems.FanSubsystem;
import frc.robot.subsystems.RollerSubsystem;
import frc.robot.subsystems.RotatorSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.TurretSubystem;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;

import java.io.File;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  SwerveSubsystem swerver = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  TurretSubystem turret = new TurretSubystem();
  RotatorSubsystem rotator = new RotatorSubsystem();
  RollerSubsystem roller = new RollerSubsystem();
  FanSubsystem fan = new FanSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private CommandXboxController m_operatorController = new CommandXboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`


    // command to drive swerve robot
    swerver.setDefaultCommand(Commands.run(() -> swerver.drive(
      () -> -MathUtil.applyDeadband(m_driverController.getLeftX(), .1),
      () -> MathUtil.applyDeadband(m_driverController.getLeftY(), .1),
      () -> .5 * -MathUtil.applyDeadband(m_driverController.getRightX(), .1)),
      swerver));

    // Make sure motors stop when buttons are released
    roller.setDefaultCommand(Commands.run(() -> roller.setRollerVoltage(0), roller));
    rotator.setDefaultCommand(Commands.run(() -> rotator.setRotatorVoltage(0), rotator));
    fan.setDefaultCommand(Commands.run(() -> fan.setFanSpeed(0), fan));
    turret.setDefaultCommand(Commands.run(() -> turret.setTurretVoltage(0), turret));

    // driver can hold x to lock robot in place
    m_driverController.x().whileTrue(Commands.run(() -> swerver.lock(), swerver));

    m_operatorController.y().whileTrue(Commands.run(() -> rotator.setRotatorVoltage(1), rotator));
    m_operatorController.x().whileTrue(Commands.run(() -> rotator.setRotatorVoltage(-1), rotator));
    m_operatorController.rightBumper().whileTrue(Commands.run(() -> roller.setRollerVoltage(1), roller));
    m_operatorController.leftBumper().whileTrue(Commands.run(() -> roller.setRollerVoltage(-1), roller));
    m_operatorController.povDown().whileTrue(Commands.run(() -> turret.setTurretVoltage(1), turret));
    m_operatorController.povUp().whileTrue(Commands.run(() -> turret.setTurretVoltage(-1), turret));
    m_operatorController.rightTrigger().whileTrue(Commands.run(() -> fan.setFanVoltage(11), fan));
    m_operatorController.leftTrigger().whileTrue(Commands.run(() -> fan.setFanVoltage(-11), fan));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new WaitCommand(15);
  }
}
