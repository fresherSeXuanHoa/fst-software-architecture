package fst.spring.cloud.midterm.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fst.spring.cloud.midterm.customerservice.entity.Customer;
import fst.spring.cloud.midterm.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findById(int id) {
		return customerRepository.findById(id).get();
	}
}
