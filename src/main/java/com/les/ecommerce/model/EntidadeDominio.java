package com.les.ecommerce.model;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.les.ecommerce.application.IApplicationData;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntidadeDominio implements IEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="created", columnDefinition="datetime default NOW()")
	protected LocalDateTime created;
	
	@Transient
	protected IApplicationData appData;
	
	@Transient
	protected String action;
	
	public LocalDateTime getCreated() {
		return created;
	}

	public IApplicationData getAppData() {
		return appData;
	}

	public void setAppData(IApplicationData appData) {
		this.appData = appData;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
