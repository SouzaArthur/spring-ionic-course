package br.com.ultracodeultracodejpa.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.ultracodeultracodejpa.services.customvalidators.CpfOrCnpjValidator;

public class ClientNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Name field is required")
	@Length(min=5, max=100, message="The name should have between 5 and 120 characters")
	private String name;
	
	@NotEmpty(message="Email field is required")
	@Email(message="Email field is required")
	private String email;
	
	@NotEmpty(message="cpf or cnpj is required")
	@CpfOrCnpjValidator
	private String cpfOrCnpj;
	
	private Integer clientType;
	
	@NotEmpty(message="phone is required")
	private String phone;
	
	private String phone2;
	private String phone3;
	
	@NotEmpty(message="logradouro is required")
	private String logradouro;
	
	@NotEmpty(message="numero is required")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message="cep is required")
	private String cep;
	
	private Integer cityId;
	
	public ClientNewDTO() {}

	public ClientNewDTO(String name, String email, String cpfOrCnpj, Integer clientType, String logradouro,
			String numero, String complemento, String bairro, String cep, Integer cityId, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.clientType = clientType;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cityId = cityId;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
