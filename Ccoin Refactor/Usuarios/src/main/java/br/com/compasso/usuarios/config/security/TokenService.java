package br.com.compasso.usuarios.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.compasso.usuarios.model.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String exp;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authenticate) {

		Usuarios logado = (Usuarios) authenticate.getPrincipal(); 
		Date hoje = new Date();
		Date dataExp = new Date(hoje.getTime() + Long.parseLong(exp));
		
		return Jwts.builder()
				.setIssuer("Api de Usuários")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {

		try {
			
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public Long getIdUsuario(String token) {

		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	

}
