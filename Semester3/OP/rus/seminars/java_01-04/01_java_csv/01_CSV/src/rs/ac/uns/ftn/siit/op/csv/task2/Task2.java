package rs.ac.uns.ftn.siit.op.csv.task2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class Task2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<StudentScore> studentScores = readStudentScores();
		printStudentScores(studentScores);

		Map<String, Student> studentMap = new HashMap<>();
		for (StudentScore studentScore : studentScores) {
			if (!studentMap.containsKey(studentScore.getIndex())) {
				studentMap.put(studentScore.getIndex(),
						new Student(studentScore.getIndex(), studentScore.getFirstName(), studentScore.getLastName()));
			}
		}
		
		Map<String, List<StudentScore>> scoresPerStudentMap = new HashMap<>();
		populateScoresPerStudentMap(studentScores, scoresPerStudentMap);

		a_studentScoreCounts(studentScores, studentMap, scoresPerStudentMap);
		b_studentAchievements(studentMap, scoresPerStudentMap);

	}

	private static void b_studentAchievements(Map<String, Student> studentMap,
			Map<String, List<StudentScore>> scoresPerStudentMap) throws IOException {
		try (CSVWriter writer = new CSVWriter(new FileWriter("resources/task2/studentAchievements.csv"), ',',
				CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END)) {
			writer.writeNext("index,firstName,lastName,completedCourses,avgScoreOnCompletedCourses".split(","));
			
			for(String index: scoresPerStudentMap.keySet()) {
				List<StudentScore> oneStudentScores = scoresPerStudentMap.get(index);
				
				int completedCourses = 0;
				int scoreSum = 0;
				
				for(StudentScore studentScore : oneStudentScores) {
					if(studentScore.getScore() > 5) {
						completedCourses++;
						scoreSum += studentScore.getScore();
					}
				}
				
				String[] line = new String[5];
				line[0] = index;
				line[1] = studentMap.get(index).getFirstName();
				line[2] = studentMap.get(index).getLastName();
				line[3] = Integer.toString(completedCourses);
				line[4] = Double.toString((double)scoreSum/completedCourses);

				writer.writeNext(line);

			}
		}
	}

	static void a_studentScoreCounts(List<StudentScore> studentScores, Map<String, Student> studentMap, Map<String, List<StudentScore>> scoresPerStudentMap) {


		for (int score = 5; score <= 10; score++) {
			try (CSVWriter writer = new CSVWriter(new FileWriter("resources/task2/studentScoreCount_" + score + ".csv"))) {
				writer.writeNext("index,firstName,lastName,scoreCount".split(","));

				for (String index : scoresPerStudentMap.keySet()) {
					List<StudentScore> oneStudentScores = scoresPerStudentMap.get(index);

					int scoreCount = 0;
					for (StudentScore studentScore : oneStudentScores) {
						if (studentScore.getScore() == score) {
							scoreCount++;
						}
					}

					String[] line = new String[4];
					line[0] = index;
					line[1] = studentMap.get(index).getFirstName();
					line[2] = studentMap.get(index).getLastName();
					line[3] = Integer.toString(scoreCount);

					writer.writeNext(line);

				}
			} catch (IOException e) {
				System.out.println("I/O error occured");

			}
		}
	}

	private static void populateScoresPerStudentMap(List<StudentScore> studentScores,
			Map<String, List<StudentScore>> scoresPerStudentMap) {
		for (StudentScore studentScore : studentScores) {
			if (scoresPerStudentMap.containsKey(studentScore.getIndex())) {
				scoresPerStudentMap.get(studentScore.getIndex()).add(studentScore);
			} else {
				List<StudentScore> oneStudentScores = new ArrayList<StudentScore>();
				oneStudentScores.add(studentScore);
				scoresPerStudentMap.put(studentScore.getIndex(), oneStudentScores);
			}
		}
	}

	private static void printStudentScores(List<StudentScore> studentScores) {
		for (StudentScore studentScore : studentScores) {
			System.out.println(studentScore);

		}
	}

	static List<StudentScore> readStudentScores() throws FileNotFoundException, IOException {
		String CSV_FILE_NAME = "resources/task2/studentske_ocene.csv";

		try (Reader reader = new FileReader(CSV_FILE_NAME)) {
			CsvToBean<StudentScore> csv = new CsvToBeanBuilder<StudentScore>(reader).withSkipLines(1)
					.withType(StudentScore.class).withSeparator(',').build();

			List<StudentScore> studentScores = csv.parse();

			return studentScores;

		}
	}

}
