package gwt.xml.shared;

public class CommonS {
    private static final ICommon instance = new ICommon() {
    };

    public static String before(String source, char c) {
        return instance.before(source, c);
    }

    private CommonS() {
    }
}
