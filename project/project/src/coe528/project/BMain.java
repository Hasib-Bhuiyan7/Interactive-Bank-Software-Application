/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package coe528.project;
//package JavaFX 19;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 *
 * @author hasib
 */
public class BMain extends Application{
    protected Stage app;
    protected Scene login, managerScene, customerScene, addcustomerScene, deletecustomerScene, depositScene, withdrawScene, purchaseScene, checkBalanceScene;
    Manager manager = new Manager();//default Manager
    Customer cust = new Customer("Kelvin", "h", 100);//Default Customer
    Label checkbalanceLabel = new Label(cust.toString());
    
    @Override
    public void start(Stage stage) {
        app = stage;
        app.setResizable(false);
        
        //Login - Scene #1
        Text loginHeader = new Text("Bank Simulation Application");
        loginHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label user = new Label("Please enter the Username:");
        TextField userText = new TextField();
        Label pswd = new Label("Please enter the Password:");
        PasswordField pswdField = new PasswordField();
        Button enter = new Button("Log in");
        FlowPane pane1 = new FlowPane();
        pane1.setPadding(new Insets(11, 12, 13, 14));
        pane1.setHgap(4);
        pane1.setVgap(6);
        pane1.getChildren().addAll(loginHeader, user, userText, pswd, pswdField,enter);
        login = new Scene(pane1, 300, 300);
        //"Enter" button action
        enter.setOnAction(e ->{
            if(manager.login(userText.getText(), pswdField.getText())){
                app.setScene(managerScene);//move to next scene by the role of a manager
                userText.clear();
                pswdField.clear();
            }
            else if(cust.login(userText.getText(), pswdField.getText())){
                app.setScene(customerScene);//move to next scene by the role of a customer
                userText.clear();
                pswdField.clear();
            }
        });
        
        //Manager Scene - log in success as Manager - Scene #2
        Text managerHeader = new Text("Welcome Manager");
        managerHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Button addCustomer = new Button("Add New Customer");
        Button deleteCustomer = new Button("Delete Customer");
        Button log_out = new Button("Logout");
        FlowPane pane2 = new FlowPane();
        pane2.setPadding(new Insets(15, 12, 15, 12));
        pane2.setHgap(4);
        pane2.setVgap(6);
        pane2.getChildren().addAll(managerHeader, addCustomer, deleteCustomer, log_out);
        managerScene = new Scene(pane2, 300, 300);
        //"AddCustomer" button action
        addCustomer.setOnAction(e->{
            app.setScene(addcustomerScene);
        });
        //"deleteCustomer" button action
        deleteCustomer.setOnAction(e->{
            app.setScene(deletecustomerScene);
        });
        //"logout" button action
        log_out.setOnAction(e->{
            manager.logout();
            app.setScene(login);
        });
        
        
        //Customer Scene - log in as customer successful - Scene #3
        Text customerHeader = new Text("Welcome Customer, " + cust.getUsername());
        customerHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Button depositBtn = new Button("Make A Deposit");
        Button withdrawBtn = new Button("Withdraw An Amount");
        Button purchaseBtn = new Button("Make A Purchase");
        Button getBalanceBtn = new Button("Check Balance");
        Button logoutBtn = new Button("Logout");
        FlowPane pane3 = new FlowPane();
        pane3.setPadding(new Insets(15, 12, 15, 12));
        pane3.setHgap(4);
        pane3.setVgap(6);
        pane3.getChildren().addAll(customerHeader, depositBtn, withdrawBtn, purchaseBtn, getBalanceBtn, logoutBtn);
        customerScene = new Scene(pane3, 300, 300);
        //"depositBtn" button action
        depositBtn.setOnAction(e->{
            app.setScene(depositScene);
        });
        //"withdrawBtn" button action
        withdrawBtn.setOnAction(e->{
            app.setScene(withdrawScene);
        });
        //"purchaseBtn" button action
        purchaseBtn.setOnAction(e->{
            app.setScene(purchaseScene);
        });
        //"getBalanceBtn" button action
        getBalanceBtn.setOnAction(e->{
            checkbalanceLabel.setText(cust.toString());
            app.setScene(checkBalanceScene);
        });
        //"logoutBtn" button action
        logoutBtn.setOnAction(e->{
            cust.logout();
            app.setScene(login);
        });
        
        //Add Customer Scene - Manager attempting to Add another customer - Scene #4
        Text addCustomerHeader = new Text("--Adding New Customer--");
        addCustomerHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label addUser = new Label("Please enter the Username for the new Customer:");
        TextField newUserText = new TextField();
        Label addPswd = new Label("Please enter the new Customer's Password:");
        PasswordField newPswd = new PasswordField();
        Button addCustomerBtn = new Button("Enter New Customer Info");
        Button backtoManagerBtn = new Button("Return back to manager page");
        FlowPane pane4 = new FlowPane();
        pane4.setPadding(new Insets(15, 12, 15, 12));
        pane4.setHgap(4);
        pane4.setVgap(6);
        pane4.getChildren().addAll(addCustomerHeader, addUser,newUserText, addPswd,newPswd, addCustomerBtn, backtoManagerBtn);
        addcustomerScene = new Scene(pane4, 300, 300);
        //"addCustomerBtn" button action
        addCustomerBtn.setOnAction(e->{
            manager.addCustomer(newUserText.getText(), newPswd.getText());
            app.setScene(managerScene);
            newUserText.clear();
            newPswd.clear();
        });
        //"backtoManagerBtn" button action
        backtoManagerBtn.setOnAction(e->{
            app.setScene(managerScene);
            newUserText.clear();
            newPswd.clear();
        });
        

        //Delete Customer Scene - Manager attempting to delete  customer - Scene #5
        Text deleteCustomerHeader = new Text("--Deleting Existing Customer--");
        deleteCustomerHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label deleteUser = new Label("Please enter the Username for the Customer Account that will be removed:");
        TextField deleteUserText = new TextField();
        Button deleteCustomerBtn = new Button("Enter Customer Info");
        Button backtoManagerBtn2 = new Button("Return back to manager page");
        FlowPane pane5 = new FlowPane();
        pane5.setPadding(new Insets(15, 12, 15, 12));
        pane5.setHgap(4);
        pane5.setVgap(6);
        pane5.getChildren().addAll(deleteCustomerHeader, deleteUser,deleteUserText, deleteCustomerBtn, backtoManagerBtn2);
        deletecustomerScene = new Scene(pane5, 300, 300);
        //"addCustomerBtn" button action
        deleteCustomerBtn.setOnAction(e->{
            manager.deleteCustomer(deleteUserText.getText());
            app.setScene(managerScene);
            deleteUserText.clear();
        });
        //"backtoManagerBtn" button action
        backtoManagerBtn2.setOnAction(e->{
            app.setScene(managerScene);
            deleteUserText.clear();
        });
        
        //Deposit scene - Customer depositing money amount into account - Scene #6
        Text depositHeader = new Text("--Depositing Into Account--");
        depositHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label depositAmount = new Label("Please enter the Amount you want to deposit into account: ");
        TextField amount = new TextField();
        Button depositActionBtn = new Button("Enter");
        Button backtoCustomerBtn = new Button("Return back to customer page");
        FlowPane pane6 = new FlowPane();
        pane6.setPadding(new Insets(15, 12, 15, 12));
        pane6.setHgap(4);
        pane6.setVgap(6);
        pane6.getChildren().addAll(depositHeader, depositAmount,amount, depositActionBtn, backtoCustomerBtn);
        depositScene = new Scene(pane6, 300, 300);
        //"deposit" button action
        depositActionBtn.setOnAction(e->{
            String num = amount.getText();
            try{
                double val = Double.parseDouble(num);
                cust.deposit(val);
                amount.clear();
                app.setScene(customerScene);
            }catch (NumberFormatException ex){
                System.out.println("ERROR: Invalid format for the deposit amount input, Please try again");
                amount.clear();
                app.setScene(depositScene);
            }
        });
        //"backtoCustomerBtn" button action
        backtoCustomerBtn.setOnAction(e->{
            app.setScene(customerScene);
            amount.clear();
        });
        
        //Withdraw scene - Customer withdrawing money amount from account - Scene #7
        Text withdrawHeader = new Text("--Wthdrawing from Account--");
        withdrawHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label withdrawAmount = new Label("Please enter the Amount you want to withdraw from account: ");
        TextField amount2 = new TextField();
        Button withdrawActionBtn = new Button("Enter");
        Button backtoCustomerBtn2 = new Button("Return back to custumer page");
        FlowPane pane7 = new FlowPane();
        pane7.setPadding(new Insets(15, 12, 15, 12));
        pane7.setHgap(4);
        pane7.setVgap(6);
        pane7.getChildren().addAll(withdrawHeader, withdrawAmount,amount2, withdrawActionBtn, backtoCustomerBtn2);
        withdrawScene = new Scene(pane7, 300, 300);
        //"withdraw" button action
        withdrawActionBtn.setOnAction(e->{
            String withdrawNum = amount2.getText();
            try{
                double withdrawVal = Double.parseDouble(withdrawNum);
                cust.withdraw(withdrawVal);
                amount2.clear();
                app.setScene(customerScene);
            }catch (NumberFormatException ex){
                System.out.println("ERROR: Invalid format for the withdraw amount input, Please try again");
                amount2.clear();
                app.setScene(withdrawScene);
            }
        });
        //"backtoCustomerBtn2" button action
        backtoCustomerBtn2.setOnAction(e->{
            app.setScene(customerScene);
            amount.clear();
        });
        
        //Purchase scene - Customer purchasing item - Scene #8
        Text purchaseHeader = new Text("--Purchasing products--");
        purchaseHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        Label purchaseProduct = new Label("Please enter the name of the product you want to purchase: ");
        TextField product = new TextField();
        Label purchaseSeller = new Label("Please enter the name of the producer/seller of your product: ");
        TextField seller = new TextField();
        Label purchaseAmount = new Label("Please enter the price of the product you want to purchase: ");
        TextField amount3 = new TextField();
        Button purchaseActionBtn = new Button("Enter");
        Button backtoCustomerBtn3 = new Button("Return back to customer page");
        FlowPane pane8 = new FlowPane();
        pane8.setPadding(new Insets(15, 12, 15, 12));
        pane8.setHgap(4);
        pane8.setVgap(6);
        pane8.getChildren().addAll(purchaseHeader, purchaseProduct,product,purchaseSeller, seller, purchaseAmount ,amount3 ,purchaseActionBtn, backtoCustomerBtn3);
        purchaseScene = new Scene(pane8, 300, 300);
        //"purchase" button action
        purchaseActionBtn.setOnAction(e->{
            String num = amount3.getText();
            double productVal;
            String prod = product.getText();
            String producer = seller.getText();
            if(prod.isEmpty() || producer.isEmpty() ){
                System.out.println("The input is empty, please try again:");
                app.setScene(purchaseScene);
            }
            else{
                try {
                    productVal = Double.parseDouble(num);
                    cust.purchase(product.getText(), seller.getText(), productVal);
                    product.clear();
                    seller.clear();
                    amount3.clear();
                    app.setScene(customerScene);
                } catch (NumberFormatException ex) {
                    System.out.println("ERROR: Invalid format for the price amount input, Please try again");
                    product.clear();
                    seller.clear();
                    amount3.clear();
                    app.setScene(purchaseScene);
                }
            }
        });
        //"backtoCustomerBtn3" button action
        backtoCustomerBtn3.setOnAction(e->{
            app.setScene(customerScene);
            product.clear();
            seller.clear();
            amount3.clear();
        });
        
        
        //Check Balance scene - Customer checking account baalnce - Scene #9
        Text checkHeader = new Text("--Check your Balance--");
        checkHeader.setFont(Font.font("Montserrat", FontWeight.SEMI_BOLD, 30));
        checkbalanceLabel.setText(cust.toString());
        Button backtoCustomerBtn4 = new Button("Return back to customer page");
        FlowPane pane9 = new FlowPane();
        pane9.setPadding(new Insets(15, 12, 15, 12));
        pane9.setHgap(4);
        pane9.setVgap(6);
        pane9.getChildren().addAll(checkHeader, checkbalanceLabel,backtoCustomerBtn4);
        checkBalanceScene = new Scene(pane9, 300, 300);
        //"backtoCustomerBtn4" button action
        backtoCustomerBtn4.setOnAction(e->{
            app.setScene(customerScene);
            checkbalanceLabel.setText(cust.toString());
        });
        
        app.setScene(login);
        app.setTitle("Bank Page");
        app.show();
 
    }
    
    //main method to launch javafx arguments
    public static void main(String[] args){
        launch(args);
    }
}
