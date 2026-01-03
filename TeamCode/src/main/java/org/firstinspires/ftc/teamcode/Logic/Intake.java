package org.firstinspires.ftc.teamcode.Logic;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor intake;
    DcMotor roller;

    public Intake(HardwareMap hardwareMap){
        intake = hardwareMap.get(DcMotor.class, "rightIntake");
        roller = hardwareMap.get(DcMotor.class, "roller");
    }

    public void intakeRun(){
        intake.setPower(0.7);
        roller.setPower(-1);
    }

    public void intake_reverse(){
        intake.setPower(-1);
        roller.setPower(-1);
    }

    public void toShooter(){
        intake.setPower(1);
        roller.setPower(1);
    }
}
