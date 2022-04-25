package com.example.application.Service;

import com.example.application.Domain.EntityId;
import com.example.application.Domain.ReturnStateObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class HassRestService {

    WebClient webClient;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxMjc0ZTBkMTAyNjM0MWJhYWM0MTRhMjZmOGRhM2JhNyIsImlhdCI6MTY1MDE0OTYxMywiZXhwIjoxOTY1NTA5NjEzfQ.hT8ZbbjPOrH5KD6fFnPc1kJ_gu_E6npLSJNfaUqLqWM";

    public HassRestService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://192.168.1.209:8123")

                .build();
    }

    public void lightOn(EntityId id){
        this.webClient.post()
                .uri("/api/services/light/turn_on")
                .headers(h->h.setBearerAuth(token))

                .contentType(MediaType.APPLICATION_JSON)

              .body(Mono.just(id),EntityId.class)
                .retrieve()
            //    .toBodilessEntity();
                .bodyToMono(Object[].class)
                .block();


    }
    public void lightOff(EntityId  id){
      //  EntityId entityId = new EntityId("light.hall_closet_ceiling_light");
        this.webClient.post()
                .uri("/api/services/light/turn_off")
                .headers(h->h.setBearerAuth(token))

                .contentType(MediaType.APPLICATION_JSON)

                .body(Mono.just(id),EntityId.class)
                .retrieve()
                //    .toBodilessEntity();
                .bodyToMono(Object[].class)
                .block();


    }
     public ReturnStateObject getState(String entity)  {
         return this.webClient.get()
                 .uri("/api/states/"+entity)
                 .headers(h->h.setBearerAuth(token))

               //  .contentType(MediaType.APPLICATION_JSON)

                // .body(Mono.just(id),EntityId.class)
                 .retrieve()
                 //    .toBodilessEntity();
                 .bodyToMono(ReturnStateObject.class)
                 .block();


     }
}
