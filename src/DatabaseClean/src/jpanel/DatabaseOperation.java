package jpanel;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperation {
    //we delete the private data below, you need to change it before using.
    static String DatabaseURL = "jdbc:mysql://IP_ADDRESS_OR_DOMAIN_NAME:PORT/DATABASE_NAME?serverTimezone=Asia/Shanghai";
    DatabaseOperation() {

    }

    static public Response.ResponseState matchPassword(String table, String UID, String password) throws SQLException{
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "select Password from " + table + " where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
            databaseStatement.setString(1, UID);
            databaseResult = databaseStatement.executeQuery();
            if(databaseResult.next()){
                if(databaseResult.getString(1).equals(password)){
                    processState = Response.ResponseState.Done;
                }
                else{
                    processState = Response.ResponseState.IncorrectPassword;
                }
            }
            else{
                processState = Response.ResponseState.IncorrectUID;
            }
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }

    static public Response.ResponseState createUID(String table, String UID, String password, String address) throws SQLException{
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "select UID from " + table + " where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
            databaseStatement.setString(1, UID);
            databaseResult = databaseStatement.executeQuery();
            if(databaseResult.next()){
                processState = Response.ResponseState.ExistedUID;
            }
            else{
                SQL = "insert into " + table + " (`UID`, `PassWord`, `Address`) values (?, ?, ?)";
                databaseStatement = databaseConnection.prepareStatement(SQL,
                        databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_UPDATABLE);
                databaseStatement.setString(1, UID);
                databaseStatement.setString(2, password);
                databaseStatement.setString(3, address);
                databaseStatement.executeUpdate();
                processState = Response.ResponseState.Done;
            }
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }

    static public ArrayList<Result> queryDB(int role, String UID) throws SQLException {
        ArrayList<Result> queryResult = new ArrayList<Result>();
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        if(role == 1){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseConnection = DriverManager.getConnection(DatabaseURL,
                        "USER_NAME", "PASSWORD");
                String SQL = "select ID, CustomerID, Address from packages, customers where packages.CustomerID = customers.UID AND SellerID = ?";
                databaseStatement = databaseConnection.prepareStatement(SQL,
                        databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
                databaseStatement.setString(1, UID);
                databaseResult = databaseStatement.executeQuery();
                while(databaseResult.next()){
                    queryResult.add(new Result(databaseResult.getString(1),
                            databaseResult.getString(2),
                            databaseResult.getString(3)));
                }
            }
            catch (SQLException e) {

            }
            catch (ClassNotFoundException e) {

            }
            finally {
                try {
                    assert databaseResult != null;
                    databaseResult.close();
                    assert databaseStatement != null;
                    databaseStatement.close();
                    assert databaseConnection != null;
                    databaseConnection.close();
                }
                catch(Exception e){

                }
            }
        }
        else{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseConnection = DriverManager.getConnection(DatabaseURL,
                        "USER_NAME", "PASSWORD");
                String SQL = "select ID, SellerID, Address from packages, sellers where packages.SellerID = sellers.UID AND CustomerID = ?";
                databaseStatement = databaseConnection.prepareStatement(SQL,
                        databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
                databaseStatement.setString(1, UID);
                databaseResult = databaseStatement.executeQuery();
                while(databaseResult.next()){
                    queryResult.add(new Result(databaseResult.getString(1),
                            databaseResult.getString(2),
                            databaseResult.getString(3)));
                }
            }
            catch (SQLException e) {

            }
            catch (ClassNotFoundException e) {

            }
            finally {
                try {
                    assert databaseResult != null;
                    databaseResult.close();
                    assert databaseStatement != null;
                    databaseStatement.close();
                    assert databaseConnection != null;
                    databaseConnection.close();
                }
                catch(Exception e){

                }
            }
        }
        return queryResult;
    }

    static public Response.ResponseState newLogisticInfo(String SellerID, String CustomerID){
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "select UID from customers where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
            databaseStatement.setString(1, CustomerID);
            databaseResult = databaseStatement.executeQuery();
            if(!databaseResult.next()){
                processState = Response.ResponseState.IncorrectUID;
            }
            else{
                SQL = "insert into packages (`ID`, `SellerID`, `CustomerID`) values (?, ?, ?)";
                databaseStatement = databaseConnection.prepareStatement(SQL,
                        databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_UPDATABLE);
                databaseStatement.setString(1, null);
                databaseStatement.setString(2, SellerID);
                databaseStatement.setString(3, CustomerID);
                databaseStatement.executeUpdate();
                processState = Response.ResponseState.Done;
            }
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }

    static public Response.ResponseState deleteID(String table, String UID) throws SQLException{
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "delete from " + table + " where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_UPDATABLE);
            databaseStatement.setString(1, UID);
            databaseStatement.executeUpdate();

            if ("sellers".equals(table)) {
                return deletePack(UID,"sellers");
            } else {
                return deletePack(UID,"customers");
            }
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }
    static private Response.ResponseState deletePack(String UID, String type) throws SQLException{
        Connection databaseConnection2 = null;
        PreparedStatement databaseStatement2 = null;
        ResultSet databaseResult2 = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection2 = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL2;
            if ("sellers".equals(type)) {
                SQL2 = "delete from packages where SellerID = ?";
            } else {
                SQL2 = "delete from packages where CustomerID = ?";
            }
            databaseStatement2 = databaseConnection2.prepareStatement(SQL2,
                    databaseResult2.TYPE_SCROLL_INSENSITIVE, databaseResult2.CONCUR_UPDATABLE);
            databaseStatement2.setString(1, UID);
            databaseStatement2.executeUpdate();
            processState = Response.ResponseState.Done;
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult2 != null;
                databaseResult2.close();
                assert databaseStatement2 != null;
                databaseStatement2.close();
                assert databaseConnection2 != null;
                databaseConnection2.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }

    static public String queryDBPassword(String table, String UID){
        String Password = null;
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "select PassWord from " + table + " where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
            databaseStatement.setString(1, UID);
            databaseResult = databaseStatement.executeQuery();
            if(databaseResult.next()){
                Password = databaseResult.getString(1);
            }
        }
        catch (SQLException e) {

        }
        catch (ClassNotFoundException e) {

        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return Password;
    }

    static public String queryDBAddress(String table, String UID){
        String Address = null;
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "select Address from " + table + " where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_READ_ONLY);
            databaseStatement.setString(1, UID);
            databaseResult = databaseStatement.executeQuery();
            if(databaseResult.next()){
                Address = databaseResult.getString(1);
            }
        }
        catch (SQLException e) {

        }
        catch (ClassNotFoundException e) {

        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return Address;
    }

    static public Response.ResponseState updateUID(String table, String UID, String password, String address) throws SQLException{
        Connection databaseConnection = null;
        PreparedStatement databaseStatement = null;
        ResultSet databaseResult = null;
        Response.ResponseState processState = Response.ResponseState.Error;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DatabaseURL,
                    "USER_NAME", "PASSWORD");
            String SQL = "update " + table + " set PassWord = ?, Address = ? where UID = ?";
            databaseStatement = databaseConnection.prepareStatement(SQL,
                    databaseResult.TYPE_SCROLL_INSENSITIVE, databaseResult.CONCUR_UPDATABLE);
            databaseStatement.setString(1, password);
            databaseStatement.setString(2, address);
            databaseStatement.setString(3, UID);
            databaseStatement.executeUpdate();
            processState = Response.ResponseState.Done;
        }
        catch (SQLException e) {
            processState = Response.ResponseState.DatabaseError;
        }
        catch (ClassNotFoundException e) {
            processState = Response.ResponseState.DriverNotFound;
        }
        finally {
            try {
                assert databaseResult != null;
                databaseResult.close();
                assert databaseStatement != null;
                databaseStatement.close();
                assert databaseConnection != null;
                databaseConnection.close();
            }
            catch(Exception e){

            }
        }
        return processState;
    }
}
