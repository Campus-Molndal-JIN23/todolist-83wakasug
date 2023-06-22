package org.campusmolndal;

import java.util.Map;

public class Application {

    DBFacade dbFacade = new DBFacade("TODO");



    public void start(){
     firstMenu();

    }

    public void firstMenu(){
        boolean run = true;
        while(run) {
            run = false;
            Text.mainMenu();
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
                case 5: run = false;
                default: Text.wrongInput();
            }
        }

    }

    public void showDataMenu(){
        boolean run = true;
        while(run) {

            Text.showDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:dbFacade.showALLTODO();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5 :run = false;
                        firstMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }

    public void ShowSingleTODO(){
        Map<Integer, Todo>  list =dbFacade.showOnlyDescription();

    }

    public void addDataMenu(){
        boolean run = true;
        while(run) {
            Text.addDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:run = false;
                    firstMenu();
                    break;

                default: Text.wrongInput();


            }
        }
    }


    public void updateDataMenu(){

        boolean run = true;
        while(run) {
            Text.updateDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:run = false;
                    firstMenu();
                    break;
                default: Text.wrongInput();

            }
        }
    }
    public void updateTODOList(){

        boolean run = true;
        while(run) {
            Text.updateTODOList();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:run = false;
                    firstMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }

    public void updateUser(){

        boolean run = true;
        while(run) {
            Text.updateUser();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:run = false;
                    firstMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }
    public void deleteDataMenu(){

        boolean run = true;
        while(run) {
            run = false;
            Text.deleteDataMenu();
            int input = Input.inputInt();

            switch (input) {
                case 1:
                    break;
                    case 2:
                    break;

                    case 3:run = false;
                    firstMenu();
                    break;
                default: Text.wrongInput();
                    run = true;

            }
        }
    }
}
