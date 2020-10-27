package br.com.ultracodeultracodejpa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {
	
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public void initiateDataBase() throws ParseException {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Jardinagem");
		Category cat4 = new Category(null, "Auto-peças");
		Category cat5 = new Category(null, "Cosméticos");
		Category cat6 = new Category(null, "Vestuário");
		Category cat7 = new Category(null, "DIY");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducties().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducties().addAll(Arrays.asList(p2, p4));
		cat3.getProducties().addAll(Arrays.asList(p5, p6));
		cat4.getProducties().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducties().addAll(Arrays.asList(p8));
		cat6.getProducties().addAll(Arrays.asList(p9, p10));
		cat7.getProducties().addAll(Arrays.asList(p11));

		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Arthur Souza", "arthurdeveloperjava@gmail.com", "44878966532", ClientTypeEnum.PESSOAFISICA, bCryptPasswordEncoder.encode("senhaForte"));
		
		cli1.getTelephones().addAll(Arrays.asList("942424242", "845452362"));
		
		Address addr1 = new Address(null, "Rua Flores", "203", "Apto 208", "Jardim", "06948250", cli1, c1);
		Address addr2 = new Address(null, "Fim do universo", "42", "Restaurante", "Fim", "42424242", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));
		
		clientRepository.save(cli1);
		
		addressRepository.saveAll(Arrays.asList(addr1, addr2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
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
