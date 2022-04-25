package com.example.application.views.light;

import com.example.application.Domain.EntityId;
import com.example.application.Domain.Light;
import com.example.application.Domain.ReturnStateObject;
import com.example.application.Service.HassRestService;
import com.example.application.Service.ListService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@PageTitle("Light")
@Route(value = "Light", layout = MainLayout.class)
public class LightView extends VerticalLayout {
    ScheduledExecutorService executorServiceImage = Executors.newScheduledThreadPool(3);
    ScheduledExecutorService executorServiceState = Executors.newScheduledThreadPool(3);
    ExecutorService executorService = Executors.newFixedThreadPool(1);


    @Autowired
    HassRestService hassRestService;

    @Autowired
    ListService listService;

    Light[] lights;
    HashMap<Light,Image> lightImageHashMap = new HashMap<>();
    Div bg;

    public LightView() {
      //  getStyle().set("background-color","green");

        bg = new Div();
        bg.getStyle().set("width","1024px");
        bg.getStyle().set("height","636px");
       // bg.getStyle().set("background-image","url('http://stevenjbarnes.com/wp-content/uploads/revslider/splash-creative-light-01-animated/Slider-CL01-Background.png')");
        bg.getStyle().set("background-image","url('images/3541w291024X636.png')");

        bg.getStyle().set("margin","auto");
        add(bg);


//        bg.add(b1,b2);

//        Button b = new Button("state");
//        b.addClickListener((e)->{{
//            ReturnStateObject returnStateObject = hassRestService.getState("light.hall_closet_ceiling_light");
//            Notification.show(returnStateObject.getState());
//
//        }});
//        bg.add(b);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        UI ui = attachEvent.getUI();

        lights = listService.getLights();


        executorService.submit(()->{
         ui.access(()->{

             for(Light light : lights){

                    Image img = new Image("icons/lightOff60X60.png","");
                 img.getStyle().set("position","relative");
                 img.getStyle().set("top",String.valueOf(light.getTop())+"px");
                 img.getStyle().set("left",String.valueOf(light.getLeft())+"px");
                 lightImageHashMap.put(light,img);

                        img.addClickListener(e->{


           if (light.isSw())
           {
               hassRestService.lightOff(new EntityId(light.getEntity()));
              img.setSrc("icons/lightOff60X60.png");
           light.setSw(false);
           }
           else

           {
               hassRestService.lightOn(new EntityId(light.getEntity()));
             img.setSrc("icons/lightOn60X60.png");
              light.setSw(true);
           }
       });



                 bg.add(img);


             }


         });

        });

/////////////////////////////
        executorServiceImage.scheduleAtFixedRate(()->{
            ui.access(()->{

                lightImageHashMap.forEach((l,i)->{
                    if (l.isSw())
                        i.setSrc("icons/lightOn60X60.png");
                    else
                        i.setSrc("icons/lightOff60X60.png");
                });



            });

        },0,1,TimeUnit.SECONDS);

        /////////////////////////////
        executorServiceState.scheduleAtFixedRate(()->{


                for (Light light : lights) {
                    ReturnStateObject r = hassRestService.getState(light.getEntity());
                    if (r.getState().equals("on"))
                        light.setSw(true);
                    else
                        light.setSw(false);
                }





        },0,1,TimeUnit.SECONDS);



    }



    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        executorService.shutdown();
        executorServiceImage.shutdown();
    }
}
