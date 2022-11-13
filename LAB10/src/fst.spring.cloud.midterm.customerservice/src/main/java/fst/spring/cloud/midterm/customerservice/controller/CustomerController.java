package fst.spring.cloud.midterm.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.cloud.midterm.customerservice.entity.Customer;
import fst.spring.cloud.midterm.customerservice.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	@GetMapping("/customer")
	public Customer findById(@RequestParam(name = "id") int id) {
		return customerService.findById(id);
	}
}
