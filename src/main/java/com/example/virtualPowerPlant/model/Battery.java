package com.example.virtualPowerPlant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "battery")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Battery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "postcode", nullable = false)
	private int postcode;
	@Column(name = "wattCapacity", nullable = false)
	double wattCapacity;

	public Battery() {
	}

	public Battery(String name, int postcode, double wattCapacity) {
		super();
		this.name = name;
		this.postcode = postcode;
		this.wattCapacity = wattCapacity;
	}

}
