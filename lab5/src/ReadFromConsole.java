import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Class for reading
 */
public class ReadFromConsole {
    private static boolean chekByZero(String line) {
        boolean check = false;
        char[] chars = line.toCharArray();
        if (chars.length > 1 && !line.equals("-0")) {
            if (chars[0] == '0' && chars[1] != '.' || chars[0] == '-' && chars[1] == '0' && chars[2] != '.'){
                check = false;
            } else {
                check = true;
            }
        } else check = true;
        return check;
    }

    /**
     * Method gets new Vehicle from User
     *
     * @return Vehicle
     */
    public static HumanBeing getElementFromConsole(Scanner scanner) {
        String name;
        while (true) {
            System.out.println("Enter the name:");
            name = scanner.nextLine();
            if (!name.equals("")) {
                break;
            }
        }
        System.out.println("Enter X coordinate (X is long and < 624):");
        String x_check;
        long x = 0;
        while (true) {
            x_check = scanner.nextLine();
            if (chekByZero(x_check)) {
                x = Long.parseLong(x_check);
                if (x < 624) {
                    if (x >= Long.MIN_VALUE) {
                        break;
                    } else System.out.println("You tried to enter too big number");
                    ;
                } else System.out.println("X has to be long type and  < 624.Try again");
            } else
                System.out.println("You wrote '000000' or '-000000' or something same. If you want fill field like 0(1/etc),please write just 0(1/etc)");
        }
        System.out.println("Enter Y coordinate (Y is int):");
        String y_check;
        int y;
        while (true) {
            y_check = scanner.nextLine();
            try {
                if (chekByZero(y_check)) {
                    y = Integer.parseInt(y_check);
                    if (y<=192 && y > Integer.MIN_VALUE){
                        break;
                    }else System.out.println("You tried to enter too big or too small number");;
                } else System.out.println("You wrote '000000' or '-000000' or something same. If you want fill field like 0(1/etc),please write just 0(1/etc)");
            } catch (NumberFormatException e) {
                System.out.println("Y has to be double type.Try again");
            }
        }
        Coordinates coordinates = new Coordinates(x, y);
        System.out.println("Enter ImpactSpeed (ImpactSpeed is long and >0):");
        String impactspeed_check;
        long impactspeed;
        while (true) {
            impactspeed_check = scanner.nextLine();
            try {
                impactspeed = Long.parseLong(impactspeed_check);
                if (chekByZero(impactspeed_check)) {
                    if (impactspeed > 0) {
                        if (impactspeed<=Long.MAX_VALUE){
                            break;
                        }else System.out.println("You tried to enter too big number");;
                    } else throw new NumberFormatException();
                } else System.out.println("You wrote '000000' or '-000000' or something same. If you want fill field like 0(1/etc),please write just 0(1/etc)");
            } catch (NumberFormatException e) {
                System.out.println("Impact speed has to be float and > 0. Try again");
            }
        }
        System.out.println("Enter minutes of waiting (minutes of waiting is int and >0):");
        String mof_check;
        int mof;
        while (true) {
            mof_check = scanner.nextLine();
            try {
                if (chekByZero(mof_check)) {
                    mof = Integer.parseInt(mof_check);
                    if (mof > 0) {
                        break;
                    } else throw new NumberFormatException();
                } else System.out.println("You wrote '000000' or '-000000' or something same. If you want fill field like 0(1/etc),please write just 0(1/etc)");
            } catch (NumberFormatException e) {
                System.out.println("Minutes of waiting has to be int and > 0. Try again");
            }
        }
        System.out.println("Choose type of weapon:\nHAMMER\nAXE\nPISTOLE\nRIFLE");
        String weaponType_check = scanner.nextLine();
        WeaponType weaponType = WeaponType.valueOf("NULL");
        if (weaponType_check.equals("HAMMER")) {
            weaponType = WeaponType.HAMMER;
        }
        else if (weaponType_check.equals("AXE"))
            weaponType = WeaponType.AXE;
        else if (weaponType_check.equals("PISTOLE"))
            weaponType = WeaponType.PISTOLE;
        else if (weaponType_check.equals("RIFLE"))
            weaponType = WeaponType.RIFLE;
        else {
            System.out.println("You tried to enter incorrect data. The field will be null");
        }
        System.out.println("Choose type of Mood:\nSORROW\nAPATHY\nCALM\nFRENZY");
        Mood mood = null;
        while(mood == null)
        {
            String mood_check = scanner.nextLine();
            if (mood_check.equals("SORROW"))
                mood = Mood.SORROW;
            else if (mood_check.equals("APATHY"))
                mood = Mood.APATHY;
            else if (mood_check.equals("CALM"))
                mood = Mood.CALM;
            else if (mood_check.equals("FRENZY"))
                mood = Mood.FRENZY;
            else
                System.out.println("You tried to enter incorrect mood. Please try again\n");
        }
        System.out.println("Enter type of car (anything you want):");
        String nameofcar = scanner.nextLine();
        System.out.println("Is this car cool?(Answer Yes or No)");
        String cool = scanner.nextLine();
        Car car;
        if (cool =="Yes") {
            car = new Car(nameofcar, true);
        }else if (cool =="No"){
            car = new Car(nameofcar, false);
        }else{
            car = new Car(nameofcar, false);
        }

        HumanBeing p = new HumanBeing(Lab5.id, name, coordinates, true, false, impactspeed, weaponType, mood, car, mof);
        Lab5.id++;
        return p;
    }

    public static HumanBeing getElementFromConsoleToUpdate(Scanner scanner, int id) {
        HumanBeing h = getElementFromConsole(scanner);
        String name = h.getName();
        Coordinates coordinates = h.getCoordinates();
        long impactspeed = h.getImpactSpeed();
        int mof = h.getMinutesOfWaiting();
        boolean rh = h.getRH();
        boolean htp = h.getHTP();
        Mood mood = h.getMood();
        Car car = h.getCar();
        WeaponType w = h.getWeaponType();

        HumanBeing hToReturn = new HumanBeing(id, name, coordinates, rh, htp, impactspeed, w, mood, car, mof);
        return hToReturn;
    }


    /*public static boolean readScript(String scriptFile, String saveFile, MyCollection collection) throws IOException,
            IllegalArgumentException,IndexOutOfBoundsException, FileCycleException{
        BufferedReader reader = new BufferedReader(new FileReader(scriptFile));
        String command;
        boolean check = true;
        while ((command = reader.readLine()) != null) {
            String[] commands = command.split(" ");
            if (commands[0].equals("help")) {
                Main.printOut();
            } else if (commands[0].equals("info")) {
                collection.info();
            } else if (commands[0].equals("show")) {
                collection.show();
            } else if (commands[0].equals("add")) {
                String name = reader.readLine();
                Float x = Float.parseFloat(reader.readLine());
                Double y = Double.parseDouble(reader.readLine());
                Coordinates coordinates = new Coordinates(x, y);
                float enginePower = Float.parseFloat(reader.readLine());
                int capacity = Integer.parseInt(reader.readLine());
                String vehicleType = reader.readLine();
                String fuelType = reader.readLine();
                Vehicle vehicle = new Vehicle(name, coordinates, enginePower, capacity, VehicleType.valueOf(vehicleType), FuelType.valueOf(fuelType));
                collection.add(vehicle);
            } else if (commands[0].equals("update")) {
                int id = Integer.parseInt(commands[1]);
                String name = reader.readLine();
                Float x = Float.parseFloat(reader.readLine());
                Double y = Double.parseDouble(reader.readLine());
                Coordinates coordinates = new Coordinates(x, y);
                float enginePower = Float.parseFloat(reader.readLine());
                int capacity = Integer.parseInt(reader.readLine());
                String vehicleType = reader.readLine();
                String fuelType = reader.readLine();
                Vehicle vehicle = new Vehicle(name, coordinates, enginePower, capacity, VehicleType.valueOf(vehicleType), FuelType.valueOf(fuelType));
                collection.update(id, vehicle);
            } else if (commands[0].equals("remove_by_id")) {
                int id = Integer.parseInt(commands[1]);
                collection.removeById(id);
            } else if (commands[0].equals("clear")) {
                collection.clear();
            } else if (commands[0].equals("save")) {
                collection.save(saveFile);
            } else if (commands[0].equals("execute_script")) {
                String sName = commands[1];
                if (scriptFile.equals(sName)) throw new FileCycleException();
                readScript(sName, saveFile, collection);
            } else if (commands[0].equals("remove_first")) {
                collection.removeFirst();
            } else if (commands[0].equals("remove_head")) {
                collection.showFirst();
            } else if (commands[0].equals("add_if_max")) {
                String name = reader.readLine();
                Float x = Float.parseFloat(reader.readLine());
                Double y = Double.parseDouble(reader.readLine());
                Coordinates coordinates = new Coordinates(x, y);
                float enginePower = Float.parseFloat(reader.readLine());
                int capacity = Integer.parseInt(reader.readLine());
                String vehicleType = reader.readLine();
                String fuelType = reader.readLine();
                Vehicle vehicle = new Vehicle(name, coordinates, enginePower,
                        capacity, VehicleType.valueOf(vehicleType), FuelType.valueOf(fuelType));
                collection.addIfMax(vehicle);
            } else if (commands[0].equals("remove_any_by_fuel_type")) {
                String fuelType = commands[1];
                collection.removeByFuelType(fuelType);
            } else if (commands[0].equals("max_by_name")) {
                collection.getMaxName();
            } else if (commands[0].equals("group_counting_by_creation_date")) {
                Map<LocalDateTime, Integer> LocalDateMap = collection.groupByCreationDate();
                Set<LocalDateTime> keys = LocalDateMap.keySet();
                for (LocalDateTime key : keys) {
                    System.out.println("Creation date is " + key + " .The amounts is " + LocalDateMap.get(key));
                }
            }else if (commands[0].equals("exit")){
                check = false;
            }
        }
        return check;
    }*/
}