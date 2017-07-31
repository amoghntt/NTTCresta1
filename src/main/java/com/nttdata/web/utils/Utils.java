package com.nttdata.web.utils;

public class Utils {

	public static ColorEnum getColorEnum(int ucl, int lcl, int predictedValue) {
		if (Double.compare(ucl, predictedValue) > 0 && Double.compare(predictedValue, lcl) > 0) {
			return ColorEnum.GREEN;
		} else {
			return ColorEnum.RED;
		}
	}

	public static ColorEnum getColorEnum(int[] ucl, int[] lcl, int predictedValue) {
		if (predictedValue >= lcl[0] && predictedValue < ucl[0]) {
			return ColorEnum.GREEN;
		} else if (predictedValue >= lcl[1] && predictedValue < ucl[1]) {
			return ColorEnum.AMBER;
		} else {
			return ColorEnum.RED;
		}
	}
}
