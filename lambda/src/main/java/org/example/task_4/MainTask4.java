package org.example.task_4;

import java.util.*;

public class MainTask4 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Количество несовершеннолетних (<18 лет)
        long underageCount = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underageCount);

        // Список фамилий призывников (мужчины 18-27 лет)
        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("Количество призывников: " + conscripts.size());
        //System.out.println("Список призывников: " + conscripts);

        // Работоспособные с высшим образованием
        List<Person> workablePeople = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() <= 60) ||
                        (p.getSex() == Sex.MAN && p.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Количество работоспособных с высшим образованием: " + workablePeople.size());
        //System.out.println("Список работоспособных с высшим образованием: " + workablePeople);
    }
}
