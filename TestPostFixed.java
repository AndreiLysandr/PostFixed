package ProiectTrei;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestPostFixed {

    public static void print() {
        System.out.println("- random(1)    ->   Folositi o ecuatie prestabilita."
                + "\t\n- insert(2)    ->   Introduceti o excuatie."
                + "\t\n- evaluate(3)  ->   Calculati o ecuatie postfixata."
                + "\t\n- exit(4)      ->   Inchideti aplicatia."
                + "\t\n- help(0)      ->   Afisati setul de comenzi disponibil.");
    }

    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        PostfixedForm postfix = new PostfixedForm();

        String premade1 = "3+2^5/(3-2+9*5/6)^4/3+1+9/5*3^4/9";
        String premade2 = "3+9-(1/3^2)+3*3-5+4*(2/9)";
        String premade3 = "1+5-1+6^2^(2/9)+2-8";
        String premade4 = "3+(2+1)*2^3^2-8/(5-1*2/2)";
        String premade5 = "(9+3)^2-5*9+9*(9+2^7)/2^8";

        boolean hasPostFixed = false;
        boolean exit = false;
        String command;
        int rnd;
        String result;
        String postfixResult = "";

        System.out.println("\nPostfixed form");
        System.out.println("\nSetul disponibil de comenzi este:");
        print();

        while (!exit) {

            int eqResult;

            System.out.println("\nVa rog alegeti urmatoarea comanda:");
            command = sc.next();

            switch (command) {
                case "1", "random":
                    rnd = random.nextInt(1,6);

                    result = getRandomEq(premade1, premade2, premade3, premade4, premade5, rnd);

                    if (result != null) {
                        postfixResult = postfix.postFixed(result);
                    }
                    hasPostFixed = true;

                    System.out.println("\nEcuatia generata automat este: " + result
                            + "\nEcuatia sub forma postfixata este: " + postfixResult);
                    break;

                case "2", "insert":
                    System.out.println("\nIntroduceti ecuatia:");
                    result = sc.next();

                    postfixResult = postfix.postFixed(result);
                    hasPostFixed = true;

                    System.out.println("\nEcuatia: " + result + " a fost salvata cu succes!"
                            + "\nEcuatia sub forma postfixata este: " + postfixResult);
                    break;

                case "3", "evaluate":
                    if(!hasPostFixed) {
                        System.out.println("\nNu exista o ecuatie postfixata!"
                                + "\nVa rog generati o ecuatie!");
                        break;
                    }
                    eqResult = postfix.evaluatePostfixed(postfixResult);

                    System.out.println("\nRezultatul ecuatiei postfixate \"" + postfixResult + "\" este: " + eqResult);
                    break;

                case "4", "exit":
                    exit = true;
                    System.out.println("\nVa multumim!" + "\nO zi buna!");

                    break;

                case "0", "help":
                    System.out.println("\nSetul disponibil de comenzi este:");
                    print();

                    break;

                default:
                    System.out.println("\n\"" + command + "\" nu este o comanda disponibila!"
                            + "\nComenzile disponibile sunt:");
                    print();

                    break;
            }
        }
    }

    private static String getRandomEq(String premade1, String premade2, String premade3, String premade4,
                                      String premade5, int rnd) {
        switch (rnd) {
            case 1:
                return premade1;
            case 2:
                return premade2;
            case 3:
                return premade3;
            case 4:
                return premade4;
            case 5:
                return premade5;
        }
        return null;
    }
}
