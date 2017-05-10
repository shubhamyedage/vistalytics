package com.vistalytics.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.vistalytics.app.App;

public class FileUtil {
	private static final String[] WRITE_FILE_HEADER_MAPPING = { "Keys", "Absolute change from start of period to end of period",  "Percentage change from start of period to end of period",
		"Absolute change for last two years", "Percentage change for last two years", "Average change", "Average absolute change", "Average percentage change"};

	private static final String[] READ_FILE_HEADER_MAPPING = { "Fiscal year ends in December. USD in millions except per share data.","2012-12", "2013-12", "2014-12", "2015-12", "2016-12", "TTM"};
	
	private static CSVParser parser = null;

	public static List<CSVRecord> fileReader() {
		File file = new File(App.class.getResource("/FSIC_Financial_Statement.csv")
				.getPath());
		System.out.println(file.exists());
		CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines().withHeader(READ_FILE_HEADER_MAPPING).withSkipHeaderRecord();
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			parser = new CSVParser(reader, format);
			return parser.getRecords();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				parser.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public static void fileWriter(List<Map<Integer, String>> modals) {
		CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("\n");
		FileWriter writer = null;
		CSVPrinter printer = null;
		try {
			writer = new FileWriter(App.class.getResource("/FSIC_REPORT.csv")
					.getPath());
			printer = new CSVPrinter(writer, format);
			printer.printRecord(WRITE_FILE_HEADER_MAPPING);
			for (Map reportModal : modals) {
				printer.printRecord(reportModal.values());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				printer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
