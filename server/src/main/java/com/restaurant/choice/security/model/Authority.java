package com.restaurant.choice.security.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
	@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
	private Long id;

	@Column(name = "NAME", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<UserSecurity> users;
	
	public Authority(Long id, @NotNull AuthorityName name) {
		super();
		this.id = id;
		this.name = name;
	}
	  
	public Authority() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<UserSecurity> getUsers() {
		return users;
	}

	public void setUsers(List<UserSecurity> users) {
		this.users = users;
	}
}