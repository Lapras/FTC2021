package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor flywheels;

    public Intake(HardwareMap hardwareMap) {
        flywheels = hardwareMap.get(DcMotor.class, "Flywheels");
        flywheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void adjustPower(int power) {
        flywheels.setPower(power);
    }
}
