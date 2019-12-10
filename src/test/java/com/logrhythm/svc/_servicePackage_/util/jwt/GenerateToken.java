package com.logrhythm.svc._servicePackage_.util.jwt;

import com.logrhythm.core.util.jwt.TokenUtils;
import java.util.HashMap;
import org.eclipse.microprofile.jwt.Claims;

public class GenerateToken {
  public static void main(String[] args) throws Exception {

    // If we ever care, we can read the path to the pk and the duration from the cmd line.
    String privateKey = "/jwt/jwt-privateKey.pem";
    long duration = 10L * 365 * 24 * 60 * 60; //10 years
    if( args.length == 0 ){
      generateDefaultTokens(privateKey, duration);
    }
    else {
      System.err.println(generate(privateKey,args[0], duration));
    }
  }

  private static void generateDefaultTokens(String privateKey, long duration) throws Exception{

    String[] tokenFiles = new String[]{
        "/jwt/token-definitions/all-admin.json",
        "/jwt/token-definitions/_entityName_-admin.json",
        "/jwt/token-definitions/all-view.json",
        "/jwt/token-definitions/_entityName_-view.json"
    };

    for( String tokenFile : tokenFiles ){
      System.err.println("Token generated from " + tokenFile + " :" );
      System.err.println(generate(privateKey,tokenFile, duration));
      System.err.println();
    }
  }

  private static String generate(String privateKey, String claimsJsonFilename, long duration) throws Exception {
    HashMap<String, Long> timeClaims = new HashMap<>();
    long exp = TokenUtils.currentTimeInSecs() + duration;
    timeClaims.put(Claims.exp.name(), exp);
    return TokenUtils.generateTokenString(privateKey, claimsJsonFilename, timeClaims);
  }
}
