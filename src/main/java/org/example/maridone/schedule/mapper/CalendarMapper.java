package org.example.maridone.schedule.mapper;

import org.example.maridone.schedule.calendar.CompanyCalendar;
import org.example.maridone.schedule.dto.CalendarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

    CompanyCalendar toCalendarDto(CalendarDto calendarDto);
}
