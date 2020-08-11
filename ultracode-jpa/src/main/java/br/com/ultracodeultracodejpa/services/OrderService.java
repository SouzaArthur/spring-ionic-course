package br.com.ultracodeultracodejpa.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.Order;
import br.com.ultracodeultracodejpa.domain.OrderItem;
import br.com.ultracodeultracodejpa.domain.PaymentBoleto;
import br.com.ultracodeultracodejpa.domain.Product;
import br.com.ultracodeultracodejpa.domain.enums.PaymentStatusEnum;
import br.com.ultracodeultracodejpa.repositories.OrderItemRepository;
import br.com.ultracodeultracodejpa.repositories.OrderRepository;
import br.com.ultracodeultracodejpa.repositories.PaymentRepository;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BoletoService boletoService;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ClientService clientService;
	
	public Order getOrder(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("No object found with provided id."));
	}
	
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.getClient(obj.getClient().getId()));
		obj.getPayment().setPaymentStatus(PaymentStatusEnum.PENDENTE);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto payment = (PaymentBoleto) obj.getPayment();
			boletoService.fillPaymentWithBoleto(payment, obj.getInstant());
		}
		obj = orderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem ordI : obj.getItems()) {
			ordI.setDiscount(0.0);
			Product productReturned = productService.findById(ordI.getProduct().getId());
			ordI.setPrice(productReturned.getPrice());
			ordI.setProduct(productService.findById(ordI.getProduct().getId()));
			ordI.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		System.out.println(obj);
		return obj;
	}
}
