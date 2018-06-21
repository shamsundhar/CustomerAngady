package angady.com.customer.model.entities;

import java.util.List;

/**
 * Created by shyam on 6/8/2018.
 */

public class Order {
    private String OrderShipName;

    private String OrderState;

    private String OrderAmount;

    private String OrderTax;

    private String OrderTrackingNumber;

    private String OrderCity;

    private String OrderPhone;

    private String OrderShipAddress2;

    private String OrderEmail;

    private String OrderZip;

    private String OrderID;

    private String OrderDate;

    private String user_id;

    private String OrderShipAddress;

    private String OrderCountry;

    private List<Product> productList;

    public String getOrderShipName ()
    {
        return OrderShipName;
    }

    public void setOrderShipName (String OrderShipName)
    {
        this.OrderShipName = OrderShipName;
    }

    public String getOrderState ()
    {
        return OrderState;
    }

    public void setOrderState (String OrderState)
    {
        this.OrderState = OrderState;
    }

    public String getOrderAmount ()
    {
        return OrderAmount;
    }

    public void setOrderAmount (String OrderAmount)
    {
        this.OrderAmount = OrderAmount;
    }

    public String getOrderTax ()
    {
        return OrderTax;
    }

    public void setOrderTax (String OrderTax)
    {
        this.OrderTax = OrderTax;
    }

    public String getOrderTrackingNumber ()
    {
        return OrderTrackingNumber;
    }

    public void setOrderTrackingNumber (String OrderTrackingNumber)
    {
        this.OrderTrackingNumber = OrderTrackingNumber;
    }

    public String getOrderCity ()
    {
        return OrderCity;
    }

    public void setOrderCity (String OrderCity)
    {
        this.OrderCity = OrderCity;
    }

    public String getOrderPhone ()
    {
        return OrderPhone;
    }

    public void setOrderPhone (String OrderPhone)
    {
        this.OrderPhone = OrderPhone;
    }

    public String getOrderShipAddress2 ()
    {
        return OrderShipAddress2;
    }

    public void setOrderShipAddress2 (String OrderShipAddress2)
    {
        this.OrderShipAddress2 = OrderShipAddress2;
    }

    public String getOrderEmail ()
    {
        return OrderEmail;
    }

    public void setOrderEmail (String OrderEmail)
    {
        this.OrderEmail = OrderEmail;
    }

    public String getOrderZip ()
    {
        return OrderZip;
    }

    public void setOrderZip (String OrderZip)
    {
        this.OrderZip = OrderZip;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getOrderShipAddress ()
    {
        return OrderShipAddress;
    }

    public void setOrderShipAddress (String OrderShipAddress)
    {
        this.OrderShipAddress = OrderShipAddress;
    }

    public String getOrderCountry ()
    {
        return OrderCountry;
    }

    public void setOrderCountry (String OrderCountry)
    {
        this.OrderCountry = OrderCountry;
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList(List<Product> productList)
    {
        this.productList = productList;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [OrderShipName = "+OrderShipName+", OrderState = "+OrderState+", OrderAmount = "+OrderAmount+", OrderTax = "+OrderTax+", OrderTrackingNumber = "+OrderTrackingNumber+", OrderCity = "+OrderCity+", OrderPhone = "+OrderPhone+", OrderShipAddress2 = "+OrderShipAddress2+", OrderEmail = "+OrderEmail+", OrderZip = "+OrderZip+", user_id = "+user_id+", OrderShipAddress = "+OrderShipAddress+", OrderCountry = "+OrderCountry+"]";
    }
}
