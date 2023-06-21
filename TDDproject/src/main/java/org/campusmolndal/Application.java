package org.campusmolndal;

public class Application {

    DBFacade dbFacade = new DBFacade("TODO");



    public void start(){


    }

    public void firstMenu(){

        boolean run = false;
        while(run) {
            run = false;
            Text.firstMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1: showDataMenu();
                    break;
                case 2:addDataMenu();
                    break;
                case 3:updateDataMenu();
                    break;
                case 4:deleteDataMenu();
                    break;

                default: Text.wrongInput();
                        run = true;

            }
        }

    }

    public void showDataMenu(){

        boolean run = false;
        while(run) {
            run = false;
            Text.showDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }

    public void addDataMenu(){

        boolean run = false;
        while(run) {
            run = false;
            Text.addDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }


    public void updateDataMenu(){

        boolean run = false;
        while(run) {
            run = false;
            Text.updateDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }
    public void updateTODOList(){

        boolean run = false;
        while(run) {
            run = false;
            Text.updateTODOList();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }

    public void updateUser(){

        boolean run = false;
        while(run) {
            run = false;
            Text.updateUser();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }
    public void deleteDataMenu(){

        boolean run = false;
        while(run) {
            run = false;
            Text.deleteDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;

                default: Text.wrongInput();
                    run = true;

            }
        }
    }
}
