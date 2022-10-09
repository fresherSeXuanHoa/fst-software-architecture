package fst.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fst.spring.jpa.entity.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
	@Query(nativeQuery= true, value="select * from nhanvien where luong < :luong")
	public List<NhanVien> findByLuongLessThan(@Param("luong") int luong);

	@Query(nativeQuery = true, value="select sum(luong) from nhanvien")
	public int getTotalLuong();
}
