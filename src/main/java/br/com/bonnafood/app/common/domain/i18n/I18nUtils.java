package br.com.bonnafood.app.common.domain.i18n;

import br.com.bonnafood.app.common.domain.enums.EnumListable;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class I18nUtils {
    public static String getText(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            return bundle.getString(key);
        } catch (MissingResourceException var4) {
            return "NOT_FOUND: " + key;
        }
    }

    public static <E extends Enum<E>> Map<String, String> getMapValues(Class<E> enumClass, Locale loc) {
        EnumSet<E> eSet = EnumSet.allOf(enumClass);
        Map<String, String> result = new ConcurrentHashMap<>(eSet.size());
        Iterator<E> var4 = eSet.iterator();

        while(var4.hasNext()) {
            E eValue = var4.next();
            if (eValue instanceof EnumListable e) {
                result.put(eValue.toString(), getText(e.getI18nKey(), loc));
            } else {
                result.put(eValue.toString(), eValue.toString());
            }
        }

        return result;
    }
}
