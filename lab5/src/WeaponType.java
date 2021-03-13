/**
 * Human's weapon
 */
public enum WeaponType {
    AXE("AXE"),
    HAMMER("HAMMER"),
    PISTOLE("PISTOLE"),
    RIFLE("RIFLE"),
    NULL("NULL");
    public String name = "NULL";
     WeaponType(String name) {
         this.name = name;
    }
}
