package projeto;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocaleDate {

    private Calendar c = Calendar.getInstance();
    private Date data = c.getTime();
    private Locale localeDefault = Locale.JAPAN;
    // Modify this method to print only day, month, and year
    public void printDate(Locale locale) {
        // Use getDateInstance to format only the date (no time)
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        System.out.println(sdf.format(data));
    }
    public String dateString() {
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT, localeDefault);
        return sdf.format(data).replace("/", "-");
    }
}
