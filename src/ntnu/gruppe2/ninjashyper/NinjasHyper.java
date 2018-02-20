package ntnu.gruppe2.ninjashyper;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

public class NinjasHyper extends Robot {

    double width, height;

    boolean clockwise = true;

    // Useful info
        // Rotation
            // Gun:   max 20 deg/turn
            // Radar: max 45 deg/turn



    @Override
    public void run() {
        super.run();
        setColors(Color.BLUE, Color.YELLOW, Color.BLACK, Color.RED, Color.BLUE);

        //Setting Variables
        width = getBattleFieldWidth();
        height = getBattleFieldHeight();

        turnGunRight(90);

        //Game Loop
        while (true) {

            //Getting Direction
            double rotation = getHeading();

            // Set gun correct
            turnGunRight(Utils.normalNearAbsoluteAngleDegrees(getHeading() - getGunHeading() + 90));

            // Robot is top half
            if (getY() > height / 2) {
                // Robot is top left
                if (getX() < width / 2) {
                    turnRight(90 - rotation);
                    ahead(width);
                }
                // Robot is top right
                else {
                    turnRight(180 - rotation);
                    ahead(height);
                }
            }
            // Robot is bottom half
            else {
                // Robot is bottom left
                if (getX() < width / 2) {
                    turnRight(Utils.normalAbsoluteAngleDegrees(-rotation));
                    ahead(height);
                }
                // Robot is bottom right
                else {
                    turnRight(270 - rotation);
                    ahead(width);
                }
            }

//            if (clockwise) {
//                turnGunRight(getHeading() + 180);
//                clockwise = false;
//            }
//            else {
//                turnGunLeft(getHeading() + 180);
//                clockwise = true;
//            }

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        // Distance to enemy
        final double distance = e.getDistance();

        // Calculating power use
        double power = 1;
        if (distance < 600) {
            power = 2;
        }
        else if (distance < 200) {
            power = 3;
        }

        fire(power);

//        turnGunRight(Utils.normalRelativeAngleDegrees(gunFrontDiff + e.getBearing()));
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
//        stop();
//        super.onHitByBullet(event);
//        double gunFrontDiff = Utils.normalAbsoluteAngleDegrees(getHeading()) - Utils.normalAbsoluteAngleDegrees(getGunHeading());
        double gunFrontDiff = getHeading()  - getGunHeading();
        turnGunRight(Utils.normalRelativeAngleDegrees(gunFrontDiff + event.getBearing()));
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
        double gunFrontDiff = getHeading()  - getGunHeading();
        turnGunRight(Utils.normalRelativeAngleDegrees(gunFrontDiff + event.getBearing()));
    }
}
