package jpanel;

public class Result {
    Result(){

    }
    Result(String ID, String UID, String address){
        this.ID = ID;
        this.UID = UID;
        this.Address = address;
    }
    private String ID;
    private String UID;
    private String Address;

    public String getID() {
        return ID;
    }

    public String getUID() {
        return UID;
    }

    public String getAddress() {
        return Address;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setAddress(String address) {
        this.Address = address;
    }


}
