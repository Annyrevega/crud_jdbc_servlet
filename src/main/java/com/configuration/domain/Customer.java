package com.configuration.domain;

public class Customer {
    private Long Id;
    private String productName;
    private String price;
    private String ShopId;

    public Customer() {
    }


    public Customer(long id, String product, String price, String shop) {
        super();
        Id = id;
        this.productName = product;
        this.price = price;
        ShopId = shop;
    }

    public Customer(String product, String price, String shop) {
        super();

        this.productName = product;
        this.price = price;
        ShopId = shop;
    }

    public Long getId() {
        return Id;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }
}
