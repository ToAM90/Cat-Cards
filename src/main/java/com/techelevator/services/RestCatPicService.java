package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		// TODO Auto-generated method stub
		String url = "https://cat-data.netlify.app/api/pictures/random";

//		try {
			CatPic picture = restTemplate.getForObject(url, CatPic.class);
			return picture;
//		} catch (RestClientResponseException ex){
//
//		} return null;
	}

}	
