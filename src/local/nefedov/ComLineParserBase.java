/**
 * Abstract class Command line parser <b>keys</b> and <b>delimeters</b>.
 * @autor Nefedov Ivan
 * @version 0.2.0
 */
package local.nefedov;

public abstract class ComLineParserBase {
    /** Field keys */
    String[] keys;
    /** Field delimeters */
    String[] delimeters;
    /** enum switchStatus{NoError, Error, ShowUsage} */
    public enum switchStatus {NoError, Error, ShowUsage}

    /**
     * Additional constructor
     * @see ComLineParserBase#ComLineParserBase(String[] keys)
     */
    public ComLineParserBase(String[] keys) {
        this(keys, new String[]{"-", "/"});
    }

    /**
     * Main constructor
     * Creating a new object with specific values
     * @see ComLineParserBase#ComLineParserBase(String[] keys, String[] delimeters)
     */
    public ComLineParserBase(String[] keys, String[] delimeters) {
        this.keys = keys;
        this.delimeters = delimeters;
    }

    /**
     * Function for checking correct delimiter and correct key {@link ComLineParserBase#parse(String[] args)}
     * @return returns false if there are no errors
     */
    public final boolean parse(String[] args) {
        // Бизнес логика по разбору командной строки
        switchStatus ss = switchStatus.NoError;
        int argNum;
        for (argNum = 0; (ss == switchStatus.NoError) && (argNum < args.length); argNum++) {
            // Провера наличия правильного разделителя
            boolean isDelimeter = false;
            for (int n = 0; !isDelimeter && (n < delimeters.length); n++) {
                isDelimeter = args[argNum].regionMatches(0, delimeters[n], 0, 1);
            }

            if (isDelimeter) {
                // Проверка наличия правильного ключа
                boolean isKey = false;
                int i;

                for (i = 0; !isKey && (i < keys.length); i++) {
                    isKey = args[argNum].toUpperCase().regionMatches(1, keys[i], 0, keys[i].length());
                    if (isKey) break;
                }

                if (!isKey) {
                    ss = switchStatus.Error;
                    break;
                } else {
                    ss = onSwitch(keys[i].toLowerCase(), args[argNum].substring(1 + keys[i].length()));
                }
            } else {
                ss = switchStatus.Error;
                break;
            }
        }

        // Завершение разбора командной строки
        if (ss == switchStatus.ShowUsage) onUsage(null);
        if (ss == switchStatus.Error) onUsage((argNum == args.length) ? null : args[argNum]);

        return ss == switchStatus.NoError;
    }

    /**
     * Function for checking correct delimiter and correct key {@link ComLineParserBase#parse(String[] args)}
     * @return returns false if there are no errors
     */
    public switchStatus onSwitch(String key, String keyValue) {
        // Отрабатывает команду для каждого найденного ключа
        return switchStatus.NoError;
    }

    public abstract void onUsage(String errorKey);

}
