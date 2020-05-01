package host.kuro.kurodiscord.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static final boolean isHankakuEisu(String target) {
        target.replace("_", "");
        return Pattern.matches("^[0-9a-zA-Z]+$", target);
    }
}
