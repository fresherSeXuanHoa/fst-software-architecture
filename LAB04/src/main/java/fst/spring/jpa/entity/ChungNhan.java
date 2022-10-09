package fst.spring.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chungnhan")
public class ChungNhan {
	
	@Id
	@Column(name = "ma_nv")
	private String maNhanVien;
	
	@Column(name = "ma_mb")
	private int maChuyenBay;
}
