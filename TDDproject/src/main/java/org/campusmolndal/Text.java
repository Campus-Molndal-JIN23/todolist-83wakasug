package org.campusmolndal;

public class Text {


        public static void firstMenu (){
            System.out.println("Please Enter Choice");
            System.out.println("1:Show data");
            System.out.println("2:Add data");
            System.out.println("3:Update data");
            System.out.println("4:Delete Data");
        }

        public static void showDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Show ALL TODO List");
            System.out.println("2:Show a List");
            System.out.println("3:Show ALL Users");
            System.out.println("4:Show a user");
        }

        public static void addDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Add a TODO List");
            System.out.println("2:Add a user");
        }

        public static void updateDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Update a TODO List");
            System.out.println("2:Update a user");
        }

        public static void updateTODOList(){
            System.out.println("1:update TODO");
            System.out.println("2:update status");
            System.out.println("3:update assignedTo");
        }

        public static void updateUser(){
            System.out.println("1:update Name");
            System.out.println("2:Update Age");
            System.out.println("3:Update ");
        }

         public static void deleteDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Delete a TODO List");
            System.out.println("2:Delete a user");
        }

        public static void wrongInput(){
            System.out.println("Wrong Input. Please Enter Again.");
        }


}
