package org.example.tesco.flyweight;

public class AnimalEval {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal cat = animalFactory.getObject("CAT");
        cat.setName("catty");
        cat.printAttributes();

        Animal dog = animalFactory.getObject("DOG");
        dog.setName("doggy");
        dog.printAttributes();
    }
}
