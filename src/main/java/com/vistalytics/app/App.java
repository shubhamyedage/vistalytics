package com.vistalytics.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

import com.vistalytics.utils.FileUtil;
import com.vistalytics.utils.MathUtils;
import com.vistalytics.utils.StringConstants;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		List<CSVRecord> records = FileUtil.fileReader();
		List<Map<Integer, String>> modal = new ArrayList<>();

		for (CSVRecord record : records) {
			if (record.size() > 1) {
				Map<Integer, String> reportValues = new HashMap<>();
				List<Double> values = new ArrayList<>();
				for (String value : record) {
					try {
						if (!value.matches(StringConstants.FILTER_ALPHABETS) && !values.contains("-")) {
							values.add(value.isEmpty() ? 0 : Double
									.parseDouble(value));
						}
					} catch (NullPointerException e) {
						values.add(0.0);
					}
				}
				if(values.size() != 0){
					System.out.println(record.get(0) + ": " + values.toString());
					// Add key.
					reportValues.put(1, record.get(0));

					int recordSize = values.size();
					boolean filter = values.get(0) == 0
							|| values.get(recordSize - 2) == 0;

					// Change Between Start of the range and end of the range.
					String change = filter ? "-" : MathUtils.getAbsoluteChange(
							values.get(recordSize - 2), values.get(0)).toString();
					reportValues.put(2, change);

					change = filter ? "-" : MathUtils.getPercentageChange(
							values.get(recordSize - 2), values.get(0)).toString();
					reportValues.put(3, "(" + change + ")");

					// Change Between last two years.
					filter = values.get(recordSize - 2) == 0
							|| values.get(recordSize - 3) == 0;
					change = filter ? "-" : MathUtils.getAbsoluteChange(
							values.get(recordSize - 2), values.get(recordSize - 3))
							.toString();
					reportValues.put(4, change);

					change = filter ? "-" : MathUtils.getPercentageChange(
							values.get(recordSize - 2), values.get(recordSize - 3))
							.toString();
					reportValues.put(5, "(" + change + ")");

					// Calculate average of values over range.
					values.remove(recordSize - 1);
					change = MathUtils.getAverageChangeOverRange(values).toString();
					reportValues.put(6, change);

					// Calculate average absolute change in values.
					change = MathUtils.getAverageAbsoluteChangeOverRange(values)
							.toString();
					reportValues.put(7, change);

					// Calculate average absolute change in values.

					change = MathUtils.getAveragePercentageChangeOverRange(values)
							.toString();
					reportValues.put(8, "(" + change + ")");
					modal.add(reportValues);
				}
			}
		}
		FileUtil.fileWriter(modal);
	}
}
