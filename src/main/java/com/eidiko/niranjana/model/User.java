package com.eidiko.niranjana.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="usrtab")
public class User {
	@Id
	private Integer id;
	private String name;
	private String username;
	private String password;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="rolestab", joinColumns=@JoinColumn(name="id"))
	@Column(name="role")
	private Set<String> roles;
}
