package org.firstinspires.ftc.teamcode.OpMode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Logic.Intake;
import org.firstinspires.ftc.teamcode.Logic.Turret;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    Follower follower;
    Turret turret;
    Intake intake;

    boolean isSlowMode = false;

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


        if(!isSlowMode){
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
        } else{
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y * 0.6,
                    -gamepad1.left_stick_x * 0.6,
                    -gamepad1.right_stick_x * 0.6
            );
        }


        follower.setPose(new Pose(70, 5));

        if(gamepad1.left_trigger > 0.5){
            intake.intakeRun();

        }else if(gamepad1.right_trigger > 0.5){
            double distance = Math.hypot(140 - x, 140 - y);
            turret.shoot(distance);
        }else if(gamepad1.left_bumper){
            intake.toShooter();
        }else if(gamepad1.right_bumper){
            intake.intake_reverse();
        }else if(gamepad1.a){
            isSlowMode = !isSlowMode;
        }else{
            intake.stop();
            turret.stopTurret();
        }


    }
}
