package it.epicode.phronesis.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import it.epicode.phronesis.businesslayer.security.SecurityUserDetails;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.entities.enums.JwtType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

	//KEY & EXPIRATION PER IL JWT DELL'AUTENTICAZIONE
	@Value("${jwt.key.authentication}")
	private String authKey;
	@Value("${jwt.expirationMs.authentication}")
	private long authExpirationMs;

	//KEY & EXPIRATION PER IL JWT DELLA VERIFICA DELLO USER TRAMITE LINK
	@Value("${jwt.key.validation.link}")
	private String validationLinkKey;
	@Value("${jwt.expirationMs.validation.link}")
	private long validationLinkExpirationMs;

	private SecretKey getKey(String key) {
		byte[] keyBytes = key.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private long getExpiration(String type) {
		return "AUTHENTICATION".equals(type) ? authExpirationMs : validationLinkExpirationMs;
	}

	private String getKeyByType(String type) {
		return "AUTHENTICATION".equals(type) ? authKey : validationLinkKey;
	}

	public String generateToken(Long userId) {
		String keyStr = getKeyByType(JwtType.VALIDATION_LINK.name());
		long expirationMs = getExpiration(JwtType.VALIDATION_LINK.name());
		SecretKey key = getKey(keyStr);

		String token = Jwts.builder()
				.subject(String.valueOf(userId))
				.issuedAt(new Date())
				.issuer("MySpringApplication")
				.expiration(new Date(new Date().getTime() + expirationMs))
				.signWith(key)
				.compact();
		// a scopo di test il link è del backned ma dovrebbe essere quello del frontend che porta ad una pagina del frontend
		//questa pagina è responsabile di richiamare l'enpoint del abckend e di fornire un messaggio a schermo a seconda della risposta del abckend
		//questa pagina probabilmene sarà un loader e quando la risposat arriverà ci sarà un messaggio a tutto schermo con un modale o un popup
		return "http://localhost:8080/api/user/activate?token=" + token;

	}


	public String generateToken(Authentication auth) {
		String keyStr = getKeyByType(JwtType.AUTHENTICATION.name());
		long expirationMs = getExpiration(JwtType.AUTHENTICATION.name());
		SecretKey key = getKey(keyStr);

		var user = (SecurityUserDetails) auth.getPrincipal();
		return Jwts.builder()
				.subject(user.getUsername())
				.issuedAt(new Date())
				.issuer("MySpringApplication")
				.expiration(new Date(new Date().getTime() + expirationMs))
				.signWith(key)
				.compact();

	}

	public boolean isTokenValid(String token, String type) {
		try {
			String keyStr = getKeyByType(type);
			long expirationMs = getExpiration(type);
			SecretKey key = getKey(keyStr);

			//PRENDIAMO LA DATA DI SCADENZA DAL TOKEN
			Date expirationDate = Jwts.parser()
					.verifyWith(key).build()
					.parseSignedClaims(token).getPayload().getExpiration();

			//VERIFICHIAMO SE LA DATA DI SCADENZA TROVATA E PRIMA O DOPO LA DATA DI OGGI
			if (expirationDate.before(new Date()))
				throw new JwtException("Token expired");

			Jwts.parser()
					.verifyWith(key).requireIssuer("MySpringApplication");

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getSubjectFromToken(String token, String type) {
		String keyStr = getKeyByType(type);
		SecretKey key = getKey(keyStr);
		return Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(token).getPayload().getSubject();
	}

}
