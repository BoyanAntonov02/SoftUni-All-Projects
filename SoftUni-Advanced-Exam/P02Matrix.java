package halloween;

import java.util.Scanner;

public class P02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
        }

        int rowBoat = 0;
        int colBoat = 0;
        int fishes = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == 'S') {
                    rowBoat = row;
                    colBoat = col;
                }
            }
        }

        String commands = scanner.nextLine();

        while (!commands.equals("collect the nets")){

            matrix[rowBoat][colBoat] = '-';

            switch (commands){
                case "left":
                    colBoat--;
                    break;
                case "right":
                    colBoat++;
                    break;
                case "up":
                    rowBoat--;
                    break;
                case "down":
                    rowBoat++;
                    break;
            }

            if(rowBoat < 0 || rowBoat >= n ){
                if(rowBoat < 0){
                    rowBoat = n - 1;
                }

                if(rowBoat >= n){
                    rowBoat = 0;
                }
            }
            if(colBoat < 0 || colBoat >= n){
                if(colBoat < 0){
                    colBoat = n - 1;
                }

                if(colBoat >= n){
                    colBoat = 0;
                }
            }

            if(matrix[rowBoat][colBoat] != 'W' && matrix[rowBoat][colBoat] != '-'){
                fishes += Integer.parseInt(String.valueOf(matrix[rowBoat][colBoat]));
                System.out.println();
            }

            if(matrix[rowBoat][colBoat] == 'W'){
                System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]", rowBoat, colBoat);
                break;
            }


            matrix[rowBoat][colBoat] = 'S';
            commands = scanner.nextLine();
        }

        if(matrix[rowBoat][colBoat] != 'W') {
            if (fishes >= 20) {
                System.out.println("Success! You managed to reach the quota!");
            } else {
                System.out.print("You didn't catch enough fish and didn't reach the quota!");
                System.out.printf(" You need %d tons of fish more.%n", 20 - fishes);
            }

            if (fishes > 0) {
                System.out.printf("Amount of fish caught: %d tons.%n", fishes);
            }

            printMatrix(matrix);

        }

    }
    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

}
