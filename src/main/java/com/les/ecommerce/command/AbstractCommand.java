package com.les.ecommerce.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.facade.Facade;

@Component
public abstract class AbstractCommand implements ICommand {
	
	@Autowired
	protected Facade fachada;
}
