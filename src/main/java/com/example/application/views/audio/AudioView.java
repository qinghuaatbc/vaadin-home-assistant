package com.example.application.views.audio;

import com.example.application.Domain.EntityId;
import com.example.application.Domain.Light;
import com.example.application.Domain.Person;
import com.example.application.Service.HassRestService;
import com.example.application.Service.ListService;
import com.example.application.Service.PersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@EnableScheduling
@PageTitle("Audio")
@Route(value = "Audio", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class AudioView extends VerticalLayout {

    private Label time;
    private Button lightOn;
    private Button lightOff;
    private Light[] lights;
    int count=0;
    @Autowired
    ListService listService;
    @Autowired
    HassRestService hassRestService;
    @Autowired
    PersonService personService;

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    ScheduledExecutorService executorServiceSche = Executors.newScheduledThreadPool(3);
    public AudioView() {

       time = new Label("Your name");
//        lightOn = new Button("Light ON",(e)->hassRestService.lightOn(new EntityId("light.hall_closet_ceiling_light")));
//        lightOff = new Button("Light Off",(e)->hassRestService.lightOff(new EntityId("light.hall_closet_ceiling_light")));


        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, time);



        add(time);


    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        UI ui = attachEvent.getUI();

        executorServiceSche.scheduleAtFixedRate(()->{
//            System.out.println("h");
//            time.setText(LocalDateTime.now().toString());

          ui.access(()->{

              time.setText(LocalDateTime.now().toString());

            });
        },1,1,TimeUnit.SECONDS);

        executorService.submit(()->{
          ui.access(()->{{


//                for(Light light: listService.getLights()){
//
//                    add(new Button(light.getName()+ "On",(e-> {
//                        hassRestService.lightOn(new EntityId(light.getEntity()));
//
//
//
//                    }
//                    )));
//                    add(new Button(light.getName()+ "Off",(e->hassRestService.lightOff(new EntityId(light.getEntity())))));
//
//                }
//
//                add(new Button("add",e->{
//                    personService.save(new Person("zheng wenjia",53));
//                }));
//               personService.findAll().forEach(e->{
//                   add(new Label(e.getName()));
//
//               });
            }});
        });


    }

//    @Scheduled(fixedRate = 1000)
//    public void execute() {
//        count++;
//
//        System.out.println("Hello"+count);
//
//
//    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        executorService.shutdown();
        executorServiceSche.shutdown();
    }
}
