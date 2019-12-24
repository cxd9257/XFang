package com.demo.fang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class StockDataBean implements Serializable, MultiItemEntity {

    private static final long serialVersionUID = -5501831682231549698L;

    @Id(autoincrement = true)
    private Long ids;
    private String code;
    private String name;
    private String buyPrice;
    private String newPrice;
    private String buyMeasure;
    private String marketValue;
    private String allMarketValue;
    private String date;

    @Generated(hash = 1332217117)
    public StockDataBean(Long ids, String code, String name, String buyPrice,
            String newPrice, String buyMeasure, String marketValue,
            String allMarketValue, String date) {
        this.ids = ids;
        this.code = code;
        this.name = name;
        this.buyPrice = buyPrice;
        this.newPrice = newPrice;
        this.buyMeasure = buyMeasure;
        this.marketValue = marketValue;
        this.allMarketValue = allMarketValue;
        this.date = date;
    }

    @Generated(hash = 875805644)
    public StockDataBean() {
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public Long getIds() {
        return this.ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getNewPrice() {
        return this.newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getBuyMeasure() {
        return this.buyMeasure;
    }

    public void setBuyMeasure(String buyMeasure) {
        this.buyMeasure = buyMeasure;
    }

    public String getMarketValue() {
        return this.marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getAllMarketValue() {
        return this.allMarketValue;
    }

    public void setAllMarketValue(String allMarketValue) {
        this.allMarketValue = allMarketValue;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
