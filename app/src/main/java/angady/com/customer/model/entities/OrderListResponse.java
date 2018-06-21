package angady.com.customer.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shyam on 6/8/2018.
 */

public class OrderListResponse {
    private Integer status;

    private String message;
    @SerializedName("data")
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
