package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDContstants;

public class FanSubsystem extends SubsystemBase {
    CANSparkMax fan;

    public FanSubsystem() {
        fan = new CANSparkMax(MotorIDContstants.kFanID, CANSparkLowLevel.MotorType.kBrushless);
    }

    public void setFanSpeed(double speed) {
        fan.set(speed);
    }

    public void setFanVoltage(double volts) {
        fan.setVoltage(volts);
    }
}
