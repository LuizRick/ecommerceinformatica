package com.les.ecommerce.model.messages;

import com.les.ecommerce.model.EntidadeAplicacao;

public class SessionMessages extends EntidadeAplicacao {
	
	private String content;
	
	public SessionMessages() {
		
	}
	
	public SessionMessages(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
}
