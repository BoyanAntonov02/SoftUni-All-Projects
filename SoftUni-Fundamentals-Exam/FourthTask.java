package Fund.FundFinalExam;

import java.util.Scanner;

public class FourthTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String username = scanner.nextLine();

        String command = scanner.nextLine();

        while (!command.equals("Registration")){

            String[] commandParts = command.split(" ");
            String commandName = commandParts[0];

            switch (commandName){
                case "Letters":
                    String lowORup = commandParts[1];
                    if (lowORup.equals("Lower")){
                        username = username.toLowerCase();
                    } else username = username.toUpperCase();

                    System.out.println(username);

                    break;

                case "Reverse":
                    int start = Integer.parseInt(commandParts[1]);
                    int end = Integer.parseInt(commandParts[2]) + 1;

                    if(start >= 0 && start < username.length() && end >= 0 && end < username.length()){
                       String subReverse = username.substring(start, end);
                        String reversedSubstring = new StringBuilder(subReverse).reverse().toString();
                        System.out.println(reversedSubstring.toString());
                    }
                    break;

                case "Substring":
                    String substring = commandParts[1];

                    if (username.contains(substring)){

                        String result = username.replace(substring, "");
                        System.out.println(result);

                    }else System.out.printf("The username %s doesn't contain %s.%n", username, substring);
                    break;

                case "Replace":
                    char replaceChar = commandParts[1].charAt(0);

                    username = username.replace(replaceChar, '-');
                    System.out.println(username);
                    break;
                case "IsValid":
                    String validChar = commandParts[1];
                    if (username.contains(validChar)){
                        System.out.println("Valid username.");
                    } else System.out.println(validChar + " must be contained in your username.");
                    break;
            }



            command = scanner.nextLine();
        }


    }
}

