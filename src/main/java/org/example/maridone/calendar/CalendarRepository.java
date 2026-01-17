package org.example.maridone.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarRepository extends JpaRepository<CompanyCalendar,Long> {

}
