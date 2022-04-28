package com.company.Query;


//7)	Добавить нового Publisher
       // Добавить новую Titles (При передачe VALUES publisherID – нужно сделать подзапросом select*from publisher where publisherName =””)
      //  Добавить authorISBN (при передачe VALUES необходимо параметр autorID так же сделать подзапросом с указанием имени и фамилии)

import com.company.Connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.company.Query.Query2.updateTables;
import static com.company.Query.Query3.showPublishers;
import static com.company.Query.Query4.showTitles;
import static com.company.Query.Query5.showAuthors;

public class Query7 {
    public static void main(String[] argv) {
        //1 part Добавить нового Publisher
        showPublishers();//показать всех Publishers из Query3
       updateTables();//Добавить нового Publisher из Query2
        showPublishers();//показать всех Publishers из Query3

        //2 part  Добавить новую Titles (При передачe VALUES publisherID – нужно сделать подзапросом select*from publisher where publisherName =””)
        showTitles();//показать все titles из Query4
        updateTableTitles();
         showTitles();

        //part3
        showAllauthorisbn();
        showAuthors();//import static com.company.Query.Query5.showAuthors;
        updateTableAuthorisbn();
        showAllauthorisbn();
    }




    //2 part
    public static void updateTableTitles() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            int newPublisherID_;
            newPublisherID_ = publisherID_(stmt);
            insertNewTitle(stmt, newPublisherID_);


        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }


        public static  int publisherID_(Statement stmt){
            Statement stmt1 = null;
            try{
                System.out.println("Insert realPublisherName");
                Scanner scanner6 = new Scanner(System.in);
                String realPublisherName = scanner6.nextLine();
                JDBC.connect();

                stmt1 = JDBC.connection.createStatement();
                String queryPublisherID = "select  publisherId from publishers where publisherName =  ('"+realPublisherName+ "')";


                ResultSet rs1 = stmt1.executeQuery(queryPublisherID);

                while (rs1.next()) {
                    int  newPublisherID = rs1.getInt("publisherID");
                    return  newPublisherID;
                }


            } catch(SQLException se) {
                se.printStackTrace();
            } finally {
                JDBC.close();
            }
            return 0;
        }


    private static void insertNewTitle(Statement stmt, int newPublisherID_) {
        System.out.println("Insert new title ");
        System.out.println("**********************************************");

        System.out.println("Insert isbn");
        Scanner scanner1 = new Scanner(System.in);
        String newIsbn = scanner1.nextLine();

        System.out.println("Insert title");
        Scanner scanner2 = new Scanner(System.in);
        String newTitle = scanner2.nextLine();

        System.out.println("Insert editionNumber");
        Scanner scanner3 = new Scanner(System.in);
        String newEditionNumber = scanner3.nextLine();

        System.out.println("Insert year");
        Scanner scanner4 = new Scanner(System.in);
        String newYear = scanner4.nextLine();

        System.out.println("Insert price");
        Scanner scanner5 = new Scanner(System.in);
        String newPrice = scanner5.nextLine();


        String insertNewTitle = "INSERT INTO Titles (isbn, title, editionNumber, year, publisherID, price)" +
                "VALUES ('" + newIsbn + "', '" + newTitle + "', '" + newEditionNumber + "','" + newYear + "'," +
                "'" + newPublisherID_ + "', '" + newPrice + "'   )";

        try {
            stmt.executeUpdate(insertNewTitle);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }

    }
    //part3 Добавить authorISBN (при передачe VALUES необходимо параметр autorID так же сделать подзапросом с указанием имени и фамилии)
    public static void  showAllauthorisbn() {
        try {

            JDBC.connect();

            Statement stmt = JDBC.connection.createStatement();
            String queryAuthorisbn = "SELECT * FROM authorisbn";
            System.out.println("Show all authorisbn");
            System.out.println("*****************************************************");
            System.out.println("authorID" + "    " + "isbn");
            System.out.println("*****************************************************");

            ResultSet rs = stmt.executeQuery(queryAuthorisbn);
            while (rs.next()) {
                int id = rs.getInt("authorID");
                String isbn = rs.getString("isbn");
                System.out.println(id + "\t\t\t" + isbn);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
    }
    public static void updateTableAuthorisbn() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            int authorID_ = authorIsbnID(stmt);
            insertNewIsbn(stmt, authorID_);


        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }
    public static  int authorIsbnID(Statement stmt){
        Statement stmt2 = null;
        try{
            System.out.println("Insert first name");
            Scanner scanner1 = new Scanner(System.in);
            String firstName_ = scanner1.nextLine();

            System.out.println("Insert last name");
            Scanner scanner2 = new Scanner(System.in);
            String lastName_ = scanner2.nextLine();

            JDBC.connect();

            stmt2 = JDBC.connection.createStatement();
            String queryAuthorsID = "select  authorId from authors where firstName =  ('"+firstName_+ "') and " +
                    "lastName = ('"+lastName_+"')";


            ResultSet rs2 = stmt2.executeQuery(queryAuthorsID);

            while (rs2.next()) {
                int  ID = rs2.getInt("authorID");
                return  ID;
            }


        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            JDBC.close();
        }
        return 0;
    }

    private static void insertNewIsbn(Statement stmt, int authorID_) {
        System.out.println("Insert new authorISBN ");
        System.out.println("**********************************************");

        System.out.println("Insert isbn");
        Scanner scanner = new Scanner(System.in);
        String newIsbn = scanner.nextLine();


        String insertNewIsbn_ = "INSERT INTO authorisbn (authorID,isbn)" +
                "VALUES ('" + authorID_ + "', '" + newIsbn + "')";

        try {
            stmt.executeUpdate(insertNewIsbn_);
        } catch (SQLException e) {
            System.out.println("Execute Update Failed!");
            e.printStackTrace();
            return;
        }

    }
}
