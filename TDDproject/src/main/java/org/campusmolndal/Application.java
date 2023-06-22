package org.campusmolndal;

import java.util.Map;

public class Application {

    DBFacade dbFacade = new DBFacade("TODO");



    public void start(){
        mainMenu();

    }

    public void mainMenu(){
        boolean run = true;
        while(run) {
            run = false;
            Text.mainMenu();
            int input = Input.number();

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
            int input = Input.number();

            switch (input) {
                case 1:dbFacade.showALLTODO();
                    break;
                case 2:ShowSingleTODO();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5 :run = false;
                        mainMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }

    private void ShowSingleTODO(){
        int id;
        Text.whichDescripion();
        Map<Integer, Todo>  list =dbFacade.showOnlyDescription();
        if(list == null){
            noDataFound();
        }
        Text.inputValue();
        Todo todo = null;

        try{
             todo = list.get(Input.number());

        }
            catch(Exception e){
                Text.somethingWrong();
                mainMenu();
        }

        id=todo.getId();
        dbFacade.showONETODO(id);

    }

    public void addDataMenu(){
        boolean run = true;
        while(run) {
            Text.addDataMenu();
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:run = false;
                    mainMenu();
                    break;

                default: Text.wrongInput();


            }
        }
    }


    public void updateDataMenu(){

        boolean run = true;
        while(run) {
            Text.updateDataMenu();
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:run = false;
                    mainMenu();
                    break;
                default: Text.wrongInput();

            }
        }
    }
    public void updateTODOList(){

        boolean run = true;
        while(run) {
            Text.updateTODOList();
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:run = false;
                    mainMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }

    public void updateUser(){

        boolean run = true;
        while(run) {
            Text.updateUser();
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:run = false;
                    mainMenu();
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
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                    case 2:
                    break;

                    case 3:run = false;
                        mainMenu();
                    break;
                default: Text.wrongInput();
                    run = true;

            }
        }
    }


    public void noDataFound(){
        Text.noDataFound();
        mainMenu();
    }
}
