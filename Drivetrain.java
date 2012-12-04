package org.usfirst.frc178;

import edu.wpi.first.wpilibj.*;

public class Drivetrain {
	private Victor victorLeft, victorLeft2, victorRight, victorRight2;

	private Joystick joystick;

	private double robotY, robotTwist;
	private double speed;

	/**
	 * Constructor for Drivetrain class
	 * @param frontLeftN
	 * @param frontRightN
	 * @param rearLeftN
	 * @param rearRightN
	 * @param speed
	 */
	public Drivetrain(int victorLeft, int victorLeft2, int victorRight, int victorRight2, final double speed) {

		this.victorLeft = new Victor(victorLeft) {
			public void set(double d) {
				super.set(d * speed);
			}
		};

		this.victorLeft2 = new Victor(victorLeft2) {
			public void set(double d) {
				super.set(d * speed);
			}
		};

		this.victorRight = new Victor(victorRight) {
			public void set(double d) {
				super.set(d * speed);
			}
		};

		this.victorRight2 = new Victor(victorRight2) {
			public void set(double d) {
				super.set(d * speed);
			}
		};

		this.speed = speed;
	}


	public void setJoystick(Joystick joystick) {
		this.joystick = joystick;
	}

	/**
	 * Getter function for front left motor speed
	 */
	public double getVictorLeft() {
		return this.victorLeft.get();
	}

	/**
	 * Getter function for front right motor speed
	 */
	public double getVictorLeft2() {
		return this.victorLeft2.get();
	}

	/**
	 * Getter function for rear left motor speed
	 */
	public double getVictorRight() {
		return this.victorRight.get();
	}

	/**
	 * Getter function for rear right motor speed
	 */
	public double getVictorRight2() {
		return this.victorRight2.get();
	}

	/**
	 * Continuously called function to drive
	 */
	public void drive() {
		if (joystick.getTrigger()) {
			speed = speed * 1/2;
		}

		robotY = -joystick.getY();
		robotTwist = -joystick.getTwist();

		victorLeft.set(robotY + (robotTwist * 1/4));
		victorLeft2.set(robotY + (robotTwist * 1/4));
		victorRight.set(-robotY + (robotTwist * 1/4));
		victorRight2.set(-robotY + (robotTwist * 1/4));
	}

	/**
	 * Set front left victor speed
	 */
	public void victorLeftSet(double value) {
		victorLeft.set(value);
	}

	/**
	 * Set front right victor speed
	 */
	public void victorLeft2Set(double value) {
		victorLeft2.set(value);
	}

	/**
	 * Set rear left victor speed
	 */
	public void victorRightSet(double value) {
		victorRight.set(value);
	}

	/**
	 * Set rear right victor speed
	 */
	public void victorRight2Set(double value) {
		victorRight2.set(value);
	}

	/**
	 * Sets the diminished robot speed
	 */
	public void setDiminishedSpeed(double fraction) {
		victorLeft.set(getVictorLeft() * fraction);
		victorLeft2.set(getVictorLeft2() * fraction);
		victorRight.set(getVictorRight() * fraction);
		victorRight2.set(getVictorRight2() * fraction);
	}
}