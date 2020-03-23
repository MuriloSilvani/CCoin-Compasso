package br.com.compasso.usuarios.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.compasso.usuarios.config.validation.CustomNotFoundException;
import br.com.compasso.usuarios.form.usuarios.UsuariosForm;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.NiveisAcessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;

@Entity
@Table(name = "usuarios")
public class Usuarios implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String login;
	@Column(unique = true)
	private String email;
	@Column(length = 60)
	private String senha;
	private String nome;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nivel_acesso", referencedColumnName = "id")
	private NiveisAcesso idNivelAcesso;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cargo", referencedColumnName = "id")
	private Cargos idCargo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidade", referencedColumnName = "id")
	private Unidades idUnidade;
	private int credito;
	private boolean responsavelUnidade;

	public Usuarios() {

	}

	public Usuarios(UsuariosForm form, NiveisAcessoRepository niveisAcessoRepository, CargosRepository cargosRepository, UnidadesRepository unidadesRepository) throws CustomNotFoundException {
		
		Optional<NiveisAcesso> nivelAcesso = niveisAcessoRepository.findById(form.getIdNivelAcesso());
		if(nivelAcesso.isPresent()) {
			this.idNivelAcesso = nivelAcesso.get();
		} else {
			throw new CustomNotFoundException("Nivel acesso '" + form.getIdNivelAcesso() +"' não encontrado");
		}
		
		Optional<Cargos> cargo = cargosRepository.findById(form.getIdCargo());
		if (cargo.isPresent()) {
			this.idCargo = cargo.get();
		} else {
			throw new CustomNotFoundException("Cargo '" + form.getIdCargo() +"' não encontrado");
		}
			
		Optional<Unidades> unidade = unidadesRepository.findById(form.getIdUnidade());
		if (unidade.isPresent()) {
			this.idUnidade = unidade.get();
		} else {
			throw new CustomNotFoundException("Unidade '" + form.getIdUnidade() +"' não encontrada");
		}
		
		
		this.login = form.getLogin();
		this.email = form.getEmail();
		this.senha = new BCryptPasswordEncoder().encode(form.getSenha());
		this.nome = form.getNome();
		this.responsavelUnidade = form.getResponsavelUnidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public NiveisAcesso getIdNivelAcesso() {
		return idNivelAcesso;
	}

	public void setIdNivelAcesso(NiveisAcesso idNivelAcesso) {
		this.idNivelAcesso = idNivelAcesso;
	}

	public Cargos getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Cargos idCargo) {
		this.idCargo = idCargo;
	}

	public Unidades getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Unidades idUnidade) {
		this.idUnidade = idUnidade;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public boolean getResponsavelUnidade() {
		return responsavelUnidade;
	}

	public void setResponsavelUnidade(boolean responsavelUnidade) {
		this.responsavelUnidade = responsavelUnidade;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<NiveisAcesso> arrayList = new ArrayList<>();
		arrayList.add(idNivelAcesso);

		return arrayList;
	}

	@Override
	public String getPassword() {

		return this.senha;
	}

	@Override
	public String getUsername() {

		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}
