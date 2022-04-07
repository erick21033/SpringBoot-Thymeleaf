package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", length = 50, nullable = false)
	@Size(min = 2, max = 50, message = "{user.name.size}")
	private String name;
	
	@Column  // 預設相當於 @Column(name = "password", length = 255, nullable = true)
	@NotEmpty(message = "{user.password.empty}")
	private String password;
	
	@Column
	@Temporal(TemporalType.DATE) // TemporalType.DATE、TemporalType.TIME、TemporalType.TIMESTAMP
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{user.birth.null}")
	private Date birth;
	
	@Column
	@NotNull(message = "{user.height.null}")
	@Range(min = 30, max = 300, message = "{user.height.range}")
	private Integer height;
	
	@Column
	@NotNull(message = "{user.weight.null}")
	@Range(min = 1, max = 400, message = "{user.weight.range}")
	private Integer weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	
	
}
