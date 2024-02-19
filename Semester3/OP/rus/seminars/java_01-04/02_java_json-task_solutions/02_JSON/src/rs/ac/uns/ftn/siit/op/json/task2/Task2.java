package rs.ac.uns.ftn.siit.op.json.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class Task2 {

	public static void main(String[] args) {
		try {
			List<Country> countries = extractCountries("resources/countries_cities.csv");

			Map<String, List<Country>> continentCountriesMap = new HashMap<String, List<Country>>();
			populateContinentCountriesMap(countries, continentCountriesMap);
			
			generateContinentFiles(continentCountriesMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void generateContinentFiles(Map<String, List<Country>> continentCountriesMap)
			throws IOException, JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		for(String continent : continentCountriesMap.keySet()) {
			mapper.writeValue(new File("resources/task2/" + continent + ".json"), continentCountriesMap.get(continent));
		}
	}

	private static void populateContinentCountriesMap(List<Country> countries,
			Map<String, List<Country>> continentCountriesMap) {
		for (Country country : countries) {
			String continentName = country.getContinentName();
			if (continentCountriesMap.containsKey(continentName)) {
				continentCountriesMap.get(continentName).add(country);
			} else {
				List<Country> continentCountriesList = new ArrayList<Country>();
				continentCountriesList.add(country);
				continentCountriesMap.put(continentName, continentCountriesList);
			}

		}
	}

	static List<Country> extractCountries(String file) throws FileNotFoundException, IOException {
		List<Country> countries = null;

		try (Reader reader = new FileReader(file)) {

			CsvToBean<Country> csv = new CsvToBeanBuilder<Country>(reader).withType(Country.class).withSeparator(',')
					.build(); // ne sme se odraditi skipLine jer mu trebaju nazivi kolona, automatski ce
								// odraditi skip

			countries = csv.parse();

		}

		return countries;
	}

}
