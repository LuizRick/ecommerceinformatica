package com.les.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.les.ecommerce.command.AbstractCommand;
import com.les.ecommerce.command.AlterarCommand;
import com.les.ecommerce.command.ConsultarCommand;
import com.les.ecommerce.command.DeletarCommand;
import com.les.ecommerce.command.SalvarCommand;
import com.les.ecommerce.command.VisualizarCommand;

@Component
public class BaseController {
	@Autowired
	protected SalvarCommand salvarCmd;
	@Autowired
	protected AlterarCommand alterarCmd;
	@Autowired
	protected ConsultarCommand consultarCmd;
	@Autowired
	protected DeletarCommand deletarCmd;
	@Autowired
	protected VisualizarCommand visualizarCmd;
	protected Map<String, AbstractCommand> commands;
	@Autowired
	protected ObjectMapper mapper;
	
	@PostConstruct
	public void init() {
		commands = new HashMap<>();
		commands.put("SALVAR", salvarCmd);
		commands.put("ALTERAR", alterarCmd);
		commands.put("CONSULTAR", consultarCmd);
		commands.put("EXCLUIR", deletarCmd);
		commands.put("VISUALIZAR", visualizarCmd);
	}
}
