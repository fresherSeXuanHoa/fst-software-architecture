package fst.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fst.spring.jpa.entity.ChuyenBay;
import fst.spring.jpa.repository.ChuyenBayRepository;

@Service
public class ChuyenBayService {
	private ChuyenBayRepository chuyenBayRepository;

	public ChuyenBayService(ChuyenBayRepository chuyenBayRepository) {
		super();
		this.chuyenBayRepository = chuyenBayRepository;
	}
	
	public List<ChuyenBay> findByGaDen(String gaDen) {
		return chuyenBayRepository.findByGaDen(gaDen);
	}
	
	public List<ChuyenBay> findByDoDaiBetween(int minDoDai, int maxDoDai) {
		return chuyenBayRepository.findByDoDaiBetween(minDoDai, maxDoDai);
	}
	
	public List<ChuyenBay> findByGaDiGaDen(String gaDi, String gaDen) {
		return chuyenBayRepository.findByGaDiGaDen(gaDi, gaDen);
	}
	
	public int countByGaDi(String gaDi) {
		return chuyenBayRepository.countByGaDi(gaDi);
	}
}
