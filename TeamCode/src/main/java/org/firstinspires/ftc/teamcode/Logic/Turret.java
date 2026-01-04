package org.firstinspires.ftc.teamcode.Logic;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Configurable
public class Turret {
    DcMotorEx leftShooter;
    DcMotorEx rightShooter;
    InterpLUT rpmLUT;

    PIDFController shooterPID;

    double kP = 0;
    double kI = 0;
    double kD = 0;
    double kF = 0;


    public Turret(HardwareMap hardwareMap){
        leftShooter = hardwareMap.get(DcMotorEx.class, "leftShooter");
        rightShooter = hardwareMap.get(DcMotorEx.class, "rightShooter");

        shooterPID = new PIDFController(kP, kI, kD, kF);

        leftShooter.setDirection(DcMotorEx.Direction.REVERSE);

        leftShooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightShooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        rpmLUT = new InterpLUT();
        rpmLUT.add(0.5, 1100);
        rpmLUT.add(1.0, 1300);
        rpmLUT.add(1.5, 1400);
        rpmLUT.add(2.0, 1500);
        rpmLUT.add(2.5, 1600);
        rpmLUT.add(3.0, 1700);
        rpmLUT.createLUT();
    }

    public void shoot(double distance){
        double rpm = rpmLUT.get(distance);

        double targetTicksPerSec = rpm * 537.7 / 60.0;

        shooterPID.setTolerance(50);
        shooterPID.setSetPoint(targetTicksPerSec);

        double currentVel = leftShooter.getVelocity();
        double power = shooterPID.calculate(currentVel);

        leftShooter.setPower(power);
        rightShooter.setPower(power);

    }

    public void stopTurret(){
        leftShooter.setPower(0);
        rightShooter.setPower(0);
    }
}
