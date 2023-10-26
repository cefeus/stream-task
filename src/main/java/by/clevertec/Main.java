package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.CostUtil;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getAge() > 10 && animal.getAge() < 20)
                .sorted(Comparator.comparing(Animal::getAge)).skip(14).limit(7));
    }

    // Отобрать всех животных из Японии (Japanese)
    // Записать породу в UPPER_CASE, если пол Female
    // Преобразовать к строкам породы животных и вывести в консоль
    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .map(animal -> {
                    if (animal.getBread().equals("Female")) return animal.getBread().toUpperCase();
                    else return animal.getBread();
                })
                .collect(Collectors.toList()));
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getAge() > 30 && animal.getOrigin().startsWith("A"))
                .map(Animal::getOrigin).distinct().sorted().collect(Collectors.toList()));
    }

    public static Long task4() {
        List<Animal> animals = Util.getAnimals();
        return animals.stream().filter(animal -> animal.getGender().equals("Female")).count();
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getAge() > 20 && animal.getAge() < 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian")));
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(animal -> animal.getGender().equals("Male") || animal.getGender().equals("Female")));
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().noneMatch(animal -> animal.getOrigin().equals("Oceania")));
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge)));
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .map(chars -> chars.length)
                .min(Integer::compareTo));
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().mapToInt(Animal::getAge).sum());
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge).average());
    }


    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream().filter(person -> person.getGender().equals("Male"))
                .filter(person -> {
                    var age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
                    return age < 27 && age > 18;
                })
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .toList().forEach(System.out::println);
    }

    public static void task13() {
        Comparator<Person> comparator = (o1, o2) -> {
            int age1 = LocalDate.now().getYear() - o1.getDateOfBirth().getYear();
            int age2 = LocalDate.now().getYear() - o2.getDateOfBirth().getYear();
            return (age1 <= 18 || age1 >= 60 ? 1 : 0) + (age2 <= 18 || age2 >= 60 ? -1 : 0);
        };

        List<House> houses = Util.getHouses();
        System.out.println(houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .map(house -> house.getPersonList().stream())
                .flatMap(house -> Stream.concat(house, houses.stream()
                        .flatMap(hs -> hs.getPersonList().stream())
                        .sorted(comparator))
                )
                .distinct()
                .limit(500));
    }


    public static void task14() {
        List<Car> cars = Util.getCars();
        double costPerTon = 7.14;

        Map<String, Double> countryShippingCost = new HashMap<>();

        double totalRevenue = cars.stream()
                .map(car -> {
                    String country = CostUtil.computeShippingCost(car);
                    double shippingCost = car.getMass() * costPerTon;
                    countryShippingCost.put(country, countryShippingCost.getOrDefault(country, 0.0) + shippingCost);
                    return Map.entry(country, shippingCost);
                })
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingDouble(Map.Entry::getValue)
                ))
                .values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        countryShippingCost.forEach((country, shippingCost) -> {
            System.out.println("Страна: " + country + ", Стоимость доставки: $" + shippingCost);
        });

        System.out.println("Общая выручка: $" + totalRevenue);
    }


    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        System.out.println(flowers.stream().sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower -> (int) flower.getCommonName().toUpperCase().charAt(0) <= 84
                        && (int) flower.getCommonName().toUpperCase().charAt(0) >= 67)
                .filter(flower -> flower.isShadePreferred()
                                && (flower.getFlowerVaseMaterial().contains("Glass")
                                || flower.getFlowerVaseMaterial().contains("Aluminium")
                                || flower.getFlowerVaseMaterial().contains("Steel")
                        )
                )
                .mapToDouble(flower -> flower.getWaterConsumptionPerDay() * 5 * 365 * 1.39)
                .sum());
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream().filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .map(student -> student.getSurname() + ": " + student.getAge()).toList());
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream().map(Student::getGroup).distinct().toList());
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream().
                collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue()).toList());
    }

    public static void task19() {
        String studentGroup = "C-2";
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        System.out.println(students.stream()
                .filter(student -> student.getGroup().equals(studentGroup))
                .collect(Collectors.filtering(
                                student -> examinations.stream()
                                        .anyMatch(examination -> examination.getId() == student.getId()
                                                && examination.getExam3() > 4), Collectors.toList()
                        )
                )
        );
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(
                        student -> examinations.stream()
                                .filter(examination -> examination.getStudentId() == student.getId())
                                .mapToDouble(Examination::getExam1)
                                .findFirst()
                                .orElse(0.0)
                )))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .stream().collect(Collectors.toSet()).forEach(System.out::println);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting())));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream()
                .collect(Collectors.toMap(
                        Student::getFaculty,
                        Student::getAge,
                        Math::min)));
    }
}
