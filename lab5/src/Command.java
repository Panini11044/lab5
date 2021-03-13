import com.sun.deploy.security.SelectableSecurityManager;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which work with collection
 */
public class Command {
    public HashSet<HumanBeing> hashSet = new HashSet<HumanBeing>();
    Comparator<HumanBeing> comparator = new Comparator<HumanBeing>(){
        @Override
        public int compare(HumanBeing o1, HumanBeing o2) {
            if (o2.getWeaponType().equals(o1.getWeaponType()))
                return 0;
            if (o1.getWeaponType().name.equals("NULL") && !o2.getWeaponType().name.equals("NULL"))
                return -1;
            else if (o1.getWeaponType().name.equals("AXE") && !o2.getWeaponType().name.equals("NULL") && !o2.getWeaponType().name.equals("AXE"))
                return -1;
            else if (o1.getWeaponType().name.equals("HAMMER") && !o2.getWeaponType().name.equals("NULL") && !o2.getWeaponType().name.equals("AXE") && !o2.getWeaponType().name.equals("HAMMER"))
                return -1;
            else if (o1.getWeaponType().name.equals("PISTOLE") && !o2.getWeaponType().name.equals("NULL") && !o2.getWeaponType().name.equals("AXE")&&  !o2.getWeaponType().name.equals("HAMMER") && !o2.getWeaponType().name.equals("PISTOL"))
                return -1;
            if (o1.getWeaponType().name.equals("RIFLE"))
                return 1;
            if (o2.getWeaponType().name.equals("NULL")&& !o1.getWeaponType().name.equals("NULL"))
                return 1;
            else if (o2.getWeaponType().name.equals("AXE") && !o1.getWeaponType().name.equals("NULL") && !o1.getWeaponType().name.equals("AXE"))
                return 1;
            else if (o2.getWeaponType().name.equals("HAMMER") && !o1.getWeaponType().name.equals("NULL") && !o1.getWeaponType().name.equals("AXE") && !o1.getWeaponType().name.equals("HAMMER"))
                return 1;
            else if (o2.getWeaponType().name.equals("PISTOLE") && !o1.getWeaponType().name.equals("NULL") && !o1.getWeaponType().name.equals("AXE")&&  !o1.getWeaponType().name.equals("HAMMER") && !o1.getWeaponType().name.equals("PISTOL"))
                return 1;
            if (o2.getWeaponType().name.equals("RIFLE"))
                return -1;
            return 0;
        }
    };

    /**
     * Returns collections. Use only for changing element by some field!!!
     *
     * @return
     */
    public HashSet<HumanBeing> getHashSet() {
        return this.hashSet;
    }

    /**
     * Show all elements from collection
     */
    public void show() {

        for(HumanBeing p : hashSet){

            System.out.println(p.getName());
        }
    }
    public boolean checkToEmpty(){
        return hashSet.isEmpty();
    }

    /**
     * Show info about collection(Type and size)
     */
    public void info() {
        System.out.println("Type of collection is " + hashSet.getClass().toString() + ". Size of collection is " + hashSet.size());
    }

    /**
     * Method adds <i>HumanBeing</i> to collection
     *
     * @param human
     */
    public void add(HumanBeing human) {
        hashSet.add(human);
    }

    /**
     * Method changes element with <i>id</i> to <i>vehicle</i>
     *
     * @param id
     * @param human
     */
    public void update(int id, HumanBeing human) {
        for (HumanBeing h : hashSet) {
            if (h.getId() == id){
                hashSet.remove(h);
                hashSet.add(human);
                break;
            }
        }
    }

    /**
     * Method removes element with <i>idToRemove</i>
     *
     * @param idToRemove
     */
    public void removeById(long idToRemove) {
        for (HumanBeing h : hashSet) {
            if (h.getId() == idToRemove){
                hashSet.remove(h);
                break;
            }
        }
    }
    public void minById() {
        long id = Long.MAX_VALUE;
        for (HumanBeing h : hashSet) {
            if (h.getId() <= id) {
                id = h.getId();
            }
        }
        for (HumanBeing h : hashSet) {
            if (h.getId() == id) {
                System.out.println(h.getName());
            }
        }
    }
    public void addIfMin(HumanBeing h){
        int flag = 1;
        for (HumanBeing p : this.hashSet){
            if(comparator.compare(h, p) == 1)
                flag = 0;
            else
                continue;

        }
        if(flag == 1){
            this.add(h);
            System.out.println("Element was added\n");
            Lab5.id++;
        }
    }
    public void removeLower(HumanBeing h){
        for (HumanBeing p : this.hashSet){
            if(comparator.compare(h,p) == -1)
                this.hashSet.remove(p);
        }
    }

    /**
     * Method returns max human by car
     */
    public void maxByCar(){
        Comparator<HumanBeing> c = new Comparator<HumanBeing>() {
            @Override
            public int compare(HumanBeing o1, HumanBeing o2) {

                if (o1.getCar().isCool() && o2.getCar().isCool())
                    return 0;
                if (o1.getCar().isCool() && !o2.getCar().isCool())
                    return 1;
                if (!o1.getCar().isCool() && o2.getCar().isCool())
                    return -1;
                return 0;
            }
        };
        int i = 0;
        for (HumanBeing p: hashSet ){
            i++;
        }
        HumanBeing[] h = new HumanBeing[i];
        int k = 0;
        for (HumanBeing p: hashSet ){
            h[k] = p;
            k++;
        }
        boolean flag = true;
        HumanBeing temp;

        while (flag) {
        flag = false;
            for (int j = 0; j < i-1; j++) {
                if (c.compare(h[j],h[j+1]) == 1) {
                    temp = h[j];
                    h[j] = h[j + 1];
                    h[j + 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(h[0].getName());

    }
    public void printAscending(){

        int i = 0;
        for (HumanBeing p: hashSet ){
            i++;
        }
        HumanBeing[] h = new HumanBeing[i];
        int k = 0;
        for (HumanBeing p: hashSet ){
            h[k] = p;
            k++;
        }

        boolean flag = true;
        HumanBeing temp;

        while (flag) {
            flag = false;
            for (int j = 0; j < h.length-1; j++) {
                if (comparator.compare(h[j],h[j+1]) == 1) {
                    temp = h[j];
                    h[j] = h[j + 1];
                    h[j + 1] = temp;
                    flag = true;
                }
            }
        }
        for (int f = 0; f < h.length;f++){
            System.out.println(h[f].getName());
        }

    }

    /**
     * Removes all element from collections
     */
    public void clear() {
        if (hashSet.isEmpty()) {
            System.out.println("Collection is already empty");
        } else {
            hashSet.clear();
            System.out.println("Collection is empty\n");
        }
    }
    public void save(String fileName) throws IOException {
        FileWriter fwOb = new FileWriter(fileName, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            if (!this.hashSet.isEmpty())
            for(HumanBeing p : this.hashSet){
                bw.write(p.getName()+",");
                bw.write(Long.toString(p.getCoordinates().getX()) + "," + Integer.toString(p.getCoordinates().getY()) + "," );
                bw.write(Boolean.toString(p.getRH()).toUpperCase() + ",");
                bw.write(Boolean.toString(p.getHTP()).toUpperCase() + ",");
                bw.write(Long.toString(p.getImpactSpeed()) + ",");
                bw.write(p.getWeaponType().name + ",");
                bw.write(p.getMood().name + ",");
                bw.write(p.getCar().getName() + ",");
                bw.write(Boolean.toString(p.getCar().isCool()).toUpperCase() + ",");
                bw.write(p.getMinutesOfWaiting()+"\n");
            }
        } catch (IOException e) {
            System.out.println("File was deleted\n");
        }
        pwOb.close();
        fwOb.close();
    }
    public boolean readScript(String scriptFile, String saveFile) throws IOException,
            IllegalArgumentException,IndexOutOfBoundsException{
        BufferedReader reader = new BufferedReader(new FileReader(scriptFile));
        String command;
        boolean check = true;
        while ((command = reader.readLine()) != null) {
            String[] commands = command.split(" ");
            if (commands[0].equals("help")) {
                Lab5.help();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("info")) {
                info();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("show")) {
                show();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("add")) {
                String name = reader.readLine();
                Long x = Long.parseLong(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                Coordinates coord = new Coordinates(x, y);
                boolean RH = reader.readLine() == "TRUE";
                boolean htp = reader.readLine() == "TRUE";
                Long impactspeed = Long.parseLong(reader.readLine());
                WeaponType weaponType = WeaponType.valueOf(reader.readLine());
                Mood mood = Mood.valueOf(reader.readLine());
                Car car = new Car(reader.readLine(), reader.readLine() == "TRUE");
                int minutesofwaiting = Integer.parseInt(reader.readLine());
                HumanBeing p = new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting);
                add(p);
                Lab5.id++;
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("update")) {
                int id = Integer.parseInt(commands[1]);
                String name = reader.readLine();
                Long x = Long.parseLong(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                Coordinates coord = new Coordinates(x, y);
                boolean RH = reader.readLine() == "TRUE";
                boolean htp = reader.readLine() == "TRUE";
                Long impactspeed = Long.parseLong(reader.readLine());
                WeaponType weaponType = WeaponType.valueOf(reader.readLine());
                Mood mood = Mood.valueOf(reader.readLine());
                Car car = new Car(reader.readLine(), reader.readLine() == "TRUE");
                int minutesofwaiting = Integer.parseInt(reader.readLine());
                HumanBeing p = new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting);
                update(id, p);
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("remove_by_id")) {
                int id = Integer.parseInt(commands[1]);
                removeById(id);
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("clear")) {
                clear();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("save")) {
                save(saveFile);
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("remove_lower")) {
                String name = reader.readLine();
                Long x = Long.parseLong(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                Coordinates coord = new Coordinates(x, y);
                boolean RH = reader.readLine() == "TRUE";
                boolean htp = reader.readLine() == "TRUE";
                Long impactspeed = Long.parseLong(reader.readLine());
                WeaponType weaponType = WeaponType.valueOf(reader.readLine());
                Mood mood = Mood.valueOf(reader.readLine());
                Car car = new Car(reader.readLine(), reader.readLine() == "TRUE");
                int minutesofwaiting = Integer.parseInt(reader.readLine());
                HumanBeing p = new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting);
                removeLower(p);
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("history")) {
                int j = 0;
                if (Lab5.k != 0){
                    for (int i = Lab5.history.length-1;i >= 0; i--){
                        if (Lab5.history[i] == null)
                            continue;
                        else
                        if (Lab5.history[i].equals("history") || Lab5.history[i].equals("save") || Lab5.history[i].equals("clear") || Lab5.history[i].equals("remove_by_id") || Lab5.history[i].equals("update") || Lab5.history[i].equals("show") || Lab5.history[i].equals("info") || Lab5.history[i].equals("add") || Lab5.history[i].equals("help")|| Lab5.history[i].equals("add_if_min") || Lab5.history[i].equals("remove_lower") || Lab5.history[i].equals("min_by_id") || Lab5.history[i].equals("max_by_car") || Lab5.history[i].equals("print_ascending"))
                            j++;
                        System.out.println(Lab5.history[i]);
                        if(j == 6)
                            break;
                    }
                }
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("add_if_min")) {
                String name = reader.readLine();
                Long x = Long.parseLong(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                Coordinates coord = new Coordinates(x, y);
                boolean RH = reader.readLine() == "TRUE";
                boolean htp = reader.readLine() == "TRUE";
                Long impactspeed = Long.parseLong(reader.readLine());
                WeaponType weaponType = WeaponType.valueOf(reader.readLine());
                Mood mood = Mood.valueOf(reader.readLine());
                Car car = new Car(reader.readLine(), reader.readLine() == "TRUE");
                int minutesofwaiting = Integer.parseInt(reader.readLine());
                HumanBeing p = new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting);
                addIfMin(p);
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("min_by_id")) {
                minById();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("max_by_car")) {
                maxByCar();
                Lab5.history[Lab5.k] = commands[0];
            } else if (commands[0].equals("print_ascending")) {
                printAscending();
                Lab5.history[Lab5.k] = commands[0];
            }else if (commands[0].equals("exit")){
                check = false;
            }
            Lab5.k++;
        }
        return check;
    }
}




