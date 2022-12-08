import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<City> list = parse();
        sortCity(list);
        sortDistrict(list);
        List<City> list1 = parse();
        maxPopulation(list1);
        countCityInRegion(list1);
    }


    private static void countCityInRegion(List<City> list) {
        Map<String, Integer> countCity = new HashMap<>();
        for (City x : list) {
            if (!countCity.containsKey(x.getRegion())) {
                countCity.put(x.getRegion(), 1);
            } else {
                countCity.put(x.getRegion(), countCity.get(x.getRegion()) + 1);
            }
        }
        countCity.forEach((key, value) -> System.out.println(key + " = " + value));
    }

    private static void maxPopulation(List<City> list) {
        City[] array = list.toArray(new City[0]);
        City x = array[0];


        for (int i = 0; i < array.length; i++) {
            if (x.getPopulation() < array[i].getPopulation()) {
                x = array[i];
            }
        }
        System.out.printf("[%d]=%d\n", Arrays.asList(array).indexOf(x), x.getPopulation());

    }

    private static void sortDistrict(List<City> list) {
        list.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        System.out.println(list);
    }

    private static void sortCity(List<City> cityList) {
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return c1.toString().compareToIgnoreCase(c2.toString());
            }
        });
        System.out.println(cityList);
    }

    private static List<City> parse() throws IOException {
        List<City> cityList = new ArrayList<>();
        int index = 0;
        Scanner scanner = new Scanner(new FileReader("src/city_ru.csv", StandardCharsets.UTF_8));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            City city = new City();
            Scanner sc = new Scanner(line);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String data = sc.next();
                switch (index) {
                    case 0:
                        city.setId(Integer.parseInt(data));
                        break;
                    case 1:
                        city.setName(data);
                        break;
                    case 2:
                        city.setRegion(data);
                        break;
                    case 3:
                        city.setDistrict(data);
                        break;
                    case 4:
                        city.setPopulation(Integer.parseInt(data));
                        break;
                    case 5:
                        city.setFoundation(data);
                        break;
                }
                index++;
            }
            index = 0;
            cityList.add(city);
        }
        System.out.println(cityList);
        return cityList;
    }
}
