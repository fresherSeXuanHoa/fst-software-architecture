package fst.spring.cloud.midterm.customerservice.entity;

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
@Table(name = "customer")
public class Customer {
	@Id
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;
}
