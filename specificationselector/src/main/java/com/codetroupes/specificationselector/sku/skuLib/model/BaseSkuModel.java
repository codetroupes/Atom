package com.codetroupes.specificationselector.sku.skuLib.model;

/**
 * Sku
 * <p>
 * Sku基本模型数据
 */
public class BaseSkuModel {

    //base 属性
    private String price;//价格
    private int stock;//库存

    public BaseSkuModel() {
    }

    public BaseSkuModel(String price, int stock) {
        this.price = price;
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
