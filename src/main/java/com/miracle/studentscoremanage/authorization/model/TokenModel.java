package com.miracle.studentscoremanage.authorization.model;


/**
 * @author miracle
 */
public class TokenModel {
    private Long userId;
    private String token;

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }


    public TokenModel() {
    }


    public Long getUserId() {
        return this.userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getToken() {
        return this.token;
    }


    public void setToken(String token) {
        this.token = token;
    }
}
