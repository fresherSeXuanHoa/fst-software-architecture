package fst.spring.jpa.entity;

import java.util.Date;

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
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chuyenbay")
public class ChuyenBay {
	
	@Id
	@Column(name = "ma_cb")
	private String maChuyenBay;
	
	@Column(name = "ga_di")
	private String gaDi;
	
	@Column(name = "ga_den")
	private String gaDen;
	
	@Column(name = "do_dai")
	private int doDai;
	
	@Column(name = "gio_di")
	private Date gioDi;
	
	@Column(name = "gio_den")
	private Date gioDen;
	
	@Column(name = "chi_phi")
	private int chiPhi;
}
