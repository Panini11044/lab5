import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Main class with command
 */
public class Lab5 {
    private static final Scanner scanner = new Scanner(System.in);
    public static long id = 1;
    public static String[] history = new String[100000];
    public static int k = 0;
    public static void main(String args[]) {
        help();
        String fileName;
        ReadCVS r = new ReadCVS();
        Command c = new Command();

        while (true) {
            try {
                r.runWithEnv(c);
                fileName = ReadCVS.fileWay.getName();
                break;
            } catch (FileNotFoundException e) {
                System.out.println("This file doesn't exist. Enter file from console");
                System.out.println("Enter a file's name for starting");
                fileName = scanner.nextLine();
                try {
                    r.run(c, fileName);
                    break;
                } catch (IOException e1) {
                    System.out.println("Something went wrong with reading file");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong with reading data from file. Enter file from console");
                System.out.println("Enter a file's name for starting");
                fileName = scanner.nextLine();
                try {
                    r.run(c, fileName);
                    break;
                } catch (IOException e2) {
                    System.out.println("Something went wrong with reading file");
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("You didn't enter a input file");
                System.out.println("Enter a file's name for starting");
                fileName = scanner.nextLine();
                try {
                    r.run(c, fileName);
                    break;
                } catch (IOException e3) {
                    System.out.println("Something went wrong with reading file");
                }
            } catch (NullPointerException e) {
                System.out.println("This file doesn't exist. Enter file from console");
                System.out.println("Enter a file's name for starting");
                fileName = scanner.nextLine();
                try {
                    r.run(c, fileName);
                    break;
                } catch (IOException e4) {
                    System.out.println("Something went wrong with reading file");
                }
            }

        }
        System.out.println("Enter a command");
        String command = scanner.nextLine();
        String[] commands = command.split(" ");


        boolean check = true;
        while (check) {
            switch (commands[0]) {
                case "help":
                    help();
                    history[k] = commands[0];
                    break;
                case "info":
                    c.info();
                    history[k] = commands[0];
                    break;
                case "show":
                    if (!c.checkToEmpty()) {
                        c.show();
                    } else {
                        System.out.println("Collection is empty");
                    }
                    history[k] = commands[0];
                    break;
                case "add":
                    System.out.println("Please enter an element");
                    HumanBeing h = ReadFromConsole.getElementFromConsole(scanner);
                    c.add(h);
                    id++;
                    history[k] = commands[0];
                    break;
                case "update":
                    String id_check;
                    int countCheckToUpdate = 1;
                    int id;
                    while (true) {
                        try {
                            if (countCheckToUpdate==1){
                                id_check = commands[1];
                            }else{
                                System.out.println("Enter an id");
                                id_check = scanner.nextLine();
                            }
                            id = Integer.parseInt(id_check);
                            if (id > 0) {
                                boolean check_in_hashset = false;
                                for (HumanBeing p : c.hashSet) {
                                    if (p.getId() == id) {
                                        check_in_hashset = true;
                                        break;
                                    }
                                }
                                if (check_in_hashset) {
                                    break;
                                } else throw new IllegalArgumentException();
                            } else {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e) {
                            countCheckToUpdate++;
                            System.out.println("Id has to be int and > 0. Try again");
                        } catch (IllegalArgumentException e) {
                            countCheckToUpdate++;
                            System.out.println("You entered id of element which doesn't exist.Try again");
                        }catch (IndexOutOfBoundsException e){
                            countCheckToUpdate++;
                            System.out.println("You forgot to enter an id. Do it");
                        }
                    }
                    HumanBeing hum = ReadFromConsole.getElementFromConsoleToUpdate(scanner, id);
                    c.update(id, hum);
                    history[k] = commands[0];
                    break;
                case "remove_by_id":
                    if (!c.checkToEmpty()) {
                        String idToRemove_check;
                        int count_check = 1;
                        int idToRemove;
                        while (true) {
                            try {
                                if (count_check == 1) {
                                    idToRemove_check = commands[1];
                                } else {
                                    System.out.println("Please enter an id of element ");
                                    idToRemove_check = scanner.nextLine();
                                }
                                idToRemove = Integer.parseInt(idToRemove_check);
                                if (idToRemove > 0) {
                                    boolean check_in = false;
                                    for (HumanBeing p : c.hashSet) {
                                        if (p.getId() == idToRemove) {
                                            check_in= true;
                                            break;
                                        }
                                    }
                                    if (check_in) {
                                        break;
                                    } else {
                                        count_check++;
                                        throw new IllegalArgumentException();
                                    }
                                } else {
                                    count_check++;
                                    throw new NumberFormatException();
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Id has to be int and > 0. Try again");
                            } catch (IllegalArgumentException e) {
                                System.out.println("You entered id of element which doesn't exist.Try again");
                            } catch (IndexOutOfBoundsException e) {
                                count_check++;
                                System.out.println("You didn't enter an id. Do it");
                            }
                        }
                        c.removeById(idToRemove);
                    } else {
                        System.out.println("Collection is empty");
                    }
                    history[k] = commands[0];
                    break;
                case "clear":
                    c.clear();
                    history[k] = commands[0];
                    break;
                case "save":
                    try {
                        c.save(fileName);
                        System.out.println("Data is saved to Input.cvs");
                    } catch (IOException e) {
                        System.out.println("File doesn't exist. Enter a command");
                    }
                    history[k] = commands[0];
                    break;
                case "execute_script":
                    String scriptName;
                    int count_check1 = 1;
                    while (true) {
                        try {
                            if (count_check1 == 1) {
                                scriptName = commands[1];
                            } else {
                                System.out.println("Enter a scriptName");
                                scriptName = scanner.nextLine();
                            }
                            check = c.readScript(scriptName, fileName);
                            break;
                        } catch (FileNotFoundException e) {
                            System.out.println("File doesn't exist. Enter command and scriptName again");
                            count_check1++;
                        } catch (IOException | IllegalArgumentException e) {
                            count_check1++;
                            System.out.println("Command or data in file is incorrect. Executing of file has stopped.");
                        } catch (IndexOutOfBoundsException e) {
                            if (count_check1==1){
                                System.out.println("You didn't enter a scriptName. Do it");
                            }else{
                                System.out.println("Some command doesn't have argument");
                            }
                            count_check1++;
                        }
                    }
                    history[k] = commands[0];
                    break;
                case "history":
                    int j = 0;
                    if (k != 0){
                        for (int i = history.length-1;i >= 0; i--){
                            if (history[i] == null)
                                continue;
                            else
                                if (history[i].equals("history") || history[i].equals("save") || history[i].equals("clear") || history[i].equals("remove_by_id") || history[i].equals("update") || history[i].equals("show") || history[i].equals("info") || history[i].equals("add") || history[i].equals("help") || history[i].equals("execute_script"))
                                    j++;
                                    System.out.println(history[i]);
                                if(j == 6)
                                    break;
                        }
                    }
                    history[k] = commands[0];
                    break;
                case "min_by_id":
                    c.minById();
                    break;
                case "add_if_min":
                    System.out.println("Please enter an element");
                    HumanBeing x = ReadFromConsole.getElementFromConsole(scanner);
                    c.addIfMin(x);
                    history[k] = commands[0];
                    break;
                case "remove_lower":
                    System.out.println("Please enter an element");
                    HumanBeing a = ReadFromConsole.getElementFromConsole(scanner);
                    c.removeLower(a);
                    break;
                case "max_by_car":
                    c.maxByCar();
                    break;
                case "print_ascending":
                    c.printAscending();
                    break;
                case "exit":
                    check = false;
                    break;
                default:
                    System.out.println("There is no this command. Try again");
            }
            if (check){
                System.out.println("Enter a command");
                command = scanner.nextLine();
                commands = command.split(" ");
                k++;
            }

        }
    }
    public static void help() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 6 команд (без их аргументов)\n" +
                "min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным\n" +
                "max_by_car : вывести любой объект из коллекции, значение поля car которого является максимальным\n" +
                "print_ascending : вывести элементы коллекции в порядке возрастания\n");
    }
}
