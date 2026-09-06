package com.mini.project.dto;

import com.mini.project.entities.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EventDTO {
    @Positive
    private Long eventId;

    @NotBlank(message = "Event title cannot be blank")
    @Size(min = 3, max = 100, message = "Event title has to be between 3 and 100 characters")
    private String eventTitle;

    private Date eventDate;

    @NotBlank(message = "Event description cannot be blank")
    @Size(min = 5, max = 300, message = "Event description has to be between 5 and 300 characters")
    private String eventDescription;


    public static EventDTO convertToDTO(Event entity) {
        EventDTO dto = EventDTO.builder()
                .eventId(entity.getId())
                .eventTitle(entity.getTitle())
                .eventDate(entity.getEventDate())
                .eventDescription(entity.getDescription())
                .build();
        return dto;
    }

    public static List<EventDTO> convertToDTO(List<Event> entityList) {
        List<EventDTO> dtos = new ArrayList<>();
        for (Event e : entityList) {
            dtos.add(convertToDTO(e));
        }
        return dtos;
    }
}
