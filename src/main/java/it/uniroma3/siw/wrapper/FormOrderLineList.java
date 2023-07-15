package it.uniroma3.siw.wrapper;

import java.util.List;

import it.uniroma3.siw.model.OrderLine;


public class FormOrderLineList {
	List<OrderLine>  orderLinesList;

	public List<OrderLine> getOrderLinesList() {
		return orderLinesList;
	}

	public void setOrderLinesList(List<OrderLine> orderLinesList) {
		this.orderLinesList = orderLinesList;
	}
}
