package com.siddhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.tutorial.protos.Person;
import com.example.tutorial.protos.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@RestController
public class ConsumeWebController {

	@Autowired
	RestTemplate restTemplate;

	@JsonSerialize(using = ProtobufSerializer.class)
	private Person.Builder builder = Person.newBuilder();


	// GET Method - To Select the data.
	@RequestMapping(value = "/person")
	public String getPerson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("http://localhost:8081/person", HttpMethod.GET, entity, String.class).getBody();
	}

	// POST Method :- To insert the data.
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public String createPerson(@RequestBody PersonModel personModel) {
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(personModel,headers);
		return restTemplate.exchange("http://localhost:8081/person", HttpMethod.POST, entity, String.class).getBody();
	}

	// PUT Method :- To update the data.
	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
	public String updatePerson(@PathVariable("id") String id, @RequestBody PersonModel personModel) {
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(personModel,headers);
		return restTemplate.exchange("http://localhost:8081/person/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}


	// DELETE Method :- To delete the data.
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(headers);
		return restTemplate.exchange("http://localhost:8081/person/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	}
	

}
