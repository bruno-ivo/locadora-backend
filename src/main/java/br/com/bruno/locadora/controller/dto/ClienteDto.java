package br.com.bruno.locadora.controller.dto;

import br.com.bruno.locadora.modelo.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDto {
	
	private Long id;
	private String nome;
	private String codigoDoCliente;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.codigoDoCliente = cliente.getCodigoDoCLiente();
	}

	public String getCodigoDoCliente() {
		return codigoDoCliente;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
	

}
