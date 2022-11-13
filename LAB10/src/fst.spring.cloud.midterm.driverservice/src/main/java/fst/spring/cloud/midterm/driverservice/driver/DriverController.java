package fst.spring.cloud.midterm.driverservice.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.cloud.midterm.driverservice.entity.Driver;
import fst.spring.cloud.midterm.driverservice.service.DriverService;

@RestController
public class DriverController {
	@Autowired
	DriverService driverService;

	@GetMapping("/drivers")
	public List<Object> findAll() {
		return driverService.findAll();
	}

	@GetMapping("/driver")
	public Driver findById(@RequestParam(name = "id") int id) {
		return driverService.findById(id);
	}

	@PostMapping("/driver")
	public Driver save(@RequestBody Driver driver) {
		return driverService.save(driver);
	}
}
