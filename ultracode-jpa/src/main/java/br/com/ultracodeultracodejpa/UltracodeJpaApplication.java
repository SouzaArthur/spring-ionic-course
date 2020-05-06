package br.com.ultracodeultracodejpa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ultracodeultracodejpa.domain.Address;
import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.domain.City;
import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.domain.State;
import br.com.ultracodeultracodejpa.domain.enums.ClientTypeEnum;
import br.com.ultracodeultracodejpa.repositories.AddressRepository;
import br.com.ultracodeultracodejpa.repositories.CategoryRepository;
import br.com.ultracodeultracodejpa.repositories.CityRepository;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.repositories.ProductRepository;
import br.com.ultracodeultracodejpa.repositories.StateRepository;

@SpringBootApplication
public class UltracodeJpaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UltracodeJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducties().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducties().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "44878966532", ClientTypeEnum.PESSOAFISICA);
		
		cli1.getTelephones().addAll(Arrays.asList("942424242", "845452362"));
		
		Address addr1 = new Address(null, "Rua Flores", "203", "Apto 208", "Jardim", "06948250", cli1, c1);
		Address addr2 = new Address(null, "Fim do universo", "42", "Restaurante", "Fim", "42424242", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));
		
		clientRepository.save(cli1);
		
		addressRepository.saveAll(Arrays.asList(addr1, addr2));
		
		
	}

}
