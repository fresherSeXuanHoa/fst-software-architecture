package fst.spring.cloud.ams.discountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fst.spring.cloud.ams.discountservice.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
