package angady.com.customer.model.network;

import java.util.List;

import angady.com.customer.model.entities.Product;

/**
 * Created by shyam on 6/15/2018.
 */

public class ProductListResponse {
    private String message;

    private String status;

    private List<Product> productsList;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public List<Product> getProducts ()
    {
        return productsList;
    }

    public void setProducts (List<Product> productsList)
    {
        this.productsList = productsList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", status = "+status+"]";
    }
}
