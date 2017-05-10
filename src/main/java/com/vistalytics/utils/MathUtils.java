package com.vistalytics.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import one.util.streamex.DoubleStreamEx;

public class MathUtils {

	public static Double getAbsoluteChange(Double a, Double b) {
		return roundValues(a - b);
	}

	public static Double getPercentageChange(Double a, Double b) {
		return Math.abs(roundValues(((a - b) / b) * 100));
	}

	public static Double getAverageChangeOverRange(List<Double> values) {
		OptionalDouble value = Arrays.stream(values.toArray())
				.mapToDouble(a -> roundValues((Double) a)).average();
		return value.isPresent() ? value.getAsDouble() : 0;
	}

	// public static Double getAverageAbsoluteChangeOverRange(List<Double>
	// values) {
	// double[] valueArray = getAllAverageAbsoluteChangeOverRange(values);
	// System.out.println(valueArray[0] + "  " + valueArray[1] + "  " +
	// valueArray[2] + "  " + valueArray[3]);
	// OptionalDouble value = Arrays.stream(valueArray).map(a -> a).average();
	// return value.isPresent() ? value.getAsDouble() : 0;
	// }
	//
	// public static Double getAveragePercentageChangeOverRange(List<Double>
	// values) {
	// double[] valueArray = getAllPercentageChangeOverRange(values);
	// System.out.println(valueArray[0] + "  " + valueArray[1] + "  " +
	// valueArray[2] + "  " + valueArray[3]);
	// OptionalDouble value = Arrays.stream(valueArray).map(a -> a).average();
	// return value.isPresent() ? roundValues(value.getAsDouble()) : 0;
	// }

	public static Double getAverageAbsoluteChangeOverRange(List<Double> values) {
		OptionalDouble value = DoubleStreamEx.of(values).filter(a -> a > 0)
				.pairMap((a, b) -> b - a).average();
		return value.isPresent() ? roundValues(value.getAsDouble()) : 0;
	}

	public static Double getAveragePercentageChangeOverRange(List<Double> values) {
		OptionalDouble value = DoubleStreamEx.of(values).filter(a -> a > 0)
				.pairMap((a, b) -> ((b - a) / a) * 100).average();
		return value.isPresent() ? roundValues(value.getAsDouble()) : 0;
	}

	private static Double roundValues(Double a) {
		BigDecimal bigDecimal = new BigDecimal(a);
		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
