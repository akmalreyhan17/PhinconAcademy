import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("Masukkan nama: "); 

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.print("Selamat datang " + name);
        scanner.close();
    }
}


