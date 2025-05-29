package gwt.xml.shared;

public interface IOption extends ICommon {
    static boolean has(int flags, int f) {
        return (flags & f) != 0;
    }

    static int remove(int flags, int f) {
        return flags & ~f;
    }

    default boolean hasFlag(int flags) {
        return has(flags, getFlag());
    }

    default int getFlag() {
        return 1 << ordinal();
    }

    default String toString(String value) {
        return Boolean.parseBoolean(value) ? Integer.toString(getFlag()) : EMPTY;
    }

    int ordinal();
}
