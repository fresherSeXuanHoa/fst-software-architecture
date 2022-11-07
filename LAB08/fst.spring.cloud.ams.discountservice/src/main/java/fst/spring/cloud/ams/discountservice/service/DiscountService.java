package fst.spring.cloud.ams.discountservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fst.spring.cloud.ams.discountservice.entity.Discount;
import fst.spring.cloud.ams.discountservice.repository.DiscountRepository;

@Service
public class DiscountService {
	@Autowired
	DiscountRepository discountRepository;

	public List<Discount> findAll() {
		return discountRepository.findAll();
	}
}
