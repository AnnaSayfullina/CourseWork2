public enum Type {
    WORK("рабочая"),
    PERSONAL("личная");
    private String type;

    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Тип: " + type;
    }
}
