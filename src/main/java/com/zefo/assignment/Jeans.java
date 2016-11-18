package com.zefo.assignment;

public class Jeans {
	private String waist;
	private String color;
	private String brand;
	public String getWaist() {
		return waist;
	}

	public void setWaist(String waist) {
		this.waist = waist;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	
	
	Jeans(String waist, String color, String brand) {
		this.waist = waist;
		this.color = color;
		this.brand = brand;
	}
}
