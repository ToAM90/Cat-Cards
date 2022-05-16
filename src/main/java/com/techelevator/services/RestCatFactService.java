package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub

		String url = "https://cat-data.netlify.app/api/facts/random";

		try {
			CatFact fact = restTemplate.getForObject(url, CatFact.class);
			return fact;
		} catch (RestClientResponseException e){}

		return null;
	}

}
