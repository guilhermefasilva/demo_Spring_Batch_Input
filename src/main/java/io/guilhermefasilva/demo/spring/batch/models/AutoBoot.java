package io.guilhermefasilva.demo.spring.batch.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name = "tb_car")
public class AutoBoot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String car;
	
	public AutoBoot() { }
	
	public AutoBoot(String nome, String car) {
		
		this.name = nome;
		this.car = car;
	}
	

}
