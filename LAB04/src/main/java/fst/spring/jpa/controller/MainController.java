package fst.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.jpa.entity.ChuyenBay;
import fst.spring.jpa.entity.MayBay;
import fst.spring.jpa.entity.NhanVien;
import fst.spring.jpa.service.ChuyenBayService;
import fst.spring.jpa.service.MayBayService;
import fst.spring.jpa.service.NhanVienService;

@RestController
@RequestMapping("/")
public class MainController {
	@Autowired
	ChuyenBayService chuyenBayService;
	
	@Autowired
	MayBayService mayBayService;
	
	@Autowired
	NhanVienService nhanVienService;
	
	@GetMapping("/")
	public String index() {
		return  "<h1><a href='/01'>Cau 01</a></h1>" +
	            "<h1><a href='/02'>Cau 02</a></h1>" +
	            "<h1><a href='/03'>Cau 03</a></h1>" +
	            "<h1><a href='/04'>Cau 04</a></h1>" +
	            "<h1><a href='/05'>Cau 05</a></h1>" +
	            "<h1><a href='/06'>Cau 06</a></h1>" +
	            "<h1><a href='/07'>Cau 07</a></h1>" +
	            "<h1><a href='/08'>Cau 08</a></h1>" +
	            "<h1><a href='/09'>Cau 09</a></h1>" +
	            "<h1><a href='/10'>Cau 10</a></h1>";
	}
	
	@GetMapping("/01")
	public List<ChuyenBay> findByGaDen() {
		return chuyenBayService.findByGaDen("DAD");
	}
	
	@GetMapping("/02")
	public List<MayBay> findByTamBayMoreThan() {
		return mayBayService.findByTamBayMoreThan(10000);
	}
	
	@GetMapping("/03")
	public List<NhanVien> findByLuongLessThan() {
		return nhanVienService.findByLuongLessThan(10000);
	}
	
	@GetMapping("/04")
	public List<ChuyenBay> findByDoDaiBetween() {
		return chuyenBayService.findByDoDaiBetween(8000, 10000);
	}
	
	@GetMapping("/05")
	public List<ChuyenBay> findByGaDiGaDen() {
		return chuyenBayService.findByGaDiGaDen("SGN", "BMV");
	}
	
	@GetMapping("/06")
	public int countByGaDi() {
		return chuyenBayService.countByGaDi("SGN");
	}
	
	@GetMapping("/07")
	public int countByLoai() {
		return mayBayService.countByLoai("Boeing");
	}
	
	@GetMapping("/08")
	public int getTotalLuong() {
		return nhanVienService.getTotalLuong();
	}
}
