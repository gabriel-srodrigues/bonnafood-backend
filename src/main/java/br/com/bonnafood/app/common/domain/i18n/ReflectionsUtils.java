package br.com.bonnafood.app.common.domain.i18n;

import br.com.bonnafood.app.common.domain.enums.EnumListable;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionsUtils {
    private static final Map<String, Map<String, String>> allEnums = new HashMap<>();

    public static Set<Class<?>> getAllImplementationsFromInterface(Class clazz) {
        Reflections reflections = new Reflections("br.com.bonnafood", new Scanner[0]);
        return reflections.getSubTypesOf(clazz);
    }


    public static Map<String, String> listI18nKey(Locale locale, Class javaEnum) {
        Map<String, String> enumByLanguage = I18nUtils.getMapValues(javaEnum, locale);
        enumByLanguage = enumByLanguage.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, second) -> second,
                        LinkedHashMap::new));
        return enumByLanguage;
    }

    public static Map<String, Map<String, String>> listAllEnumsByLocale(Locale locale) {
        if (allEnums.isEmpty()) {
            Set<Class<?>> enumClasses = getAllImplementationsFromInterface(EnumListable.class);

            for (Class<?> aClass : enumClasses) {
                Map<String, String> enums = new HashMap<>(listI18nKey(locale, aClass));
                allEnums.put((aClass).getSimpleName(), enums);
            }
        }

        return allEnums;
    }
}
