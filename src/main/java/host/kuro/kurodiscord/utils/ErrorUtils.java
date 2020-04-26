package host.kuro.kurodiscord.utils;

public class ErrorUtils {

    private static final int MAX_LENGTH = 2000;

    public static String GetErrorMessage(Exception ex) {
        StackTraceElement[] ste = ex.getStackTrace();
        String buff = "";
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element: ste) {
            sb.append("[");
            sb.append(element);
            sb.append("]\n");
        }
        buff = (ex.getClass().getName() + ": "+ ex.getMessage() + " -> " + new String(sb));
        if (buff.length() > MAX_LENGTH) {
            buff = buff.substring(0, MAX_LENGTH);
        }
        return buff;
    }
}
