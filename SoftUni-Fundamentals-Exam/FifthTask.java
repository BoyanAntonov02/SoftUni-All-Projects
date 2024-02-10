package Fund.FundFinalExam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FifthTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        Map<String, Double> distributorsMap = new LinkedHashMap<>();
        Map<String, Double> clientMap = new LinkedHashMap<>();

        while (!command.equals("End")){
            String[] commandParts = command.split(" ");
            String commandName = commandParts[0];

            switch (commandName){
                case "Deliver":
                    String distributorName = commandParts[1];
                    double moneySpent = Double.parseDouble(commandParts[2]);

                    if(!distributorsMap.containsKey(distributorName)){
                        distributorsMap.put(distributorName, moneySpent);
                    }else {
                        double currentMoneySpent = distributorsMap.get(distributorName);
                        currentMoneySpent += moneySpent;
                        distributorsMap.put(distributorName, currentMoneySpent);
                    }
                    break;
                case "Return":
                    String distributorName2 = commandParts[1];
                    double moneyReturn = Double.parseDouble(commandParts[2]);
                    if (distributorsMap.containsKey(distributorName2)){
                        double currentMoney = distributorsMap.get(distributorName2);
                        currentMoney = currentMoney - moneyReturn;
                        if (currentMoney <= 0){
                            distributorsMap.remove(distributorName2);
                        }else distributorsMap.put(distributorName2, currentMoney);
                    }
                    break;
                case "Sell":
                    String clientName = commandParts[1];
                    double moneyEarned = Double.parseDouble(commandParts[2]);

                    if(!clientMap.containsKey(clientName)){
                        clientMap.put(clientName, moneyEarned);
                    } else {
                        double money = clientMap.get(clientName);
                        money += moneyEarned;
                        clientMap.put(clientName, money);
                    }

                    break;
            }


            command = scanner.nextLine();
        }

        double totalIncome = 0;
        for (Map.Entry<String, Double> entry : clientMap.entrySet()) {
            String clientName = entry.getKey();
            Double moneySpent = entry.getValue();
            System.out.printf("%s: %.2f%n", clientName, moneySpent);
            totalIncome += moneySpent;
        }

        System.out.println("-----------");

        for (Map.Entry<String, Double> entry : distributorsMap.entrySet()) {
            String distrubitor = entry.getKey();
            Double moneySpent = entry.getValue();
            System.out.printf("%s: %.2f%n", distrubitor, moneySpent);
        }

        System.out.println("-----------");

        System.out.printf("Total Income: %.2f%n", totalIncome);
    }
}
