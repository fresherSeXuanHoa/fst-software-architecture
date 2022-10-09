package fst.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fst.spring.jpa.entity.ChuyenBay;

@Repository
public interface ChuyenBayRepository extends JpaRepository<ChuyenBay, String> {
	@Query(nativeQuery = true, value="select * from chuyenbay where ga_den = :gaDen")
	public List<ChuyenBay> findByGaDen(@Param("gaDen") String gaDen);

	@Query(nativeQuery = true, value="select * from chuyenbay where do_dai > :minDoDai and do_dai < :maxDoDai")
	public List<ChuyenBay> findByDoDaiBetween(@Param("minDoDai") int minDoDai,@Param("maxDoDai") int maxDoDai);

	@Query(nativeQuery = true, value = "select * from chuyenbay where ga_di = :gaDi and ga_den = :gaDen")
	public List<ChuyenBay> findByGaDiGaDen(@Param("gaDi") String gaDi, @Param("gaDen") String gaDen);
	
	@Query(nativeQuery = true, value="select count(ma_cb) from chuyenbay where ga_di = :gaDi")
	public int countByGaDi(@Param("gaDi") String gaDi);
}
