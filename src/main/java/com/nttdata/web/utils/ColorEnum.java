package com.nttdata.web.utils;

public enum ColorEnum {
	RED("rgba(255,0,0,1)"), GREEN("rgba(0,255,0,1)"), AMBER("rgba(255,191,0,1)");
	String colorValue;

	ColorEnum(String colorValue) {
		this.colorValue = colorValue;
	}

	public String getColorValue() {
		return colorValue;
	}

	public void setColorValue(String colorValue) {
		this.colorValue = colorValue;
	}

	public static String getColorValueByEnum(ColorEnum colorEnumType) {
		for (ColorEnum colorEnum : values()) {
			if (colorEnumType == colorEnum) {
				return colorEnumType.getColorValue();
			}
		}
		throw new IllegalArgumentException("Illegal color enum type given: '" + colorEnumType + "'");
	}
}
