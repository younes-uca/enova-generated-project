package ma.enova.rth.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.Profil;
import ma.enova.rth.domain.core.Role;
import ma.enova.rth.domain.core.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Implemetntation UtilisateurDetails
 */

@Component 
public class UtilisateurDetailsImpl implements UserDetails, Serializable {

	private Long id;
	private String nom;
	private String prenom;
	private String cin;
	private String adresse;
	private String email;
	private String telephone;
	private String mobile;
	private boolean expertise;
	private String username;
	private String password;
	private boolean enabled;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private String nomComplet;
	private Etablissement	etablissement;
	private Profil	profil;
	private List<GrantedAuthority> authorities;
	private Utilisateur utilisateur;
	private List<Role> roleSet;
	private List<String> rolesByDomain;
	private List<String> categorieRoles;
		
	public UtilisateurDetailsImpl()  {
	
	}
	
	public UtilisateurDetailsImpl(Utilisateur utilisateur)  {
			this.id = utilisateur.getId();
			this.utilisateur = utilisateur;
			this.nom = utilisateur.getNom();
			this.prenom = utilisateur.getPrenom();
			this.cin = utilisateur.getCin();
			this.adresse = utilisateur.getAdresse();
			this.email = utilisateur.getEmail();
			this.telephone = utilisateur.getTelephone();
			this.mobile = utilisateur.getMobile();
			this.expertise = utilisateur.isExpertise();
			this.username = utilisateur.getUsername();
			this.password = utilisateur.getPassword();
			this.enabled = utilisateur.isEnabled();
			accountNonExpired = true;
			accountNonLocked = true;
			credentialsNonExpired = true;

		if (utilisateur.getEtablissement() != null)
			etablissement = new Etablissement(utilisateur.getEtablissement().getId());

		if (utilisateur.getProfil() != null)
			profil = new Profil(utilisateur.getProfil().getId());
		if (profil != null) {
			profil.setLibelle(utilisateur.getProfil().getLibelle());
		}
			authorities = new ArrayList<GrantedAuthority>();
			roleSet = new ArrayList<Role>();
		if (utilisateur.getProfil() != null && utilisateur.getProfil().getRolesList() != null && !utilisateur.getProfil().getRolesList().isEmpty())
			for (Iterator<Role> iterator = utilisateur.getProfil().getRolesList().iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				authorities.add(new GrantedAuthorityImpl(role));
				roleSet.add(role);
			}
			

	}
	
	
	
	public UtilisateurDetailsImpl(Long id) {
		this.id = id;
	}

	public void getRolesByDomaine(Integer domain) {
		this.rolesByDomain = new ArrayList<String>();
		if (domain != null)
			for (Iterator<Role> iterator = roleSet.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				if (role.getDomaine() != null && role.getDomaine().equals(domain))
					this.rolesByDomain.add(role.getLibelle());
			}

	}
	
	public List<String> getRolesByDomain() {
		return rolesByDomain;
	}

	public void setRolesByDomain(List<String> rolesByDomain) {
		this.rolesByDomain = rolesByDomain;
	}

	@JsonIgnore
	public List<Role> getRoleSet() {
		return roleSet;
	}

	@JsonIgnore
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public Long getId() {
		return id;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom_) {
		this.nom = nom_;
	}

	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom_) {
		this.prenom = prenom_;
	}

	public String getCin() {
		return this.cin;
	}
	public void setCin(String cin_) {
		this.cin = cin_;
	}

	public String getAdresse() {
		return this.adresse;
	}
	public void setAdresse(String adresse_) {
		this.adresse = adresse_;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email_) {
		this.email = email_;
	}

	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone_) {
		this.telephone = telephone_;
	}

	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile_) {
		this.mobile = mobile_;
	}

	public boolean isExpertise() {
		return this.expertise;
	}
	public void setExpertise(boolean expertise_) {
		this.expertise = expertise_;
	}

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username_) {
		this.username = username_;
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password_) {
		this.password = password_;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	public void setEnabled(boolean enabled_) {
		this.enabled = enabled_;
	}




	public Etablissement getEtablissement() {
		return this.etablissement;
	}
		
	public void setEtablissement(Etablissement etablissement_) {
		this.etablissement = etablissement_;
	}
	public Profil getProfil() {
		return this.profil;
	}
		
	public void setProfil(Profil profil_) {
		this.profil = profil_;
	}
	

	public List<String> getCategorieRoles() {
		return categorieRoles;
	}

	public void setCategorieRoles(List<String> categorieRoles) {
		this.categorieRoles = categorieRoles;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof UtilisateurDetailsImpl) {
			return username.equals(((UtilisateurDetailsImpl) o).username);
		}
		return false;
	}
	
	public String getNomComplet() {
		if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty())
			nomComplet = nom + " " + prenom + " (" + username + ")";
		else
			nomComplet = username;
		return nomComplet;
	}
		
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public String toString() {
		return this.id.toString();
	}	
	
}
