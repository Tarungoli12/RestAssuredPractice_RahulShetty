package com.automation.Pojo.Ecommerce;

import java.util.List;

public class ResponseGetOrderDetails {
    private Data data;
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
