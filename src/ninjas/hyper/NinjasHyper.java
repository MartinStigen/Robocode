package ninjas.hyper;

import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class NinjasHyper extends Robot {

    double width, height;

    boolean left = true, up = true;

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

        //Game Loop
        while (true) {

            //Getting Direction
            double rotation = getHeading();

            // Robot is top half
            if (getY() > height / 2) {
                // Robot is top left
                if (getX() < width / 2) {
                    turnRight(90 - rotation);
                }
                // Robot is top right
                else {
                    turnRight(90 - rotation);

                }
            }
            // Robot is bottom half
            else {
                // Robot is bottom left
                if (getX() < width / 2) {
                    turnRight(90 - rotation);
                }
                // Robot is bottom right
                else {
                    turnRight(90 - rotation);

                }
            }

            turnRight(45);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        while (getEnergy() > 0) {
            fire(1);
        }
    }
}
