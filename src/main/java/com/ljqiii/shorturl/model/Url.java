package com.ljqiii.shorturl.model;

import java.sql.Timestamp;

public class Url {

    String id;
    String originurl;
    Timestamp expiretime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginurl() {
        return originurl;
    }

    public void setOriginurl(String originurl) {
        this.originurl = originurl;
    }

    public Timestamp getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Timestamp expiretime) {
        this.expiretime = expiretime;
    }

    public Url() {
    }

    public Url(String id, String originurl, Timestamp expiretime) {
        this.id = id;
        this.originurl = originurl;
        this.expiretime = expiretime;
    }
}
