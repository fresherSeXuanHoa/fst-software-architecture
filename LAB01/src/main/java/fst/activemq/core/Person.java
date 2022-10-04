package fst.activemq.core;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings({ "restriction", "serial" })
@XmlRootElement
@XmlType(propOrder = { "rollNo", "name", "dayOfBirth" })
public class Person implements Serializable {
	private long rollNo;
	private String name;
	private Date dayOfBirth;

	public Person(long rollNo, String name, Date dayOfBirth) {
		this.rollNo = rollNo;
		this.name = name;
		this.dayOfBirth = dayOfBirth;
	}

	public Person() {
	}

	public long getrollNo() {
		return rollNo;
	}

	public void setrollNo(long rollNo) {
		this.rollNo = rollNo;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public Date getdayOfBirth() {
		return dayOfBirth;
	}

	public void setdayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	@Override
	public String toString() {
		return "Person [rollNo=" + rollNo + ", name=" + name + ", dayOfBirth=" + dayOfBirth + "]";
	}
}