package local.nefedov.comLineParser;

public class SimpleParser extends ComLineParserBase {
    private String inFile, outFile;

    public SimpleParser(String[] keys) {
        super(keys);
    }

    public SimpleParser(String[] keys, String[] delimeters) {
        super(keys, delimeters);
    }

    public String getInFile() {
        return inFile;
    }

    public String getOutFile() {
        return outFile;
    }

    @Override
    public void onUsage(String errorKey) {
        // Выводит подсказку с форматом командной строки
        if (errorKey != null) {
            System.out.println(errorKey);
            System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
            System.out.println("   -?  показать Help файл");
            System.out.println("   -r  задать имя входного файла");
            System.out.println("   -w  выполнить вывод в указанный файл");
        }
    }

    /**
     *
     */
    @Override
    public switchStatus onSwitch(String key, String keyValue) {
        switchStatus status = switchStatus.NoError;

        switch (key) {
            case ("?"):
                status = switchStatus.ShowUsage;
                break;
            case ("r"):
                if (keyValue != null) {
                    inFile = keyValue;
                } else status = switchStatus.Error;
                break;
            case ("w"):
                if (keyValue != null) {
                    outFile = keyValue;
                } else status = switchStatus.Error;
            default:
                status = switchStatus.ShowUsage;
        }
        return status;
    }
}
