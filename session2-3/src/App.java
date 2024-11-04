public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // int x = 10;
        // if (x > 5) {
        // System.out.println(x + " is greater than 5");
        // } else if (x > 3) {
        // System.out.println(x + " is greater than 3 but less than or equal to 5");
        // } else {
        // System.out.println(x + " is less than or equal to 3");
        // }

        // int x = 10;
        // int y = 20;
        // if (x > 5) {
        // System.out.println(x + " is greater than 5");
        // if (y > 5) {
        // System.out.print(" and " + y + " is too");
        // }
        // }

        // int num = 100;
        // int x = 10;
        // int y = 20;
        // int z = 30;
        // if (num > x) {
        // System.out.println(num + " is greater than x");
        // if (num > y) {
        // System.out.print(" and y");
        // if (num > z) {
        // System.out.print(" and z");
        // } else {
        // System.out.print(" but not z");
        // }
        // } else {
        // System.out.print(" but not y");
        // }
        // } else {
        // System.out.println(num + " is not greater than x");
        // }

        // int x = 10;
        // int y = 20;
        // if (x > 5 && y > 5) {
        // System.out.println(x + " and " + y + " is greater than 5");
        // } else if (x > 5 || y > 5) {
        // System.out.println("either " + x + " or " + y + " is greater than 5");
        // } else {
        // System.out.println("both " + x + " and " + y + " is not greater than 5");
        // }

        // int x = 10;
        // String xs = String.valueOf(x);
        // String result = (x > 5) ? xs + " is greater than 5" : xs + " is not greater
        // than 5";
        // System.out.println(result);

        // int x = 10;
        // int y = 20;
        // int z = 30;
        // if (x > 0) {
        // if (y > 0) {
        // if (z > 0) {
        // System.out.println("Semua positif");
        // }
        // }
        // }

        // int countryCode = 3;
        // String countryName = switch (countryCode) {
        // case 1:
        // yield "Indonesia";
        // case 2:
        // yield "Malaysia";
        // case 3:
        // yield "Thailand";
        // default:
        // yield "not registered";
        // };

        // System.out.println("My country is " + countryName);

        // String fruit = "Apple";

        // switch (fruit) {
        // case "Apple":
        // System.out.println("It's an apple");
        // break;
        // case "Banana":
        // System.out.println("It's a banana");
        // break;
        // case "Orange":
        // System.out.println("It's an orange");
        // break;
        // default:
        // System.out.println("Unknown fruit");
        // break;
        // }

        // int month = 3;
        // String season;

        // switch (month) {
        // case 12:
        // case 1:
        // case 2:
        // season = "Winter";
        // break;
        // case 3:
        // case 4:
        // case 5:
        // season = "Spring";
        // break;
        // default:
        // season = "Unknown";
        // break;
        // }

        // System.out.println("The season is " + season);

        // int day = 5;

        // String dayType = switch (day) {
        // case 1, 2, 3, 4, 5 -> "Weekday";
        // case 6, 7 -> "Weekend";
        // default -> "Invalid day";
        // };

        // System.out.println("Today is a " + dayType);

        // int score = 85;

        // String grade = switch (score) {
        // case 90 -> "A";
        // case 80 -> "B";
        // case 70 -> "C";
        // case 60 -> "D";
        // default -> {
        // if (score < 60)
        // yield "F";
        // else
        // yield "Invalid";
        // }
        // };

        // System.out.println("Grade: " + grade);

        // enum Direction {
        // NORTH, SOUTH, EAST, WEST
        // }

        // Direction dir = Direction.EAST;

        // switch (dir) {
        // case NORTH:
        // System.out.println("Heading North");
        // break;
        // case SOUTH:
        // System.out.println("Heading South");
        // break;
        // case EAST:
        // System.out.println("Heading East");
        // break;
        // case WEST:
        // System.out.println("Heading West");
        // break;
        // default:
        // System.out.println("Unknown direction");
        // break;
        // }

        // int i = 0;
        // do {
        //     System.out.println("i is: " + i);
        //     i = 1;
        // } while (i < 5);

        // for (int i = 0; i < 3; i = i + 2) {
        // System.out.println("i is: " + i);
        // }

        // int[] numbers = { 1, 2, 3, 4, 5 };

        // for (int num : numbers) {
        // System.out.println("Number: " + num);
        // }

        // int[][] matrix = {
        // {1, 2, 3},
        // {4, 5, 6},
        // {7, 8, 9}
        // };

        // for (int i = 0; i < matrix.length; i++) {
        // for (int j = 0; j < matrix[i].length; j++) {
        // System.out.print(matrix[i][j] + " ");
        // }
        // System.out.println();
        // }

        // for (int i = 0; i < 10; i++) {
        //     if (i == 5) {
        //         break; // Exit loop when i is 5
        //     }
        //     System.out.println("i is: " + i);
        // }
        
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            System.out.println("i is: " + i);
        }
        

    }
}
