package br.com.ultracodeultracodejpa;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ultracodeultracodejpa.domain.Address;
import br.com.ultracodeultracodejpa.domain.Category;
import br.com.ultracodeultracodejpa.domain.City;
import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.domain.Order;
import br.com.ultracodeultracodejpa.domain.OrderItem;
import br.com.ultracodeultracodejpa.domain.Payment;
import br.com.ultracodeultracodejpa.domain.PaymentBoleto;
import br.com.ultracodeultracodejpa.domain.PaymentCard;
import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.domain.State;
import br.com.ultracodeultracodejpa.domain.enums.ClientTypeEnum;
import br.com.ultracodeultracodejpa.domain.enums.PaymentStatusEnum;
import br.com.ultracodeultracodejpa.repositories.AddressRepository;
import br.com.ultracodeultracodejpa.repositories.CategoryRepository;
import br.com.ultracodeultracodejpa.repositories.CityRepository;
import br.com.ultracodeultracodejpa.repositories.ClientRepository;
import br.com.ultracodeultracodejpa.repositories.OrderItemRepository;
import br.com.ultracodeultracodejpa.repositories.OrderRepository;
import br.com.ultracodeultracodejpa.repositories.PaymentRepository;
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
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, addr1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, addr2);
		
		Payment pmt1 = new PaymentCard(null, PaymentStatusEnum.QUITADO, ord1, 6);
		ord1.setPayment(pmt1);
		Payment pmt2 = new PaymentBoleto(null, PaymentStatusEnum.PENDENTE, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pmt2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pmt1, pmt2));
		
		OrderItem ordI1 = new OrderItem(p1, ord1, 0.00, 1, 2000.00);
		OrderItem ordI2 = new OrderItem(p3, ord1, 0.00, 2, 80.00);
		OrderItem ordI3 = new OrderItem(p2, ord2, 100.00, 1, 800.00);
		
		ord1.getItems().addAll(Arrays.asList(ordI1, ordI2));
		ord2.getItems().addAll(Arrays.asList(ordI3));

		p1.getItems().addAll(Arrays.asList(ordI1));
		p2.getItems().addAll(Arrays.asList(ordI2));
		p3.getItems().addAll(Arrays.asList(ordI3));
		
		orderItemRepository.saveAll(Arrays.asList(ordI1, ordI2, ordI3));
	}

}
