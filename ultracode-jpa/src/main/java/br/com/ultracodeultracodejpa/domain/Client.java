package br.com.ultracodeultracodejpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ultracodeultracodejpa.domain.enums.ClientTypeEnum;
import br.com.ultracodeultracodejpa.domain.enums.RolesEnum;

@Entity
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer clientType;
	private String password;
	
	@ElementCollection
	@CollectionTable(name="TELEPHONE")
	private Set<String> telephones = new HashSet<>();
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	private List<Address> addresses =  new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="ROLES")
	private Set<Integer> roles = new HashSet<>();
	
	public Client() {
		this.setRole(RolesEnum.CLIENT);
	}

	public Client(Integer id, String name, String email, String cpfOrCnpj, ClientTypeEnum clientType, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.clientType = clientType == null ? null : clientType.getCode();
		this.password = password;
		this.setRole(RolesEnum.CLIENT);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ClientTypeEnum getClientType() {
		return ClientTypeEnum.toEnum(clientType);
	}

	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType.getCode();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<String> telephones) {
		this.telephones = telephones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RolesEnum> getRoles() {
		return this.roles.stream().map(x -> RolesEnum.toEnum(x)).collect(Collectors.toSet());
	}

	public void setRole(RolesEnum role) {
		this.roles.add(role.getCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
