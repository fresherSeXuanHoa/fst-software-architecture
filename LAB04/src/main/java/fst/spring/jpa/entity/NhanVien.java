package fst.spring.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "nhanvien")
public class NhanVien {
	
	@Id
	@Column(name = "ma_nv")
	private String maNhanVien;
	
	@Column(name = "ten")
	private String tenNhanVien;
	
	@Column(name = "luong")
	private String luong;
}
