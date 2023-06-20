package com.ibm.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Utility class for handling JSON Web Tokens (JWT).
 */
public class JwtUtil {

	// Generate a secure key for HS512 algorithm
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days

	/**
	 * Generates a JWT token for the given email.
	 *
	 * @param email The email of the user.
	 * @return The generated JWT token.
	 */
	public static String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, email);
	}

	/**
	 * Extracts the username (subject) from the JWT token.
	 *
	 * @param token The JWT token.
	 * @return The username extracted from the token.
	 */
	public static String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts the expiration date from the JWT token.
	 *
	 * @param token The JWT token.
	 * @return The expiration date extracted from the token.
	 */
	public static Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Checks if the JWT token is expired.
	 *
	 * @param token The JWT token.
	 * @return True if the token is expired, false otherwise.
	 */
	public static boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Generates a JWT token for the given claims.
	 *
	 * @param claims  The claims to include in the token.
	 * @param subject The subject of the token (e.g., username).
	 * @return The generated JWT token.
	 */
	private static String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	/**
	 * Extracts a specific claim from the JWT token.
	 *
	 * @param token          The JWT token.
	 * @param claimsResolver The function to extract the desired claim from the
	 *                       token's claims.
	 * @param <T>            The type of the claim to extract.
	 * @return The extracted claim value.
	 */
	private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Parses the JWT token and extracts all claims.
	 *
	 * @param token The JWT token.
	 * @return The parsed claims from the token.
	 */
	private static Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
