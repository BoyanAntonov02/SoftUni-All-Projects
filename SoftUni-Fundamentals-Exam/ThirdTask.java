package Fund.examMid;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ThirdTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> phoneList =
                Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equals("End")){

            String command1 = command.split(" - ")[0];
            String phone = command.split(" - ")[1];

            switch (command1){
                case "Add":

                    if(!phoneList.contains(phone)){
                        phoneList.add(phone);
                    }

                    break;
                case "Remove":
                    if(phoneList.contains(phone)){
                        phoneList.remove(phone);
                    }
                    break;
                case "Bonus phone":
                    String oldPhone = phone.split(":")[0];
                    String newPhone = phone.split(":")[1];
                    if(phoneList.contains(oldPhone)){
                        int index = phoneList.indexOf(oldPhone);
                        phoneList.add(index + 1, newPhone);
                    }
                    break;
                case "Last":
                    if(phoneList.contains(phone)){
                        phoneList.remove(phone);
                        phoneList.add(phone);
                    }
                    break;
            }


            command = scanner.nextLine();
        }

        System.out.println(String.join(", ", phoneList));

    }
}
