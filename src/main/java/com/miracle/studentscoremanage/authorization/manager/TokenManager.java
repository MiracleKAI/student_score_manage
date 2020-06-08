package com.miracle.studentscoremanage.authorization.manager;


import com.miracle.studentscoremanage.authorization.model.TokenModel;

/**
 * @author miracle
 */
public interface TokenManager {

  TokenModel createToken(long paramLong);
  
  boolean checkToken(TokenModel paramTokenModel);
  
  TokenModel getToken(String paramString);
  
  void deleteToken(long paramLong);
}
