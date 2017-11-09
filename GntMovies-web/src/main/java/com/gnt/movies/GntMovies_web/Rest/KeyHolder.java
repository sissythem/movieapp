package com.gnt.movies.GntMovies_web.Rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.util.Date;

import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

public class KeyHolder 
{
    public static final Key key = MacProvider.generateKey();
	private static final Logger logger = LoggerFactory.getLogger(SchedulerBean.class);
    
    public static String issueToken(String keyname) {
        String jws;
        if (keyname != null && keyname != "") {
            Key key = KeyHolder.key;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            long expMillis = nowMillis + 300000L;
            Date exp = new Date(expMillis);
            jws = Jwts.builder()
                        .setSubject(keyname)
                        .setIssuedAt(now)
                        .signWith(SignatureAlgorithm.HS512, key)
                        .setExpiration(exp)
                        .compact();
            System.out.println("User [" + keyname + "] , encrypted with key: " + key.toString());
            System.out.println("Now: " + now + " issued new token ["+jws+"] with expiration: " + exp);
        } else {
            jws = null;
        }
        return jws;
    }
    
    public static Boolean checkToken(String token, String entityName) 
    {
        logger.info("Checktoken entname : " + entityName);
        logger.info("Validating token: [" + token.toString() + "]");
        try 
        {
            AuthenticationFilter.filter(token);
            return true;
        } 
        catch(Exception ex) 
        {
            logger.info("Token [" + token.toString() + "] failed to validate");
            return false;
        }
        
    }
}
