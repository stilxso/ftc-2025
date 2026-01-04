package org.firstinspires.ftc.teamcode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Logic.*;


@Autonomous(name = "Drive 24 Inches Time")
public class Drive24InchesTime extends LinearOpMode {

    private DcMotorEx fl, fr, bl, br;
    private ElapsedTime runtime = new ElapsedTime();
    private Turret turret;

    // Tune these values for your robot
    private static final double DRIVE_SPEED = 0.5;     // Motor power (0 to 1.0)
    private static final double DRIVE_TIME = 1;      // Seconds to drive (tune this!)

    @Override
    public void runOpMode() {
        // Initialize motors
        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");

        turret = new Turret()

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        // Set to brake when stopped
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Ready");
        telemetry.addData("Drive Speed", DRIVE_SPEED);
        telemetry.addData("Drive Time", DRIVE_TIME + " seconds");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // Drive forward for specified time
        while (opModeIsActive() && runtime.seconds() < DRIVE_TIME) {
            fl.setPower(DRIVE_SPEED);
            fr.setPower(DRIVE_SPEED);
            bl.setPower(DRIVE_SPEED);
            br.setPower(DRIVE_SPEED);



            telemetry.addData("Status", "Driving...");
            telemetry.addData("Time Elapsed", "%.2f seconds", runtime.seconds());
            telemetry.update();
        }

        // Stop all motors
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        telemetry.addData("Status", "Complete!");
        telemetry.addData("Total Time", "%.2f seconds", runtime.seconds());
        telemetry.update();

        sleep(1000); // Wait 1 second before ending
    }
}