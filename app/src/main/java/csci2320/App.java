/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package csci2320;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static interface Fruit {
        void juice();
    }

    public static class Banana implements Fruit {
        public void juice() {}
    }

    public static class Apple implements Fruit {
        public void juice() {}
    }

    public static void main(String[] args) {
        System.out.println(args.length);
        for (String s: args) {
            System.out.println(s);
        }
        System.out.println(new App().getGreeting());
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();

        int a = 5;
        Integer b = 7;
        int c = b;

        List<Apple> fruits = new ArrayList<>();
        fruits.add(new Apple());
        smoothy(fruits);
        // Fruit f = (Banana)fruits.get(0);
    }

    public static void smoothy(List<? extends Fruit> fruit) {
        for (var f : fruit) {
            f.juice();
        }
        // fruit.add(new Banana());
    }
}
