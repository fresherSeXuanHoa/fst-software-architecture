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
@NoArgsConstructor
@ToString
@Entity
@Table(name = "maybay")
public class MayBay {
	
	@Id
	@Column(name = "ma_mb")
	private int maMayBay;
	
	@Column(name = "loai")
	private String loaiMayBay;
	
	@Column(name = "tam_bay")
	private int tamBay;
}
