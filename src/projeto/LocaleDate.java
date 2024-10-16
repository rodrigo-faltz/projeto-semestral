package projeto;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocaleDate {

    private Calendar c = Calendar.getInstance();
    private Date data = c.getTime();

    public void printDate(Locale locale) throws ParseException {
        DateFormat sdf = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale);
        System.out.println(sdf.format(data));
    }
}
