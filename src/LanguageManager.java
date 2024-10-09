import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static Locale currentLocale = new Locale("pt", "BR"); // Idioma padrão

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("messages", currentLocale);
    }
}
