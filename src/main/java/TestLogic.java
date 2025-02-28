import java.util.ArrayList;

public class TestLogic {
    public static void main(String[] args) {

        System.out.println("abc");


        System.out.println("Hello World");

        // Integer Operations
        int a = 10, b = 5;
        System.out.println("Addition: " + (a + b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Is a greater than b? " + (a > b));

        // Floating Point Numbers
        double pi = 3.14159;
        System.out.println("Value of Pi: " + pi);

        // Boolean and Logical Operators
        boolean isJavaFun = true;
        System.out.println("Is Java fun? " + isJavaFun);

        // String Operations
        String greeting = "Hello, ";
        String name = "World!";
        System.out.println(greeting + name); // String concatenation

        // Arrays
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("First element in array: " + numbers[0]);

        // Loop Example
        System.out.println("Loop through array:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }

        float floatMax = Float.MAX_VALUE;
        float floatMin = Float.MIN_VALUE;
        double doubleMax = Double.MAX_VALUE;
        double doubleMin = Double.MIN_VALUE;

        System.out.println("Float Min: " + floatMin);
        System.out.println("Float Max: " + floatMax);
        System.out.println("Double Min: " + doubleMin);
        System.out.println("Double Max: " + doubleMax);

        int time = 15;
        if (time < 12) {
            System.out.println("Good morning.");
        } else if (time < 18) {
            System.out.println("Good afternoon.");
        } else {
            System.out.println("Good evening.");
        }

        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }

        for (int j = 0; j < 30; j++,j*=3) {
            System.out.println(j);
        }

        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String car : cars) {
            System.out.println(car);
        }

        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        int[] IDS = {2,5,8,9};
        // 以下請完成你的代碼

        for (int ID : IDS) {
            if (ID%3 != 2) {
                System.out.println("This student don't belong to this classroom: " + ID);
            }
        }

        int f1 = 0;
        int f2 = 1;
        System.out.println(f1);
        System.out.println(f2);
        for (int f=1; f<=5; f++) {
            int f3 = f1 + f2;
            System.out.println(f3);
            f1 = f2;
            f2 = f3;
        }

//        ArrayList<String> hisCars = new ArrayList<String>();
        ArrayList<String> hisCars = new ArrayList<String>();
        hisCars.add("Audi");
        hisCars.add("Maserati");
        System.out.println(hisCars);
        System.out.println(hisCars.get(1));
        System.out.println(hisCars.size());
        //hisCars.remove(1);
        System.out.println(hisCars.size());
        hisCars.add("Toyota");
        hisCars.add(1,"BMW");
        System.out.println(hisCars);
        hisCars.set(1,"Benz");
        System.out.println(hisCars);
        hisCars.clear();
        System.out.println(hisCars);

    }
}
