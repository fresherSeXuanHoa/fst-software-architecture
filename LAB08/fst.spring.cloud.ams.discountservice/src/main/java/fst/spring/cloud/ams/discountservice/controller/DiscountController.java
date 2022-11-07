package fst.spring.cloud.ams.discountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.cloud.ams.discountservice.entity.Discount;
import fst.spring.cloud.ams.discountservice.service.DiscountService;

@RestController
public class DiscountController {
	@Autowired
	DiscountService discountService;

	@GetMapping("/discounts")
	public List<Discount> findAll() {
		return discountService.findAll();
	}
}
