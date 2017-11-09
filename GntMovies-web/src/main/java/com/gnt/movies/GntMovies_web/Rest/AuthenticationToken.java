package com.gnt.movies.GntMovies_web.Rest;

import java.security.Key;
import java.util.Date;

import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class AuthenticationToken {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationToken.class);
	public static final Key key = MacProvider.generateKey();

	private static boolean validateToken(String token){

		logger.info(String.format("Validating token :[%s]", token));
		//isNull
		if (token == null) {
			logger.info(String.format("Authorization header must be provided"));
			return false;
		}
		//isSigned
		if (!Jwts.parser().isSigned(token)) {
			logger.info(String.format("Unsigned token"));
			return false;
		} 
		//isExpired
		return isExpired(token);
	}

	private static boolean isExpired(String token) {
		System.out.println("Fetching claim: " + key.toString());
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if (now.after(claims.getExpiration())) {
			logger.info(String.format("Expired,token signed at %s, expired at %s, now : %s",
					claims.getIssuedAt().toString(), claims.getExpiration().toString(), now.toString()));
			return false;
		}
		return true;
	}

	public static String issueToken(String keyname) {
		String jws;
		if (keyname != null && keyname != "") {
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			long expMillis = nowMillis + 300000L;
			Date exp = new Date(expMillis);
			jws = Jwts.builder().setSubject(keyname).setIssuedAt(now).signWith(SignatureAlgorithm.HS512, key)
					.setExpiration(exp).compact();
			System.out.println("User [" + keyname + "] , encrypted with key: " + key.toString());
			System.out.println("Now: " + now + " issued new token [" + jws + "] with expiration: " + exp);
		} else {
			jws = null;
		}
		return jws;
	}

	public static Boolean isTokenValid(String token, String className) {
		logger.info("Validating token: [" + token.toString() + "] from class:"+className);
		return validateToken(token);
	}
}