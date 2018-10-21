
package com.developer.willshuffy.willstore.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product")
    @Expose
    private List<Product> product = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

}
