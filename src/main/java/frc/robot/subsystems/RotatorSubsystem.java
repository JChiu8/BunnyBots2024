package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import frc.robot.Constants.MotorIDContstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RotatorSubsystem extends SubsystemBase {
    CANSparkMax leftArm;
    CANSparkMax rightArm;

    public RotatorSubsystem() {
        leftArm = new CANSparkMax(MotorIDContstants.kLeftArmID, CANSparkLowLevel.MotorType.kBrushless);
        rightArm = new CANSparkMax(MotorIDContstants.kRightArmID, CANSparkLowLevel.MotorType.kBrushless);
        leftArm.setInverted(true);
        rightArm.setInverted(false);
    }

    public void setRotatorSpeed(double speed) {
        leftArm.set(speed);
        rightArm.set(speed);
    }

    public void setRotatorVoltage(double volts) {
        leftArm.setVoltage(volts);
        rightArm.setVoltage(volts);
    }
}