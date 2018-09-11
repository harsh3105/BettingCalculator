package com.teamprofit.calculator.Models;

public class HistoryDataStore {
    int id;
    String event;
    String timestamp;
    String profite;
    String outcomebat;
    String bookmark;
    String exchange;
    String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfite() {
        return profite;
    }

    public void setProfite(String profite) {
        this.profite = profite;
    }

    public String getOutcomebat() {
        return outcomebat;
    }

    public void setOutcomebat(String outcomebat) {
        this.outcomebat = outcomebat;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
