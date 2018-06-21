package angady.com.customer.model.entities;

/**
 * Created by shyam on 3/4/2018.
 */

public class Checkout {
    public static enum CHECKOUT_ITEM_TYPE{ADDRESS, PHONE, NAME};
    private String address;
    private boolean isAddressProvided;
    private String phoneNumber;
    private Boolean isPhoneNumberProvided;
    private CHECKOUT_ITEM_TYPE checkoutItemType;
    public CHECKOUT_ITEM_TYPE getCheckoutItemType() {
        return checkoutItemType;
    }
    public void setCheckoutItemType(CHECKOUT_ITEM_TYPE checkoutItemType) {
        this.checkoutItemType = checkoutItemType;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getAddressProvided() {
        return isAddressProvided;
    }

    public void setAddressProvided(boolean addressProvided) {
        isAddressProvided = addressProvided;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsPhoneNumberProvided() {
        return isPhoneNumberProvided;
    }

    public void setIsPhoneNumberProvided(Boolean isPhoneNumberProvided) {
        this.isPhoneNumberProvided = isPhoneNumberProvided;
    }


}
