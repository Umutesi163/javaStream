import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many people do you want to enter?: ");
        int count = Integer.parseInt(scanner.nextLine());

        List<Person> people = Stream.generate(() -> {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("City: ");
            String city = scanner.nextLine();
            return new Person(name, age, city);
        }).limit(count).collect(Collectors.toList());
        if (people.size() >= 2) {
            people.get(1).setName("OriginalUpdated Name");
            System.out.println("Changed name of second person.");
        }
        System.out.println("\nGrouped by age:");
        people.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())))
                .forEach((age, names) -> System.out.println(age + " : " + names));
        System.out.println("\nCount by city:");
        people.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
                .forEach((city, countNum) -> System.out.println(city + " : " + countNum));

        scanner.close();
    }
}