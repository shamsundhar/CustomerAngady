package angady.com.customer.model.network;

/**
 * Created by shyam on 3/19/2018.
 */

public class AddressRequest {
    private String user_id;
    private String user_address_name;
    private String user_address_line_1;
    private String user_address_line_2;
    private String user_city;
    private String user_sate;
    private String usr_postal_code;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_address_name() {
        return user_address_name;
    }

    public void setUser_address_name(String user_address_name) {
        this.user_address_name = user_address_name;
    }

    public String getUser_address_line_1() {
        return user_address_line_1;
    }

    public void setUser_address_line_1(String user_address_line_1) {
        this.user_address_line_1 = user_address_line_1;
    }

    public String getUser_address_line_2() {
        return user_address_line_2;
    }

    public void setUser_address_line_2(String user_address_line_2) {
        this.user_address_line_2 = user_address_line_2;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_sate() {
        return user_sate;
    }

    public void setUser_sate(String user_sate) {
        this.user_sate = user_sate;
    }

    public String getUsr_postal_code() {
        return usr_postal_code;
    }

    public void setUsr_postal_code(String usr_postal_code) {
        this.usr_postal_code = usr_postal_code;
    }
}
