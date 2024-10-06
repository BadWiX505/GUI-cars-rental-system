package Tools;

import java.sql.Date;
import java.time.LocalDate;

public class OtherTools {

	public static Date getDateFromPeriode(Date date, int periode) {
        // Convert java.sql.Date to java.time.LocalDate
        LocalDate localDate = date.toLocalDate();

        // Add the specified number of days to the LocalDate
        LocalDate newLocalDate = localDate.plusDays(periode);

        // Convert back to java.sql.Date
        Date newDate = Date.valueOf(newLocalDate);

        return newDate;
    }
}
