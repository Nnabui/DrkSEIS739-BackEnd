package com.seis739.AbsoluteUnit;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seis739.AbsoluteUnit.model.Exercise;
import com.seis739.AbsoluteUnit.service.ExerciseService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@SpringBootApplication
public class AbsoluteunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbsoluteunitApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ExerciseService exerciseService) {
		return args -> {
			//read JSON and put it in database
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpUriRequest request = new HttpGet("https://api.api-ninjas.com/v1/exercises");
			request.addHeader("x-api-key", "0A/QWa2buxBA0nzkHrGLRA==mxZ8czATsCp72Q5Q");
			HttpResponse response = httpClient.execute(request);

			JSONArray jsonArray = new JSONArray(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
//			String context =  IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println(jsonArray);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Exercise exercise = new Exercise();
				// set the properties of the Exercise object based on the JSON data
				exercise.setName(jsonObject.getString("name"));
				exercise.setType(jsonObject.getString("type"));
				exercise.setMuscle(jsonObject.getString("muscle"));
				exercise.setEquipment(jsonObject.getString("equipment"));
				exercise.setDifficulty(jsonObject.getString("difficulty"));

				// save the Exercise object in the database using ExerciseService
				if (Math.toIntExact(exerciseService.countRepository()) == jsonArray.length()) {
					exerciseService.findAllExercises();
				} else {
					exerciseService.saveExercise(exercise);
				}
			}
		};

	}

	@Bean //Used to let front/back end applications access each others resources
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
