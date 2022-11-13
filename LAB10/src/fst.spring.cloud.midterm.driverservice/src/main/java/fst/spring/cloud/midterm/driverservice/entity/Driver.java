package fst.spring.cloud.midterm.driverservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drive")
public class Driver implements Serializable {

	private static final long serialVersionUID = -5566053777071917253L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "distance")
	private double distance;
}
