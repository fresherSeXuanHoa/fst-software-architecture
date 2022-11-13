package fst.spring.cloud.midterm.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fst.spring.cloud.midterm.customerservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
