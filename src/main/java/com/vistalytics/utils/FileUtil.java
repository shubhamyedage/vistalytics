package com.vistalytics.utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.vistalytics.app.App;
import com.vistalytics.models.ReportModel;;
public class FileUtil {
	private static final String[] FILE_HEADER_MAPPING = { "Keys",
			"2016-17 Absolute Difference", "2016-17 % Difference" };

	private static CSVParser parser = null;

	public static List<CSVRecord> fileReader() {
		File file = new File(App.class.getResource("/UDR_Income_Statement.csv")
				.getPath());
		CSVFormat format = CSVFormat.DEFAULT.withNullString("0");
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

	public static void fileWriter(List<ReportModel> modals) {
		CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("\n");
		FileWriter writer = null;
		CSVPrinter printer = null;
		try {
			writer = new FileWriter(App.class
					.getResource("/UDR_Report.csv").getPath());
			printer = new CSVPrinter(writer, format);
			printer.printRecord(FILE_HEADER_MAPPING);
			for(ReportModel rModal : modals){
				List modal = new ArrayList();
				modal.add(rModal.getKey());
				modal.add(Double.toString(rModal.getAbsoluteDiff()));
				modal.add(Double.toString(rModal.getPercentageDiff()));
				printer.printRecord(modal);				
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
