package com.example.application.views.video;

import com.example.application.Domain.Video;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Video")
@Route(value = "Video", layout = MainLayout.class)
public class VideoView extends VerticalLayout {
    public VideoView() {

        Video video = new Video("http://24.80.132.186:5081/LiveApp/streams/790959164419468023195632.mp4");
        video.getStyle().set("margin","auto");
        video.getStyle().set("width","80%");
        video.getStyle().set("height","auto");
        add(video);

    }

}
