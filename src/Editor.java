
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * Created by STARR on 4/9/2016.
 */
public class Editor {
    private static String name;
    private static int bound;
    private static int iterations;
    private static Scanner sc;
    private static boolean isAutoBound;
    private static List<String> results;

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 1) {
            System.out.println("PHOTO EVOLUTION");
            System.out.println("1. Edit Image");
            System.out.println("2. Exit");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 2) {
                return;
            }
        }
        showAvailableFiles();
        mainLoop();
    }

    private static void mainLoop() throws IOException {
        isAutoBound = true;

        sc = new Scanner(System.in);
        System.out.println("Image Name(include extension): ");
        name = sc.nextLine();
        System.out.println("Auto-bounding? (y/n): ");
        String option = sc.nextLine();
        if (option.equals("n")) {
            System.out.println("Upper/Lower Bound (0-255): ");
            bound = Integer.parseInt(sc.nextLine());
            isAutoBound = false;
        }
        System.out.println("Number of iterations: ");
        iterations = Integer.parseInt(sc.nextLine());
        System.out.println("Editing...");
        Long startTime = System.currentTimeMillis();
        Photo photo = new Photo(name);
        int cnt = 0;
        while (cnt < iterations) {
            if (isAutoBound) {
                photo.edit();
            } else {
                photo.edit(bound);
            }
            cnt++;
        }
        photo.save();
        Long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime));
    }

    private static void showAvailableFiles() throws IOException {
        results = new ArrayList<String>();
        File[] files = new File("PhotoEvolution/src/resources").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String fName = file.getName();
                if (fName.contains(".png") || fName.contains(".jpg")) {
                    results.add(fName);
                }
            }
        }

        System.out.println("Files available to edit: ");
        results.forEach((fname) -> {
            System.out.println(fname);
        });
    }

    public static List<String> getResults() {
        return results;
    }

    public static void setResults(List<String> results) {
        Editor.results = results;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Editor.name = name;
    }

    public static int getBound() {
        return bound;
    }

    public static void setBound(int bound) {
        Editor.bound = bound;
    }

    public static int getIterations() {
        return iterations;
    }

    public static void setIterations(int iterations) {
        Editor.iterations = iterations;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static void setSc(Scanner sc) {
        Editor.sc = sc;
    }

    public static boolean isAutoBound() {
        return isAutoBound;
    }

    public static void setIsAutoBound(boolean isAutoBound) {
        Editor.isAutoBound = isAutoBound;
    }
}
