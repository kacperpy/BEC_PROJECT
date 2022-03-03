package dk.bec.bookanything.mapper;

import org.springframework.stereotype.Component;

@Component
public class WeekDayNumberToNameMapper {
    private final static String MONDAY = "Monday";
    private final static String TUESDAY = "Tuesday";
    private final static String WEDNESDAY = "Wednesday";
    private final static String THURSDAY = "Thursday";
    private final static String FRIDAY = "Friday";
    private final static String SATURDAY = "Saturday";
    private final static String SUNDAY= "Sunday";
    private final static String DEFINITION_NOT_FOUND_MSG = "Definition not available";

    public static String mapDayNumberToName(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
        }
        return DEFINITION_NOT_FOUND_MSG;
    }
}
