import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Home {
    public static void main(String[] args) {
        System.out.println("hello world");

        // Get the current month and year
        LocalDate currentDate = LocalDate.now();
        String monthYear = currentDate.format(DateTimeFormatter.ofPattern("MMMM_yyyy"));

        Logs logs = new Logs();
        //logs.createFolderOnDesktop(monthYear);

        logs.debug(LocalDateTime.now().toString()+"first debug message"+"\n");


    }

}


class Logs {

    //write to file
    public void debug(String text) {
        // Get the path to the desktop directory
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        LocalDate currentDate = LocalDate.now();
        String monthYear = currentDate.format(DateTimeFormatter.ofPattern("MMMM_yyyy"));

        String folderName = monthYear;

        // Create a Path object for the desktop directory and the folder
        Path desktop = Paths.get(desktopPath);
        Path folderPath = desktop.resolve(folderName);

        // Specify the file name

        LocalDate date = LocalDate.now();
        String Filename = "FnbxLogFile";
        String fileName = Filename+date.toString();
        String filePath = folderPath + File.separator + fileName;

        // Write text to the file
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(text);
            fileWriter.flush();
            System.out.println("Text added to the file '" + fileName + "' in the folder '" + folderName + "'.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    //create folder like January_2023

    public static void createFolderOnDesktop(String folderName) {
        // Get the path to the desktop directory
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        // Create a Path object for the desktop directory and the new folder
        Path desktop = Paths.get(desktopPath);
        Path newFolder = desktop.resolve(folderName);

        // Create a File object from the Path
        File folder = newFolder.toFile();

        // Check if the folder doesn't exist, then create it
        if (!folder.exists()) {
            boolean folderCreated = folder.mkdir();
            if (folderCreated) {
                System.out.println("Folder '" + folderName + "' created successfully on the desktop.");
            } else {
                System.err.println("Failed to create folder '" + folderName + "' on the desktop.");
            }
        } else {
            System.out.println("Folder '" + folderName + "' already exists on the desktop.");
        }
        createTexFile(folder.getAbsolutePath(), folderName);
    }

    public static void createTexFile(String folderPath, String folderName) {
        // Get the current month and year
        LocalDate currentDate = LocalDate.now();
        String currentMonthYear = currentDate.format(DateTimeFormatter.ofPattern("MMMM_yyyy"));

        // Check if the current month is different from the folder's month
        if (!folderName.equals(currentMonthYear)) {
            // If the month changed, create a new folder for the current month
            createFolderOnDesktop(currentMonthYear);
            return;
        }

        // Create .tex file in the folder
        LocalDate date = LocalDate.now();
        String Filename = "FnbxLogFile";
        String monthYear = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String fileName = Filename+monthYear.toString();
        String filePath = folderPath + File.separator + fileName;

        // Check if the file doesn't exist, then create it
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File '" + fileName + "' created successfully in the folder '" + folderName + "'.");
                    // You can add content to the file here if needed
                } else {
                    System.err.println("Failed to create file '" + fileName + "' in the folder '" + folderName + "'.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file: " + e.getMessage());
            }
        } else {
            System.out.println("File '" + fileName + "' already exists in the folder '" + folderName + "'.");
        }
    }



}


