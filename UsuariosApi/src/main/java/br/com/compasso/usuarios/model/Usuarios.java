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

import br.com.compasso.usuarios.form.UsuariosForm;
import br.com.compasso.usuarios.repository.CargosRepository;
import br.com.compasso.usuarios.repository.Niveis_acessoRepository;
import br.com.compasso.usuarios.repository.UnidadesRepository;

@Entity
@Table(name = "usuarios")
public class Usuarios implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String login;
	@Column(unique=true)
	private String email;
	private String senha;
	private String nome;
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "id_nivel_acesso", referencedColumnName = "id")
	private Niveis_acesso id_nivel_acesso;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cargo", referencedColumnName = "id")
	private Cargos id_cargo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_unidade", referencedColumnName = "id")
	private Unidades id_unidade;
	private int credito;
	private boolean responsavel_unidade;

	public Usuarios() {

	}

	public Usuarios(UsuariosForm form, Niveis_acessoRepository niveis_acessoRepository,
			CargosRepository cargosRepository, UnidadesRepository unidadesRepository) {

		Optional<Niveis_acesso> nivel_acesso = niveis_acessoRepository.findById(form.getId_nivel_acesso());
		Optional<Cargos> cargo = cargosRepository.findById(form.getId_cargo());
		Optional<Unidades> unidade = unidadesRepository.findById(form.getId_unidade());

		if (nivel_acesso.isPresent() && cargo.isPresent() && unidade.isPresent()) {

			this.login = form.getLogin();
			this.email = form.getEmail();
			this.senha = new BCryptPasswordEncoder().encode(form.getSenha());
			this.nome = form.getNome();
			this.id_nivel_acesso = nivel_acesso.get();
			this.id_cargo = cargo.get();
			this.id_unidade = unidade.get();
			this.credito = 0;
			this.responsavel_unidade = form.isResponsavel_unidade();

		}
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
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Niveis_acesso getId_nivel_acesso() {
		return id_nivel_acesso;
	}

	public void setId_nivel_acesso(Niveis_acesso id_nivel_acesso) {
		this.id_nivel_acesso = id_nivel_acesso;
	}

	public Cargos getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Cargos id_cargo) {
		this.id_cargo = id_cargo;
	}

	public Unidades getId_unidade() {
		return id_unidade;
	}

	public void setId_unidade(Unidades id_unidade) {
		this.id_unidade = id_unidade;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public boolean isResponsavel_unidade() {
		return responsavel_unidade;
	}

	public void setResponsavel_unidade(boolean responsavel_unidade) {
		this.responsavel_unidade = responsavel_unidade;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<Niveis_acesso> arrayList = new ArrayList<>();
		
		arrayList.add(id_nivel_acesso);
		
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
