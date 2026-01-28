package org.example.maridone.schedule.calendar;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    private final CalendarService calendarService;
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

}
