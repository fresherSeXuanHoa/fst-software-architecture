package fst.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fst.spring.jpa.entity.MayBay;

@Repository
public interface MayBayRepository extends JpaRepository<MayBay, Integer> {
	@Query(nativeQuery = true, value="select * from maybay where tam_bay > :tamBay")
	public List<MayBay> findByTamBayMoreThan(@Param("tamBay") int tamBay);

	@Query(nativeQuery = true, value="select count(ma_mb) from maybay where loai like :loai" + "%")
	public int countByLoai(@Param("loai") String loai);
}
