package org.example.maridone.schedule.calendar;

import org.example.maridone.schedule.shift.ShiftRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final ShiftRepository shiftRepository;

    public CalendarService(
            CalendarRepository calendarRepository,
            ShiftRepository shiftRepository)
    {
        this.calendarRepository = calendarRepository;
        this.shiftRepository = shiftRepository;
    }
}
