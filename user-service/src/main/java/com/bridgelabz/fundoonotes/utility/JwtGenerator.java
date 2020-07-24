
package com.bridgelabz.fundoonotes.utility;

/*
 *  author : Sandhya
 */

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Component
public class JwtGenerator {
	private static final String SECRET = "2129152050365";

	/* Method to generate the token for the particular userId */
	public String jwtToken(long id) {
		String token = null;
		try {
			token = JWT.create().withClaim("id", id).sign(Algorithm.HMAC512(SECRET));
		} catch (IllegalArgumentException | JWTCreationException e) {

			e.printStackTrace();
		}
		return token;
	}

	/* Method to decode the token to id */
	public Long parseJWT(String jwt) {
		Long userId = (long) 0;
		if (jwt != null) {
			userId = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwt).getClaim("id").asLong();
		}
		return userId;
	}

}