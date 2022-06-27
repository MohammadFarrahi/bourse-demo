package com.example.boursedemo.model.DTO;

public class OrderDTO {
    private Integer id;
    private String side;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public boolean checkNullability() {
        if(this.price == null || this.side == null)
            return true;
        return false;
    }
}
