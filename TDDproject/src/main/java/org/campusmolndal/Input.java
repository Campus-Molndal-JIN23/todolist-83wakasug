package org.campusmolndal;

import java.util.Scanner;

public class Input {

    static Scanner input = new Scanner(System.in);

    //user input for String
    public static String Str(){
        String text;
        text= input.nextLine();
        return text;
    }

    //user input for Int
    public static int number(){
        int number = 0;
        boolean run = true;
        do{
            try{
                number= Integer.parseInt(input.nextLine());
                run = false;
            }
            catch(Exception e){
                System.out.println("Wrong input,Try Again");
            }
        }
        while(run);

        return number;
    }



}
