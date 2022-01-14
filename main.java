package com.example.trial1;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.text.Format;
import java.util.Scanner;

public class main extends Application {
    public ObservableList<Object> data;
    private TableView table = new TableView();

    public static void main(String[] args) {
        launch(args);
    }

    Connection conn ;
    @Override
    public void start(Stage Stage) throws ClassNotFoundException {
        data = FXCollections.observableArrayList();
           try {
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");
               if (conn != null) {
                   System.out.println("concted");
               }
           }
              catch (Exception e) {
                   System.out.println(e);

               }

               //DBC conobj = new DBC();
               // Connection conn = conobj.connMethod();
               // Connection c = conobj.connMethod();
               GridPane root = new GridPane();
               Button insert = new Button();
               Button display = new Button("display ");
               insert.setText("  insert ");
             insert.setLayoutX(15);
              display.setLineSpacing(40);
              Button specifcdisplay  = new Button("specifc display");
           specifcdisplay.setLayoutX(20);
            Button distnict =new Button("  distnict ");
        distnict.setLayoutX(15);
        Button update = new Button(" update");
        update.setLayoutX(15);
        TextField txt = new TextField();
               TextField txt1 = new TextField();
               TextField txt2 = new TextField();
               TextField txt3 = new TextField();
               TextField txt4 = new TextField();
               TextField txt5 = new TextField();

               Label lb = new Label("SID :");
               Label slb = new Label("STUDID :");
               Label flb = new Label("FIRST NAME :");
               Label llb = new Label("LAST NAME :");
               Label selb = new Label("SECTION :");
               Label delb = new Label("DEPARTMENT :");

               final Label label = new Label("Address Book");
               label.setFont(new Font("Arial", 10));
       table.setEditable(true);
      /*  TableColumn sidcol = new TableColumn("sid");
        TableColumn studidcol = new TableColumn("studid");
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn secCol = new TableColumn("section");
        TableColumn deptcol = new TableColumn(" department");
*/

       // table.getColumns().addAll( sidcol,studidcol,firstNameCol, lastNameCol, secCol,deptcol);
               root.add(lb,0,0);
               root.add(txt,1,0);
               root.addRow(1, slb, txt1);
               root.addRow(2, flb, txt2);
               root.addRow(3, llb, txt3);
               root.addRow(4, selb, txt4);
               root.addRow(5, delb, txt5);
               root.add( insert,1,6);
               root.addRow(18,label);
               root.add(table,50,50);
               root.add( display,16,6);
              root.add(distnict,3,6);
              root.add(specifcdisplay,4,6);
              root.add(update,7,6);

               Scene scene = new Scene(root, 800, 800);
                scene.setFill(Color.BLUE);

               Stage.setWidth(900);
               Stage.setHeight(900);
       insert.setStyle("-fx-background-color:Blue; -fx-textfill:white;");
        display.setStyle("-fx-background-color:yellow; -fx-textfill:white;");
        distnict.setStyle("-fx-background-color:red; -fx-textfill:white;");
        update.setStyle("-fx-background-color:orange; -fx-textfill:white;");
        specifcdisplay.setStyle("-fx-background-color:orange; -fx-textfill:white;");
        insert.setStyle("-fx-background-color:red; -fx-textfill:white;");
               Stage.setTitle("javafx  World!");
               Stage.setScene(scene);
               Stage.setScene(scene);
               insert.setOnAction(new EventHandler<ActionEvent>() {

                   @Override
                   public void handle(ActionEvent event) {
                       String tf1 = txt.getText();
                       String tf2 = txt1.getText();
                       String tf3 = txt2.getText();
                       String tf4 = txt3.getText();
                       String tf5 = txt4.getText();
                       String tf6 = txt5.getText();
                       int st = Integer.parseInt(tf1);
                       try {
                           Class.forName("com.mysql.jdbc.Driver");
                           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");

                           Statement stm = conn.createStatement();

                           // String sql = "INSERT INTO `dept_tb1`(`sid`, `studid`, `fristname`, `lastname`, `section`, `dept`) VALUES   VALUES(" + tf1 + ",'" + tf2 + "','" + tf3 + "','" + tf4 + "','" + tf5 + "','" + tf6 + "',)";
                           String stat = ("insert into dept_tb1 (sid,studid,fristname,lastname,section,dept) values(" + tf1 + ",'" + tf2 + "','" + tf3 + "','" + tf4 + "','" + tf5 + "','" + tf6 + "')");
                           stm.executeUpdate(stat);
                           System.out.println("secuceful insert ");


                       } catch (Exception e) {
                           System.out.println(e);

                       }
                   }


               });

               display.setOnAction(new EventHandler<ActionEvent>() {


                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           Class.forName("com.mysql.jdbc.Driver");
                           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");
                           // Statement stm = conn.createStatement();
                           ResultSet rs;
                           data = FXCollections.observableArrayList();
                           table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");

                           String SQL = "SELECT * from dept_tb1";
                           rs = conn.createStatement().executeQuery(SQL);
                           for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                               final int j = i;
                               TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                               col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                                       ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                               table.getColumns().addAll(col);
                               System.out.println("Column [" + i + "] ");

                           }


                           while (rs.next()) {
                               ObservableList<String> row = FXCollections.observableArrayList();
                               for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                   row.add(rs.getString(i));
                               }
                               System.out.println("Row[1]added " + row);
                               data.add(row);

                           }


                           table.setItems(data);
                       } catch (Exception e) {
                           e.printStackTrace();
                           System.out.println("Error ");
                       }




                   }});

        specifcdisplay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");

                    Statement stm = conn.createStatement();
                    String sql2 = "SELECT  dept  FROM dept_tb1 WHERE fristname='ff'AND section='ff'AND dept='ff'";
                    ResultSet rs2 = stm.executeQuery(sql2);
                    System.out.println("condition query");
                    ObservableList<String> obj = FXCollections.observableArrayList();
                    while (rs2.next()) {
                        for (int j = 1; j<rs2.getMetaData().getColumnCount(); j++) {
                            obj.add(rs2.getString(j));

                            // System.out.println(rs2.getString(j));
                        }
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText(String.valueOf((obj)));
                        a.showAndWait();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        distnict.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");

                    Statement stm = conn.createStatement();
                    String sql2 = "SELECT DISTINCT(section),`sid`, `studid`, `fristname`, `lastname`,  `dept` FROM `dept_tb1` "
                            ;
                    ResultSet rs2 = stm.executeQuery(sql2);
                    System.out.println("distct query");
                    ObservableList<String> obj = FXCollections.observableArrayList();
                    while (rs2.next()) {
                        for (int j = 1; j<rs2.getMetaData().getColumnCount(); j++) {
                            obj .add( rs2.getString(j)+" ");
                        System.out.println(rs2.getString(j));
                            // System.out.println(rs2.getString(j));
                        }}
                       Alert a = new Alert(Alert.AlertType.INFORMATION);
                       a.setContentText(String.valueOf(obj)+"\n");
                      a.showAndWait();

                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

update.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event)
    {
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        try
        { Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cci1", "root", "");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter an updated name:fom db ");
            String vall = input.nextLine();
            System.out.print("Enter an new  name: ");
            String vall1 = input.nextLine();
            // closing the scanner object
            input.close();
            String sql = "UPDATE dept_tb1 SET fristname='"+vall1+"' WHERE fristname='"+vall+"'";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            A.setContentText("Updated successfuly");
            A.showAndWait();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
});

        Stage.show();}}

