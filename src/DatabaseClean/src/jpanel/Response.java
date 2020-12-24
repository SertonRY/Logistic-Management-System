package jpanel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Response {
    Response(){

    }
    public static enum ResponseState{
        Error,
        DatabaseError,
        DriverNotFound,
        IncorrectUID,
        ExistedUID,
        IncorrectPassword,
        PasswordMismatched,
        IncorrectAddress,
        Done,
    }
    //0--customer
    //1--seller
    public static ResponseState login(
            int role, String UID, String password){
        if((UID == null) || (UID.length() != 11)){
            return ResponseState.IncorrectUID;
        }
        else if((password == null) || (password.length() < 6 || password.length() > 20)){
            return ResponseState.IncorrectPassword;
        }
        else if(role == 1){
            try {
                return DatabaseOperation.matchPassword("sellers", UID, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(role == 0){
            try {
                return DatabaseOperation.matchPassword("customers", UID, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ResponseState.Error;
    }

    public static ResponseState register(
            int role, String UID, String address,
            String password, String passwordVerification){
        if((UID == null) || (UID.length() != 11)){
            return ResponseState.IncorrectUID;
        }
        else if((password == null) || (password.length() < 6 || password.length() > 20)){
            return ResponseState.IncorrectPassword;
        }
        else if(!password.equals(passwordVerification)){
            return ResponseState.PasswordMismatched;
        }
        else if((address == null) || (address.length() < 1)){
            return ResponseState.IncorrectAddress;
        }
        else if(role == 1){
            try {
                return DatabaseOperation.createUID("sellers", UID, password, address);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(role == 0){
            try {
                return DatabaseOperation.createUID("customers", UID, password, address);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ResponseState.Error;
    }

    public static ArrayList<Result> query(int role, String UID){
        try {
            return DatabaseOperation.queryDB(role, UID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Result>();
    }

    public static ResponseState newLogisticInformation(String SellerID, String CustomerID){
        return DatabaseOperation.newLogisticInfo(SellerID, CustomerID);
    }


    public static ResponseState delete(
            int role, String UID){

        if((UID == null || (UID.length() != 11)) ){
            return ResponseState.IncorrectUID;
        }
        else if(role == 1){
            try {
                return DatabaseOperation.deleteID("sellers", UID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(role == 0){
            try {
                return DatabaseOperation.deleteID("customers", UID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ResponseState.Error;
    }



    public static String queryPassword(int role, String UID){
        if(role == 1){
            return DatabaseOperation.queryDBPassword("sellers", UID);
        }
        else{
            return DatabaseOperation.queryDBPassword("customers", UID);
        }
    }

    public static String queryAddress(int role, String UID){
        if(role == 1){
            return DatabaseOperation.queryDBAddress("sellers", UID);
        }
        else{
            return DatabaseOperation.queryDBAddress("customers", UID);
        }
    }

    public static ResponseState updateInformation(
            int role, String UID, String address, String password){
        if((password == null) || (password.length() < 6 || password.length() > 20)){
            return ResponseState.IncorrectPassword;
        }
        else if((address == null) || (address.length() < 1)){
            return ResponseState.IncorrectAddress;
        }
        else if(role == 1){
            try {
                return DatabaseOperation.updateUID("sellers", UID, password, address);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(role == 0){
            try {
                return DatabaseOperation.updateUID("customers", UID, password, address);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ResponseState.Error;
    }
}
