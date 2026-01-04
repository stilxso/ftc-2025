package org.firstinspires.ftc.teamcode.OpMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Logic.Intake;
import org.firstinspires.ftc.teamcode.Logic.Turret;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class TeleOp extends OpMode {

    Follower follower;
    Turret turret;
    Intake intake;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(132, 100, 0));
        turret = new Turret(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void start() {
        follower.startTeleOpDrive();
    }

    @Override
    public void loop() {
        follower.update();

        double x = follower.getPose().getX();
        double y = follower.getPose().getY();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_x,
                -gamepad1.left_stick_y,
                -gamepad1.right_stick_x
        );


        if(gamepad1.left_trigger > 0.5){
            double distance = Math.hypot(140 - x, 140 - y);
            turret.shoot(distance);
        }

        if(gamepad1.right_trigger > 0.5){
            intake.toShooter();
        }

        if(gamepad1.left_bumper){
            intake.intakeRun();
        }

        if(gamepad1.right_bumper){
            intake.intake_reverse();
        }


    }
}
