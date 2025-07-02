package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
/*

addOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, String pizzaType, int quantity)
updateOrder(ArrayList<Integer> quantities, int index, int newQuantity)
removeOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, int index)
printOrders(ArrayList<String> pizzas, ArrayList<Integer> quantities)

 */
public class Main {
    static void addOrder(ArrayList<String> pizzas, ArrayList<Integer> quantArray, String pizzaType, int quantity){
        if (zeroCheck(quantity)){
            if(pizzas.contains(pizzaType)){//pizza already in memory
                int existingPizzaIndex = pizzas.indexOf(pizzaType);
                quantArray.set(existingPizzaIndex, quantity);
                System.out.println("Pizza entry already exists, updating quantity instead");
            }else{
                pizzas.add(pizzaType);
                quantArray.add(quantity);
            }
        }
        else{
            System.out.println("Quantity must be positive");
        }
    }

    static void updateOrder(ArrayList<Integer> quantities, int index, int newQuantity){
       try{
           if (zeroCheck(newQuantity)){
               quantities.set(index,newQuantity);
           }else{
               System.out.println("Quantity must be positive");
           }
       } catch (IndexOutOfBoundsException e) {
           System.out.println("Index invalid");
       }
    }
    static void removeOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, int index){
        try{
            pizzas.remove(index);
            quantities.remove(index);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index invalid");
        }

    }
    static String printOrders(ArrayList<String> pizzas, ArrayList<Integer> quantities){
        String output = "--- Current Orders ---\n";
        for (int i = 0;  i <= (pizzas.size()-1); i++) {
            output += String.format("%d. %S x %d%n",(i+1), pizzas.get(i), quantities.get(i));
        }
        return output;
    }

    static void printMenu(){
        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Remove Order");
        System.out.println("4. View Order");
        System.out.println("5. Exit");
    }

    static boolean zeroCheck(int orderQuant){
        return (orderQuant > 0);
    }
    public static void main(String[] args) {
    ArrayList<String> pizzaOrderList = new ArrayList<>();
    ArrayList<Integer> quantityList = new ArrayList<>();
    Scanner userIn = new Scanner(System.in);
    boolean exitCondition = false;

    printMenu();

    while (!exitCondition){
        System.out.print("Choose option: ");
        int userChoice = 0;
        try{
            userChoice = userIn.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Invalid input, please enter a number between 1-to-5 \n Error:"+e);
        }
        userIn.nextLine();

        if(userChoice==5){
            exitCondition = true;
        } else if (userChoice==1) {//Add
            System.out.print("Pizza type: ");
            String pizzaEntry = userIn.nextLine();
            System.out.print("Quantity: ");
            int quant = userIn.nextInt();
            userIn.nextLine();
            addOrder(pizzaOrderList,quantityList,pizzaEntry,quant);
        } else if (userChoice==2) {//Update
            System.out.println("-------Printing Orders for Convenience----------\n"+printOrders(pizzaOrderList,quantityList)+"\n-----------");
            System.out.print("Order number to update: ");
            int orderNum = userIn.nextInt();
            userIn.nextLine();
            System.out.print("New quantity: ");
            int quant = userIn.nextInt();
            userIn.nextLine();
            updateOrder(quantityList, orderNum-1, quant);
        } else if (userChoice==3) {
            System.out.println("-------Printing Orders for Convenience----------\n"+printOrders(pizzaOrderList,quantityList)+"\n-----------");
            printOrders(pizzaOrderList,quantityList);
            System.out.print("Order number to remove: ");
            int orderIndex = userIn.nextInt();
            userIn.nextLine();
            removeOrder(pizzaOrderList, quantityList, orderIndex-1);
        } else if (userChoice==4) {
            System.out.println(printOrders(pizzaOrderList,quantityList));
        } else{
            System.out.println("Possible wrong type or number out of range\n");
        }
        printMenu();
        System.out.println(printOrders(pizzaOrderList,quantityList));
    }

    }
}