package pl.kb;

import java.util.Scanner;

public class Main {

    static String[] tasks = new String[100];            // Tablica do przechowywania zadań
    static String[] projects = new String[100];         // Tablica do przechowywania projektów
    static String[] changLog = new String[100];         // Tablica do przechowywania spisu operacji

    static int taskCount = 0;                           // Zmienna do przechowywania indexu tablicy
    static int projectCount = 0;
    static int changeLogCount = 0;


    public static void displayManu(){
        System.out.println("1 - Create new task");
        System.out.println("2 - Remove task");
        System.out.println("3 - Create new project");
        System.out.println("4 - Remove project");
        System.out.println("5 - Display all tasks");
        System.out.println("6 - Display all projects");
        System.out.println("7 - Display all change log");
        System.out.println("0 - Exit App");
    }

    /**
     * Część odpowiedzialna za:
     * - dodawanie zadania
     * - usuwanie zadania
     * - wyświetlenie zadania
     */
    public static void addTask(String task){
        boolean hasCapacityForNewTask = taskCount < tasks.length;       // Zwróci true jeśli w tablicy jest miejsce na dodanie nowego zadania
        if(hasCapacityForNewTask){                                      // jeśli prawda
            tasks[taskCount] = task;                                    // doda zadanie do tablicy o aktualnym indeksie taskCount
            taskCount++;                                                // i zwiększy indeks o jeden
        }
    }

    public static void removeTask(int indexTask){                       // usuwanie task na podstawie indexu w tablicy
        boolean isTaskExist = indexTask < taskCount;                    // sprawdza czy usuwany indeks jest dostępny w tablicy
        if(isTaskExist){
            for (int i = indexTask; i < taskCount-1; i++) {
                tasks[i] = tasks[i+1];                                  // przesuwanie tablicy po usunięciu elementu
            }
            taskCount--;
        }
    }

    public static void displayTasks(){
        System.out.println("List of tasks: ");
        for (int i = 0; i < taskCount; i++) {                           // Wyświetlenie tasks do końca zakresu, a nie do końca tablicy(tasks.length-1)
            System.out.println(i + "." + " " + tasks[i]);
        }
    }


    /**
     * Część odpowiedzialna za:
     * - dodawanie projektu
     * - usuwanie projektu
     * - wyświetlenie projektu
     */

    public static void addProject(String project){
        boolean hasCapacityForNewProject = projectCount < projects.length;
        if(hasCapacityForNewProject){
            projects[projectCount] = project;
            projectCount++;
        }

    }

    public static void removeProject(int indexProject){
        boolean isProjectExist = indexProject < projectCount;
        if(isProjectExist){
            for (int i = indexProject; i < projectCount-1; i++) {
                projects[i] = projects[i+1];
            }
            projectCount--;
        }
    }

    public static void displayProjects(){
        System.out.println("List of projects: ");
        for (int i = 0; i < projectCount; i++) {
            System.out.println(i + "." + " " + projects[i]);
        }
    }


    /**
     * Część odpowiedzialna za:
     * - wykonane działania
     *
     */

    public static void addChangeLog(String action){
        changLog[changeLogCount] = action;
        changeLogCount++;
    }

    public static void displayChangeLogs(){
        System.out.println("List of all Actions: ");
        for (int i = 0; i < changeLogCount; i++) {
            System.out.println("[" + i +"]" + changLog[i]);
        }
    }




    /**
     * Metoda główna
     *
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isApplicationRun = true;
        int chosenNum;
        while (isApplicationRun) {
            displayManu();
            System.out.print("Choose the option: ");
            chosenNum = scanner.nextInt();
            System.out.println();

            switch(chosenNum){
                case 0:
                    isApplicationRun = false;
                    addChangeLog("Zamknięcie programu");
                    break;
                case 1:
                    System.out.print("Podaj nazwę zadania do dodania: ");
                    String task = scanner.next();
                    addTask(task);
                    addChangeLog("Dodano zadanie - " + task);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Podaj indeks zadania do usunięcia: ");
                    Integer indexTask = scanner.nextInt();
                    removeTask(indexTask);
                    addChangeLog("Usunięto zadanie o indeksie- " + indexTask);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Podaj nazwę projektu do dodania: ");
                    String project = scanner.next();
                    addProject(project);
                    addChangeLog("Dodano projekt - " + project);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Podaj indeks projektu do usunięcia: ");
                    Integer indexProject = scanner.nextInt();
                    removeProject(indexProject);
                    addChangeLog("Usunięto projekt o indeksie- " + indexProject);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("--------------");
                    displayTasks();
                    addChangeLog("Wyświetlono listę wszystkich zadań");
                    System.out.println("--------------");
                    break;
                case 6:
                    System.out.println("--------------");
                    displayProjects();
                    addChangeLog("Wyświetlono listę wszystkich projektów");
                    System.out.println("--------------");
                    break;
                case 7:
                    System.out.println("*******");
                    displayChangeLogs();
                    System.out.println("*******");
                default:
                    System.out.println("#####");
                    System.out.println("Nie ma takiej opcji");
                    addChangeLog("Wybrano niepoprawną liczbę");
                    break;
            }
        }

    }


}
