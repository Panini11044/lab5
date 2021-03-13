
import java.io.*;

/**
 * Class for reading from the file
 */
public class ReadCVS {
    static File fileWay;
    static boolean checkEnvironmentVariable = true;


    //ReadCVS obj = new ReadCVS();
    // obj.run();


    /**
     * Method reads the file
     * @param c
     * @throws IOException
     */
    public void runWithEnv(Command c) throws IOException {
        if (checkEnvironmentVariable) {
            fileWay = new File(System.getenv("enV"));
        }
        if (!fileWay.exists()) {
            System.out.println("File doesn't exist");
            throw new FileNotFoundException();
        }
        if (!fileWay.canWrite()) {
            System.out.println("Permission denied");
            throw new FileNotFoundException();
        }
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";


        br = new BufferedReader(new FileReader(fileWay));
        while ((line = br.readLine()) != null) {
            WeaponType weaponType;
            Mood mood;
            Coordinates coord = null;
            String[] human = line.split(cvsSplitBy);
            String name = human[0];
            try {
                coord = new Coordinates(Long.parseLong(human[1]), Integer.parseInt(human[2]));
            } catch (NumberFormatException e) {
                System.out.println("Illegal element\n");
            }
            boolean RH = human[3] == "TRUE";
            boolean htp = human[4] == "TRUE";
            Long impactspeed = Long.parseLong(human[5]);
            try {
                weaponType = WeaponType.valueOf(human[6]);
                mood = Mood.valueOf(human[7]);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal argument");
                continue;
            }
            Car car = new Car(human[8], human[9] == "TRUE");
            int minutesofwaiting = 0;
            try{
                minutesofwaiting = Integer.parseInt(human[10]);
            } catch (NumberFormatException e) {
                System.out.println("Illegal element\n");
            }
            c.hashSet.add(new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting));
            Lab5.id++;
        }
    }

    /**
     * Method for read file
     * @param c
     * @param filename
     * @throws IOException
     */
    public void run(Command c, String filename) throws IOException {

        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";


        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            WeaponType weaponType;
            Mood mood;
            Coordinates coord = null;
            String[] human = line.split(cvsSplitBy);
            String name = human[0];
            try {
                coord = new Coordinates(Long.parseLong(human[1]), Integer.parseInt(human[2]));
            } catch (NumberFormatException e) {
                System.out.println("Illegal element\n");
            }
            boolean RH = human[3] == "TRUE";
            boolean htp = human[4] == "TRUE";
            Long impactspeed = Long.parseLong(human[5]);
            try {
                weaponType = WeaponType.valueOf(human[6]);
                mood = Mood.valueOf(human[7]);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal argument");
                continue;
            }
            Car car = new Car(human[8], human[9] == "TRUE");
            int minutesofwaiting = 0;
            try{
                minutesofwaiting = Integer.parseInt(human[10]);
            } catch (NumberFormatException e) {
                System.out.println("Illegal element\n");
            }
            c.hashSet.add(new HumanBeing(Lab5.id, name, coord, RH, htp, impactspeed, weaponType, mood, car, minutesofwaiting));
            Lab5.id++;
        }
    }
}