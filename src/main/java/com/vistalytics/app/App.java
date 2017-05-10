package com.vistalytics.app;

import java.util.ArrayList;
import java.util.List;

import one.util.streamex.DoubleStreamEx;

import org.apache.commons.csv.CSVRecord;

import com.vistalytics.models.ReportModel;
import com.vistalytics.utils.FileUtil;
/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		List<CSVRecord> records = null;
		records = FileUtil.fileReader();

		List<ReportModel> modal = new ArrayList<ReportModel>();
		for (CSVRecord record : records) {
			if (record.size() > 1 && record.getRecordNumber() != 2) {
				List<Double> values = new ArrayList<Double>();
				values.add((!record.get(1).isEmpty()) ? Double
						.parseDouble(record.get(1)) : 0);
				values.add((!record.get(2).isEmpty()) ? Double
						.parseDouble(record.get(2)) : 0);
				values.add((!record.get(3).isEmpty()) ? Double
						.parseDouble(record.get(3)) : 0);
				values.add((!record.get(4).isEmpty()) ? Double
						.parseDouble(record.get(4)) : 0);
				values.add((!record.get(5).isEmpty()) ? Double
						.parseDouble(record.get(5)) : 0);
				
				double[] diff = DoubleStreamEx.of(values)
						.pairMap((a, b) -> b - a).toArray();
				double[] diffInPer = DoubleStreamEx.of(values)
						.pairMap((a, b) -> ((b - a) / a) * 100).toArray();
				modal.add(new ReportModel(record.get(0), diff[diff.length - 1], diffInPer[diffInPer.length - 1]));
			}
		}
		FileUtil.fileWriter(modal);
	}
}
