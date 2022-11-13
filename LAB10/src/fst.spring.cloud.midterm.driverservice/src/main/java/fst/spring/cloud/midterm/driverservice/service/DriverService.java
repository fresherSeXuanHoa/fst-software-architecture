package fst.spring.cloud.midterm.driverservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import fst.spring.cloud.midterm.driverservice.entity.Driver;
import fst.spring.cloud.midterm.driverservice.repository.DriverRepository;

@Service
public class DriverService {
	private static final String HASH_KEY = "Driver";

	@Autowired
	DriverRepository driverRepository;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	public List<Object> findAll() {
		return redisTemplate.opsForHash().values(HASH_KEY);
	}

	public Driver findById(int id) {
		return driverRepository.findById(id).get();
	}

	public Driver save(Driver driver) {
		redisTemplate.opsForHash().put(HASH_KEY, driver.getId(), driver);
		return driverRepository.save(driver);
	}
}
