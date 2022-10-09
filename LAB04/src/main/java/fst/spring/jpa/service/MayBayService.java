package fst.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fst.spring.jpa.entity.MayBay;
import fst.spring.jpa.repository.MayBayRepository;

@Service
public class MayBayService {
	private MayBayRepository mayBayRepository;

	public MayBayService(MayBayRepository mayBayRepository) {
		super();
		this.mayBayRepository = mayBayRepository;
	}
	
	public List<MayBay> findByTamBayMoreThan(int tamBay) {
		return mayBayRepository.findByTamBayMoreThan(tamBay);
	}
	
	public int countByLoai(String loai) {
		return mayBayRepository.countByLoai(loai);
	}
}
