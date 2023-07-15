package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Deliveryman;
import it.uniroma3.siw.model.Dish;
import it.uniroma3.siw.model.Feedback;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.model.OrderState;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.DeliveyManService;
import it.uniroma3.siw.service.DishService;
import it.uniroma3.siw.service.FeedbackService;
import it.uniroma3.siw.service.OrderLineService;
import it.uniroma3.siw.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private DishService dishService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLineService orderLineService;
	@Autowired
	private DeliveyManService deliveryManService;
	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping("/modifyOrder/{ido}")
	public String modifyOrder(@PathVariable("ido") Long ido,Model model) {
    	Order order = orderService.getOrder(ido);
    	
		model.addAttribute("order", order);
    	model.addAttribute("dishes", dishService.getAllDishes());
    	
		return "formNewOrder.html";
    	
	}
	
    @GetMapping("/newOrder/{idc}")
    public String newOrder(@PathVariable("idc") Long idc,Model model) {
    	
    	Customer customer = customerService.getCustomer(idc);
    	Order order = orderService.createNewOrder(customer);
    	
    	return "redirect:/modifyOrder/"+orderService.getId(order);
    }
    
    @GetMapping("/addOrderLineToOrder/{ido}/{idp}/{qty}")
    public String addOrderLineToOrder(@PathVariable("ido") Long ido,@PathVariable("idp") Long idp,@PathVariable("qty") int qty,Model model) {
    	
    	Order order = orderService.getOrder(ido);
    	Dish dish = dishService.getDish(idp);
    	
    	
    	OrderLine orderLine = new OrderLine();
    	
    	orderLineService.initializeAttributes(orderLine,dish,qty,order);
    	orderService.addOrderLine(order,orderLine);
    	
    	return "redirect:/modifyOrder/"+ido;
    }
    
    @GetMapping("/removeOrderLineFromOrder/{ido}/{idl}")
    public String removeOrderLineFromOrder(@PathVariable("ido") Long ido,@PathVariable("idl") Long idl,Model model) {
    	
    	OrderLine line = orderLineService.getOrderLine(idl);
    	orderLineService.delete(line);
    	
    	return "redirect:/modifyOrder/"+ido;
    }
    
    @GetMapping("/saveOrder/{ido}")
    public String saveOrder(@PathVariable("ido") Long ido,Model model) {
    	
    	Order order = orderService.getOrder(ido);
    	if(!orderService.isEmpty(order)) {
    		orderService.setReady(order);
    		return "redirect:/myOrdersCustomer/"+customerService.getId(orderService.getCustomer(order));
        }
    	return "redirect:/modifyOrder/"+ido;
    }
    
    
    @GetMapping("/myOrdersCustomer/{idu}")
    public String ordersCustomer(@PathVariable("idu") Long idu,Model model) {
    	
    	Customer customer = customerService.getCustomer(idu);
    	
    	model.addAttribute("orders", customerService.getOrders(customer));
    	model.addAttribute("feedback", new Feedback());
    	
        return "myOrders.html";
    }
    
    @GetMapping("/deliveryman/deliveryManOrders/{idu}")
    public String deliveryManOrders(@PathVariable("idu") Long idu,Model model) {
    	
    	Deliveryman deliveryman = deliveryManService.getDeliveryMan(idu);
    	
    	model.addAttribute("readyOrders", orderService.getOrdersByState(OrderState.PRONTO));
    	model.addAttribute("deliveringOrders", deliveryManService.getOrdersByState(deliveryman,OrderState.SPEDITO));
    	model.addAttribute("deliveredOrders", deliveryManService.getOrdersByState(deliveryman,OrderState.CONSEGNATO));
    	model.addAttribute("numDeliveringOrders", deliveryManService.countOrdersByState(deliveryman,OrderState.SPEDITO));
    	model.addAttribute("numDeliveredOrders", deliveryManService.countOrdersByState(deliveryman,OrderState.CONSEGNATO));
    	model.addAttribute("rating", feedbackService.getRatingOfDeliveryman(deliveryman));

        return "/deliveryman/deliveryManOrders.html";
    }
    
    @GetMapping("/deliveryman/orderDelivered/{idu}/{ido}")
    public String orderDelivered(@PathVariable("idu") Long idu,@PathVariable("ido") Long ido,Model model) {
    	
    	Order order = orderService.getOrder(ido);
    	Deliveryman deliveryman = deliveryManService.getDeliveryMan(idu);

    	deliveryManService.deliverOrder(deliveryman,order);

    	return "redirect:/deliveryman/deliveryManOrders/"+idu;
    }
    
    
    @GetMapping("/deliveryman/takeOrder/{idu}/{ido}")
    public String takeOrder(@PathVariable("idu") Long idu,@PathVariable("ido") Long ido,Model model) {
    	
    	Order order = orderService.getOrder(ido);
    	Deliveryman deliveryman = deliveryManService.getDeliveryMan(idu);
    	
    	if (!deliveryManService.isOccupied(deliveryman))
    		deliveryManService.takeOrder(deliveryman,order);
        	
        return "redirect:/deliveryman/deliveryManOrders/"+idu;
    }
    
    
    
    @GetMapping("/deleteOrder/{idu}/{ido}")
    public String orders(@PathVariable("idu") Long idu,@PathVariable("ido") Long ido,Model model) {
    	
    	Order order = orderService.getOrder(ido);
    	orderService.delete(order);
    	
    	return "redirect:/myOrdersCustomer/"+idu;
    }
}
