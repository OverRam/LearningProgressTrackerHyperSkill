package tracker;

public enum LanguagesEnum {
    JAVA("Java"),
    DSA("DSA"),
    DATABASE("Databases"),
    SPRING("Spring");

    final String lang;

    LanguagesEnum(String lang) {
        this.lang = lang;
    }

    public String toString() {
        return this.lang;
    }
}
