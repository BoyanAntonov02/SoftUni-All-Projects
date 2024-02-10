package Fund.examMid;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SecondTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> nameList = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String command = scanner.nextLine();
        int cntBlack = 0;
        int cntError = 0;
        while (!command.equals("Report")){
            String nameTask = command.split(" ")[0];
            String task = command.split(" ")[1];


            switch (nameTask) {
                case "Blacklist":
                    if(nameList.contains(task)){
                        int index = nameList.indexOf(task);
                        nameList.remove(index);
                        nameList.add(index, "Blacklisted");
                        System.out.println(task + " was blacklisted.");
                        cntBlack++;
                    } else System.out.println(task + " was not found.");
                    break;
                case "Error":
                  int index = Integer.parseInt(command.split(" ")[1]);
                  if(nameList.size() > index && index >= 0){
                      if(!nameList.get(index).equals("Blacklisted") && !nameList.get(index).equals("Lost")){
                          String takeName = nameList.get(index);
                          System.out.println(takeName + " was lost due to an error.");
                          nameList.set(index, "Lost");
                          cntError++;
                      }
                  }
                    break;
                case "Change":
                    int index1 = Integer.parseInt(command.split(" ")[1]);
                    String newName = command.split(" ")[2];

                    if(nameList.size() > index1 && index1 >= 0){
                        String oldName = nameList.get(index1);
                        nameList.set(index1, newName);

                        System.out.println(oldName + " changed his username to " + newName + ".");
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.println("Blacklisted names: " + cntBlack);
        System.out.println("Lost names: " + cntError);
        for (String name:nameList){
            System.out.print(name + " ");
        }

    }
}
