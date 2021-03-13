public class HumanBeing {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Long impactSpeed; //Поле не может быть null
    private Integer minutesOfWaiting; //Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null



    public String getName(){
        return this.name;
    }
    public Long getId(){
        return this.id;
    }
    public HumanBeing(long id, String name, Coordinates coord, boolean RH, boolean htp, Long impactSpeed, WeaponType weaponType, Mood mood, Car car, int minutesOfWaiting){
        this.id = id;
        this.name = name;
        this.coordinates = coord;
        this.realHero = RH;
        this.hasToothpick = htp;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.minutesOfWaiting = minutesOfWaiting;
    }
    public Coordinates getCoordinates() { return this.coordinates; }
    public Long getImpactSpeed(){
        return this.impactSpeed;
    }
    public Integer getMinutesOfWaiting(){ return this.minutesOfWaiting; }
    public WeaponType getWeaponType() { return this.weaponType; }
    public Mood getMood(){
        return this.mood;
    }
    public Car getCar() { return this.car; }
    public boolean getRH() { return this.realHero; }
    public boolean getHTP() { return this.hasToothpick; }
}
