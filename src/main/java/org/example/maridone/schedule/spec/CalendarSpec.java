package org.example.maridone.schedule.spec;


import org.example.maridone.schedule.calendar.CompanyCalendar;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class CalendarSpec {

    public static Specification<CompanyCalendar> isWithinMonth(int month, int year) {
        return (root, query, cb) -> {
            ZonedDateTime startOfMonth = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneOffset.UTC);
            ZonedDateTime endOfMonth = startOfMonth.plusMonths(1);

            Instant start = startOfMonth.toInstant();
            Instant end = endOfMonth.toInstant();
            return cb.and(
                    cb.greaterThanOrEqualTo(root.get("startDate"), start),
                    cb.lessThan(root.get("startDate"), end)
            );
        };
    }

    public static Specification<CompanyCalendar> isActive() {
        return (root, query, cb) -> cb.equal(root.get("isActive"), true);
    }
}
