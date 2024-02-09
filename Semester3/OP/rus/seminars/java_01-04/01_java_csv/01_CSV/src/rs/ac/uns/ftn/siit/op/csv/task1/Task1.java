package rs.ac.uns.ftn.siit.op.csv.task1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class Task1 {

	public static void main(String[] args) {
		try {
			List<Point> points = readPoints();
			writePoints(points, "resources/task1/output.csv");

			Collections.sort(points, Collections.reverseOrder());
			writePoints(points, "resources/task1/output_sorted.csv");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void writePoints(List<Point> points, String path)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = new FileWriter(path)) {

			StatefulBeanToCsv<Point> beanToCsv = new StatefulBeanToCsvBuilder<Point>(writer).withApplyQuotesToAll(false)
					.withSeparator('#').build();
			beanToCsv.write(points);
		}

	}

	static List<Point> readPoints() throws FileNotFoundException, IOException {
		String CSV_FILE_NAME = "resources/task1/koordinate_tacaka.csv";

		try (Reader reader = new FileReader(CSV_FILE_NAME)) {
			CsvToBean<Point> csv = new CsvToBeanBuilder<Point>(reader).withSkipLines(1).withType(Point.class)
					.withSeparator(',').build();

			List<Point> points = csv.parse();

			for (Point point : points) {
				point.calculateDistance(); // mora da se postavi nakon citanja
			}

			return points;

		}
	}

}
