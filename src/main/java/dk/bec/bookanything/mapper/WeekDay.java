package dk.bec.bookanything.mapper;

public enum WeekDay {
    MONDAY(1, "Monday"),
    TUESDAY(2, "Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4, "Thursday"),
    FRIDAY(5, "Friday"),
    SATURDAY(6, "Saturday"),
    SUNDAY(7, "Sunday");

    private final int dayNumber;
    private final String dayName;
    private final static String DEFINITION_NOT_FOUND_MSG = "Definition not available";

    WeekDay(int dayNumber, String dayName) {
        this.dayNumber = dayNumber;
        this.dayName = dayName;
    }

    public static String getDayNameFromDayNumber(int dayNumber) {
        for (WeekDay weekDay : WeekDay.values()) {
            if (weekDay.dayNumber == dayNumber)
                return weekDay.dayName;
        }
        return DEFINITION_NOT_FOUND_MSG;
    }
}
