package com.apmods.hpspells.entity;

public enum EnumBroomstick {
	BROOMSTICK("Broomstick", 0.5, 0.1),
	FIREBOLT("Firebolt", 1.1, 0.25);
	
	private String name;
	private double speed, acceleration;
	
	private EnumBroomstick(String name, double speed, double acceleration){
		this.name = name;
		this.speed = speed;
		this.acceleration = acceleration;
	}

	public String getName() {
		return name;
	}

	public double getSpeed() {
		return speed;
	}

	public double getAcceleration() {
		return acceleration;
	}
}
