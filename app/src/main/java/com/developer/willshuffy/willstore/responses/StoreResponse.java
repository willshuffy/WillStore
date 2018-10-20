//generate with http://www.jsonschema2pojo.org/


package com.developer.willshuffy.willstore.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("store")
    @Expose
    private List<Store> store = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

}
