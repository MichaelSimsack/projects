package sample;
//imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Controller {

    ArrayList<String> list = new ArrayList<>(); //creating an array list to store values in.
    String operation = ""; //holds an operation command entered by the user: +-x/
    double num1=0; //number place holder
    double hold=0; //number place holder

    //javaFX initializations
    @FXML
    private VBox vbox;

    @FXML
    private TextField txtDisplay;

    @FXML
    private GridPane gpane;

    @FXML
    private Button btnAC;

    @FXML
    private Button btnNegate;

    @FXML
    private Button btnPercent;

    @FXML
    private Button btnDivide;

    @FXML
    private Button btnSeven;

    @FXML
    private Button btnEight;

    @FXML
    private Button btnNine;

    @FXML
    private Button btnFour;

    @FXML
    private Button btnFive;

    @FXML
    private Button btnSix;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnTwo;

    @FXML
    private Button btnThree;

    @FXML
    private Button btnZero;

    @FXML
    private Button btnDecimal;

    @FXML
    private Button btnMultiply;

    @FXML
    private Button btnSubtract;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEquals;

    @FXML
    void add(ActionEvent event) { //add button
        if(num1!=0) //checks if there is a previous value typed in by user for cases with multiple operations.
            compute(); //does the correct computation using the value of num1 and current value in the array list.
        if(list.isEmpty()) //checks if the list is empty in the event that user wants to use previous computed number.
            list.add(hold+""); //if so it will add old place holder number back in as a string.
        operation = "+"; //sets the operation.
        num1=convert(); //sets num1 to what ever is currently in the list so it be used later in the computation.
        list.clear(); //clears the list so the new value (what ever the user wants to add to the previous number) can be typed in.
    }

    @FXML
    void allClear(ActionEvent event) { //this method is simple, when called it clears all values and  sets the list to null.
        list.clear(); //array list clear.
        txtDisplay.setText("0"); //sets display to 0 for the user.
        num1=0; //sets num1 place holder to 0.
        operation = ""; //clears the current operation.
        hold=0; //sets place holder value to 0.
    }

    @FXML
    void decimal(ActionEvent event) { //this method is for the decimal button.
        boolean flag=false; //boolean flag set to false.

        if(list.isEmpty()) //checks if anything is entered into the list
            list.add("0"); //if not, it will add a 0 in. This is so when a user types in a decimal first it appears as "0.###" and not ".###"

        for(String e: list) { //for each loop that will run the length of the list.
            if(e.equals(".")) //looks for a decimal within the list.
                flag=true; //if it finds one, it sets the flag to true.
        }
        if(!flag) //finally, if the flag is false at this point, there is not currently a decimal in the list.
            list.add("."); //so a decimal is added.
        print(); //prints out the current list out to the user display.
    }

    @FXML
    void divide(ActionEvent event) { //this method is exactly the same as the add method but only now it changes the operation to "/".
        if(num1!=0)
            compute();
        if(list.isEmpty())
            list.add(hold+"");
        operation = "/";
        num1=convert();
        list.clear();
    }

    @FXML
    void eight(ActionEvent event) { //8 button
        list.add("8"); //adds 8 to the list.
        print(); //prints the updated list to user display.
    }

    @FXML
    void equals(ActionEvent event) { //this method is for the equals button.
        compute(); //calls the compute function to compute values entered by the user.
        hold=convert(); //places the computed number into a place holder value.
        num1=0; //resets num1.
        list.clear(); //clears the list so the everything is reset if the user wants to type a new number in without hitting AC.
    }

    @FXML
    void five(ActionEvent event) { //five button.
        list.add("5"); //adds 5.
        print(); //updates the user display
    }

    @FXML
    void four(ActionEvent event) { //four button.
        list.add("4"); //adds 4 to list.
        print(); //updates user display.
    }

    @FXML
    void multiply(ActionEvent event) { //exactly the same as the add and divide functions but now this one sets operation to "x".
        if(num1!=0)
            compute();
        if(list.isEmpty())
            list.add(hold+"");
        operation = "x";
        num1=convert();
        list.clear();
    }

    @FXML
    void negate(ActionEvent event) { //this function is for the negate button
        if(list.isEmpty()) //checks if list is currently empty.
            list.add(hold+""); //if so, adds previous computed value to the list.
        double value=convert(); //set new variable to current value of the list.
        value = value*-1; //changes the sign of the number.
        String valueStr=isDecimal(value); //runs the value through isDecimal and converts it to a string.
        list.clear(); //clears current list.
        list.add(valueStr); //adds the new string to the list.
        print(); //updates user display.
    }

    @FXML
    void nine(ActionEvent event) { //nine button.
        list.add("9"); //adds 9 to the list
        print(); //updates the user display.
    }

    @FXML
    void one(ActionEvent event) { //one button
        list.add("1"); //adds 1 to the list.
        print(); //updates the user display.
    }

    @FXML
    void percent(ActionEvent event) { //this method is for the percent button.
        if(list.isEmpty()) //checks if list is empty.
            list.add(hold+""); //if so, adds the previous number to list.
        double value=convert(); //new variable value is set equal to current value of list.
        value=value/100; //the value is divided by 100.
        list.clear(); //clears list.
        list.add(value+""); //adds new value just computed to the list.
        print(); //updates the user display.
    }

    @FXML
    void seven(ActionEvent event) { //seven button.
        list.add("7"); //adds 7 to the list.
        print(); //updates the user display.
    }

    @FXML
    void six(ActionEvent event) { //six button.
        list.add("6"); //adds 6 to the list.
        print(); //updates user display.
    }

    @FXML
    void subtract(ActionEvent event) { //works the same way as the previous operation buttons but this one sets operation to "-".
        if(num1!=0)
            compute();
        if(list.isEmpty())
            list.add(hold+"");
        operation = "-";
        num1=convert();
        list.clear();
    }

    @FXML
    void three(ActionEvent event) { //three button.
        list.add("3"); //adds 3 to the list.
        print(); //updates the user display.
    }

    @FXML
    void two(ActionEvent event) { //two button.
        list.add("2"); //adds 2 to the list.
        print(); //updates the user display.
    }

    @FXML
    void zero(ActionEvent event) { //zero button.
        list.add("0"); //adds 0 to the list.
        print(); //updates the user display.
    }

    public void initialize(){ //this method just adds in a small easter egg. this is not part of the assignment and can be ignored.
       txtDisplay.textProperty().addListener(((observableValue, s, t1) -> {
           if(txtDisplay.getText().equals("2514"))
               txtDisplay.setText("JAY YUTE");
       }));
    }

    public void print(){ //this method will print the current list when called.
        txtDisplay.setText(""); //resets the text on user display.
        for(String e: list){ //for each loop that runs length of the list.
            txtDisplay.setText(txtDisplay.getText()+e); //this line will keep appending every element of the list to the user display.
        }
    }

    public String isDecimal(double value){ //this method is used to basically remove the decimal for integers computed.
        String valueStr=""; //new string
        if(value%1==0){ //if value you passed in is not a decimal,
            int valueInt= (int) value;//value gets casted to an int to remove decimal.
            valueStr = valueInt + ""; //the int variable gets converted to a string.
        }
        else {
            valueStr = value + ""; //if it is a decimal, it is left alone and converted to a string.
        }
        return valueStr; //returns the string.
    }

    public double convert(){ //will convert the string value of the current list to a double.
        String value = ""; //new string
        for(String e: list){ //for each loop the runs the length of the list.
            value += e; //every element gets appended into the string value.
        }
        double convertedNum = Double.parseDouble(value); //converts the string to a double.
        return convertedNum; //returns the converted value.
    }

    public void compute(){ //this function will make the correct computations based on current data.
        double num2=convert(); //puts the current list value into num2.
        double temp=0; //sets temp to 0.
        String valueStr=""; //new string
        list.clear(); //clears the current list.

        switch(operation){ //I put in a switch case for the operation variable because... i quite like switch cases, they look clean. although if statements would have worked too.
            case "+": //if operation == "+".
                temp=Math.round((num1+num2)*100.0)/100.0; //does the operation and limits out put to two decimal places.
                valueStr=isDecimal(temp); //checks if the computed number is a decimal or not.
                list.add(valueStr); //adds the computed value to the list.
                print(); //updates user display.
                break; //breaks out of the switch.

            case "-": //the rest of the cases are identical to the first case except the operation is changed to reflect the case.
                temp=Math.round((num1-num2)*100.0)/100.0; //now subtracts
                valueStr=isDecimal(temp);
                list.add(valueStr);
                print();
                break;

            case "x": //see first case
                temp=Math.round((num1*num2)*100.0)/100.0; //now multiples
                valueStr=isDecimal(temp);
                list.add(valueStr);
                print();
                break;

            case "/": //see first case
                temp=Math.round((num1/num2)*100)/100.0; // now divides
                valueStr=isDecimal(temp);
                list.add(valueStr);
                print();
                break;
        }
    }
}
