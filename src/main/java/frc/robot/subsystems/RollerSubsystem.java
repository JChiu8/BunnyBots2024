package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDContstants;

public class RollerSubsystem extends SubsystemBase {
    CANSparkMax roller;

    public RollerSubsystem() {
        roller = new CANSparkMax(MotorIDContstants.kRollerID, CANSparkLowLevel.MotorType.kBrushless);
    }

    public void setRollerSpeed(double speed) {
        roller.set(speed);
    }

    public void setRollerVoltage(double volts) {
        roller.setVoltage(volts);
    }
}
