package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubystem extends SubsystemBase {
    CANSparkMax turretMotor;

    public TurretSubystem() {
        turretMotor = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
    }

    public void setTurretSpeed(double speed) {
        turretMotor.set(speed);
    }

    public void setTurretVoltage(double volts) {
        turretMotor.setVoltage(volts);
    }
}
