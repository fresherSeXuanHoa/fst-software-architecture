package fst.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fst.spring.jpa.entity.NhanVien;
import fst.spring.jpa.repository.NhanVienRepository;

@Service
public class NhanVienService {
	private NhanVienRepository nhanVienRepository;

	public NhanVienService(NhanVienRepository nhanVienRepository) {
		super();
		this.nhanVienRepository = nhanVienRepository;
	}

	public List<NhanVien> findByLuongLessThan(int luong) {
		return nhanVienRepository.findByLuongLessThan(luong);
	}
	
	public int getTotalLuong() {
		return nhanVienRepository.getTotalLuong();
	}
}
