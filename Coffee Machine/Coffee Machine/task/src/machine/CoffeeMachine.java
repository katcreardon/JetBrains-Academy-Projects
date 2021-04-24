package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int[] machineState = new int[]{400, 540, 120, 9, 550};
        showMenu(machineState);
    }

    static void printSteps() {
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
    }

    static void calculateIngredients() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        int cups = in.nextInt();
        int water = 200 * cups;
        int milk = 50 * cups;
        int beans = 15 * cups;
        System.out.printf("For %d cups of coffee you will need:%n%d ml of water%n%d ml of milk%n%d g of coffee beans",
                cups, water, milk, beans);
    }

    static void checkSupplies() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int water = in.nextInt() / 200;
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = in.nextInt() / 50;
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int beans = in.nextInt() / 15;
        System.out.println("Write how many cups of coffee you will need:");
        int cupsNeeded = in.nextInt();

        int maxCups = Math.min(water, (Math.min(milk, beans)));
        int n = maxCups - cupsNeeded;

        if (n == 0) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (n > 0) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", n);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", maxCups);
        }
    }

    // Similar to checkSupplies but returns the index of limited resource
    static int checkResources(int drink, int[] arr) {
        boolean[] flags = new boolean[4];

        // Check cups
        if (arr[3] < 1) {
            flags[3] = false;
        } else {
            flags[3] = true;
        }

        switch (drink) {
            case 1:
                // Check water
                if (arr[0] < 250) {
                    flags[0] = false;
                } else {
                    flags[0] = true;
                }
                // Check coffee beans
                if (arr[2] < 16) {
                    flags[2] = false;
                } else {
                    flags[2] = true;
                }
                if (!flags[0]) {
                    return 0;
                } else if (!flags[2]) {
                    return 2;
                } else if (!flags[3]) {
                    return 3;
                }
                break;
            case 2:
                // Check water
                if (arr[0] < 350) {
                    flags[0] = false;
                } else {
                    flags[0] = true;
                }
                // Check milk
                if (arr[1] < 75) {
                    flags[1] = false;
                } else {
                    flags[1] = true;
                }
                // Check coffee beans
                if (arr[2] < 20) {
                    flags[2] = false;
                } else {
                    flags[2] = true;
                }
                if (!flags[0]) {
                    return 0;
                } else if (!flags[1]) {
                    return 1;
                } else if (!flags[2]) {
                    return 2;
                } else if (!flags[3]) {
                    return 3;
                }
                break;
            case 3:
                // Check water
                if (arr[0] < 200) {
                    flags[0] = false;
                } else {
                    flags[0] = true;
                }
                // Check milk
                if (arr[1] < 100) {
                    flags[1] = false;
                } else {
                    flags[1] = true;
                }
                // Check coffee beans
                if (arr[2] < 16) {
                    flags[2] = false;
                } else {
                    flags[2] = true;
                }
                if (!flags[0]) {
                    return 0;
                } else if (!flags[1]) {
                    return 1;
                } else if (!flags[2]) {
                    return 2;
                } else if (!flags[3]) {
                    return 3;
                }
                break;
        }
        return -1;
    }

    static void printState(int[] arr) {
        // arr[0] = water
        // arr[1] = milk
        // arr[2] = coffee beans
        // arr[3] = cups
        // arr[4] = money
        System.out.printf("The coffee machine has:%n%d of water%n%d of milk%n%d of coffee beans%n%d of disposable cups"
                + "%n$%d of money%n", arr[0], arr[1], arr[2], arr[3], arr[4]);
    }

    static void showMenu(int[] arr) {
        Scanner in = new Scanner(System.in);
        String action = "";

        while (!action.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = in.nextLine();
            switch (action) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to "
                            + "main menu:");
                    String choice = in.nextLine();
                    if (choice.equals("back")) {
                        break;
                    } else {
                        buyDrink(Integer.parseInt(choice), arr);
                    }
                    break;
                case "fill":
                    fillMachine(arr);
                    break;
                case "take":
                    takeMoney(arr);
                    break;
                case "remaining":
                    System.out.println();
                    printState(arr);
                    break;
            }
            System.out.println();
        }
    }

    static void buyDrink(int drink, int[] arr) {
        int needsResource = checkResources(drink, arr);

        if (needsResource == -1) {
            System.out.println("I have enough resources, making you a coffee!");
            switch (drink) {
                // For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
                case 1:
                    arr[0] -= 250;
                    arr[2] -= 16;
                    arr[3] -= 1;
                    arr[4] += 4;
                    break;
                // For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans.
                // It costs $7.
                case 2:
                    arr[0] -= 350;
                    arr[1] -= 75;
                    arr[2] -= 20;
                    arr[3] -= 1;
                    arr[4] += 7;
                    break;
                // For the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans.
                // It costs $6.
                case 3:
                    arr[0] -= 200;
                    arr[1] -= 100;
                    arr[2] -= 12;
                    arr[3] -= 1;
                    arr[4] += 6;
                    break;
            }
        } else {
            switch (needsResource) {
                case 0:
                    System.out.println("Sorry, not enough water!");
                    break;
                case 1:
                    System.out.println("Sorry, not enough milk!");
                    break;
                case 2:
                    System.out.println("Sorry, not enough coffee beans!");
                    break;
                case 3:
                    System.out.println("Sorry, not enough cups!");
                    break;
            }
        }
    }

    static void fillMachine(int[] arr) {
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        arr[0] += in.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        arr[1] += in.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        arr[2] += in.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        arr[3] += in.nextInt();
    }

    static void takeMoney(int[] arr) {
        System.out.printf("I gave you $%d%n", arr[4]);
        arr[4] = 0;
    }
}
