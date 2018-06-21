
package angady.com.customer.model.entities;


import java.util.List;

public class ShopListResponse {


    private Integer status;

    private String message;

    private List<Shop> shop = null;


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }


}
