package island.util;

public enum EntityType {
    WOLF("🐺"),
    BOA("🐍"),
    FOX("🦊"),
    BEAR("🐻"),
    EAGLE("🦅"),
    HORSE("🐎"),
    DEER("🦌"),
    RABBIT("🐇"),
    MOUSE("🐁"),
    GOAT("🐐"),
    SHEEP("🐑"),
    BOAR("🐗"),
    BUFFALO("🐃"),
    DUCK("🦆"),
    CATERPILLAR("🐛"),
    PLANT("🌿");

    private final String icon;

    EntityType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}