package kz.itstep.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Item extends Entity {
    private String name;
    private int categoryId;
    private int startPrice;
    private int currentPrice;
    private Timestamp startTime;
    private Timestamp endTime;
    private int userId;
    private int lastUpdatedUserId;

    public String getName() {
        return name;
    }

    public int getLastUpdatedUserId() {
        return lastUpdatedUserId;
    }

    public void setLastUpdatedUserId(int lastUpdatedUserId) {
        this.lastUpdatedUserId = lastUpdatedUserId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
