package host.kuro.kurodiscord.lang;

import host.kuro.kurodiscord.utils.FileUtils;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Language {

	private static Map<String, String> lang;
    private static String encode;

    private Language() {
    }

    public static void load(String enc) {
        encode = enc;
        if (lang != null) {
            return;
        }
        try {
            lang = loadLang(Language.class.getClassLoader().getResourceAsStream("lang/lang.ini"));
        } catch (NullPointerException e) {
            lang = new HashMap<>();
        }
    }

    public static String translate(String str, Object... params) {
        String baseText = get(str);
        baseText = parseTranslation(baseText != null ? baseText : str);
        for (int i = 0; i < params.length; i++) {
            baseText = baseText.replace("{%" + i + "}", parseTranslation(String.valueOf(params[i])));
        }
        return baseText;
    }

    public static String translate(String str, String... params) {
        String baseText = get(str);
        baseText = parseTranslation(baseText != null ? baseText : str);
        for (int i = 0; i < params.length; i++) {
            baseText = baseText.replace("{%" + i + "}", parseTranslation(params[i]));
        }
        return baseText;
    }

    private static Map<String, String> loadLang(InputStream stream) {
        try {
            String content = FileUtils.readFile(stream, encode);
            Map<String, String> d = new HashMap<>();
            for (String line : content.split("\n")) {
                if (line.equals("") || line.charAt(0) == '#') {
                    continue;
                }
                int splitIndex = line.indexOf('=');
                if (splitIndex == -1) {
                    continue;
                }
                String key = line.substring(0, splitIndex);
                String value = line.substring(splitIndex + 1);

                d.put(key, value);
            }
            return d;
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
            return null;
        }
    }

    private static String internalGet(String id) {
        if (lang.containsKey(id)) {
            return lang.get(id);
        }
        return null;
    }

    private static String get(String id) {
        if (lang.containsKey(id)) {
            return lang.get(id);
        }
        return id;
    }

    private static String parseTranslation(String text) {
        StringBuilder newString = new StringBuilder();
        text = String.valueOf(text);

        StringBuilder replaceString = null;

        int len = text.length();

        for (int i = 0; i < len; ++i) {
            char c = text.charAt(i);
            if (replaceString != null) {
                int ord = c;
                if ((ord >= 0x30 && ord <= 0x39) // 0-9
                        || (ord >= 0x41 && ord <= 0x5a) // A-Z
                        || (ord >= 0x61 && ord <= 0x7a) || // a-z
                        c == '.' || c == '-') {
                    replaceString.append(String.valueOf(c));
                } else {
                    String t = internalGet(replaceString.substring(1));
                    if (t != null) {
                        newString.append(t);
                    } else {
                        newString.append(replaceString);
                    }
                    replaceString = null;
                    if (c == '%') {
                        replaceString = new StringBuilder(String.valueOf(c));
                    } else {
                        newString.append(String.valueOf(c));
                    }
                }
            } else if (c == '%') {
                replaceString = new StringBuilder(String.valueOf(c));
            } else {
                newString.append(String.valueOf(c));
            }
        }

        if (replaceString != null) {
            String t = internalGet(replaceString.substring(1));
            if (t != null) {
                newString.append(t);
            } else {
                newString.append(replaceString);
            }
        }
        return newString.toString();
    }
}

