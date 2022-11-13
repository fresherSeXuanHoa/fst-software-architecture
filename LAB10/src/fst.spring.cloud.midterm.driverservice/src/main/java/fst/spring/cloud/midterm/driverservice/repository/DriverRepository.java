package fst.spring.cloud.midterm.driverservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fst.spring.cloud.midterm.driverservice.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
