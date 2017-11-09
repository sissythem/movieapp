package com.gnt.movies.GntMovies_web.Rest;

import java.util.Date;

import javax.ws.rs.NotAuthorizedException;
import java.security.Key;
import io.jsonwebtoken.*;
import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

public class AuthenticationFilter  {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerBean.class);

    public static void filter(String token) throws Exception {
        // Check if the HTTP Authorization header is present and formatted correctly 
        if (token == null) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        else validateToken(token);   
    }

    private static void validateToken(String token) throws Exception {
        
        logger.info(String.format("Validating token :[%s]",token));
    	Key key = KeyHolder.key;
        System.out.println("decrypting with key: " + key.toString());

    	try {
            if (!Jwts.parser().isSigned(token))
            {
            	logger.info(String.format("Unsigned token"));

                throw new Exception();
            }
            System.out.println("Fetching claim: " + key.toString());
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            if (now.after(claims.getExpiration()))
            {
                logger.info(String.format("Expired,token signed at %s, expired at %s, now : %s",
                		claims.getIssuedAt().toString(),claims.getExpiration().toString(),now.toString()));
                throw new Exception();
            }
    	} catch (SignatureException e) {
            logger.error(String.format("Signature exception."), e);
            throw e;
    	}
        catch (Exception e)
        {
        	logger.error(String.format("Exception : "), e);
            throw e;
        }
    }
}