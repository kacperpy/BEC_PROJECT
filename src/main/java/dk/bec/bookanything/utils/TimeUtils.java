package dk.bec.bookanything.utils;

import dk.bec.bookanything.model.DayOpenEntity;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class TimeUtils {

    public boolean isEqualOrAfter(LocalDateTime date1, LocalDateTime date2) {
        return date1.isEqual(date2) || date1.isAfter(date2);
    }

    public boolean isEqualOrBefore(LocalDateTime date1, LocalDateTime date2) {
        return date1.isEqual(date2) || date1.isBefore(date2);
    }

    public boolean isTimeEqualOrAfter(LocalTime time1, LocalTime time2) {
        return time1 == time2 || time1.isAfter(time2);
    }

    public boolean isTimeEqualOrBefore(LocalTime time1, LocalTime time2) {
        return time1 == time2 || time1.isBefore(time2);
    }
}
