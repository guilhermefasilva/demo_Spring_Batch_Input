package io.guilhermefasilva.demo.spring.batch.service;

import org.springframework.batch.item.ItemProcessor;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoBootItemProcessor implements ItemProcessor<AutoBoot, AutoBoot> {

	@Override
	public AutoBoot process(AutoBoot autoboot) throws Exception {
			final String firstName = autoboot.getName().toUpperCase();
			final String lastName = autoboot.getCar().toUpperCase();
			final AutoBoot transformed = new AutoBoot(firstName, lastName);
			log.info("Converting ( {}",autoboot," ) into ( {}",transformed," )");
			
		
		return transformed;
	}

}
