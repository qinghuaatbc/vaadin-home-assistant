package com.example.application.Service;

import com.example.application.Domain.Light;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ListService {

    WebClient webClient;

    public ListService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://vaadin-b55f5-default-rtdb.firebaseio.com")

                .build();
    }

    public Light[] getLights(){
      return   this.webClient.get()
                .uri("/hass/lights.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Light[].class)
                .block();


    }

}
