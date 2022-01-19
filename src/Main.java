import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";// colour changing text codes
    static void bubbleSort(int[] arr, String[] strArr) {//method for sorting
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    String strTemp = strArr[j];// the string variables here keep the names aligned with the scores
                    strArr[j] = strArr[j + 1];
                    strArr[j + 1] = strTemp;
                }
    }

    public static void main(String[] args) throws IOException {
        boolean reRun = true;//the loop and what's keeping it running
        while (reRun) {

            int textFileLength = 0;
            File myFile = new File(("FileName.txt"));//declaring filename
            Scanner lineCounter = new Scanner(myFile);
            while (lineCounter.hasNextLine()) {//loop to count how many lines are in the file
                lineCounter.nextLine();
                textFileLength++;
            }

            lineCounter.close();//closing line counter
            //three arrays, one for the file data, one for highs-cores and one for names
            String[] rawData = new String[textFileLength];
            int[] highScores = new int[textFileLength];
            String[] names = new String[textFileLength];
            Scanner myReader = new Scanner(myFile);
            Scanner input = new Scanner(System.in);

            for (int x = 0; myReader.hasNextLine(); x++) {//reading raw data and splitting into scores and names arrays
                rawData[x] = myReader.nextLine();
                highScores[x] = Integer.parseInt(rawData[x].substring(0, rawData[x].indexOf(" ")));
                names[x] = rawData[x].substring(rawData[x].indexOf(" ") + 1);
            }
            myReader.close();

            bubbleSort(highScores, names);//calling bubble sort method
            if (highScores.length > 10) {//if the length is over 10 after sorting, remove scores after 10
                int[] temp = new int[10];
                String[] strTemp = new String[10];
                System.arraycopy(highScores, 0, temp, 0, 10);
                System.arraycopy(names, 0, strTemp, 0, 10);
                highScores = temp;
                names = strTemp;

            }

            System.out.println("Would you like to print and save?");
            String save = input.nextLine();
            if (save.equalsIgnoreCase("yes")) {//if the user opts to save, the program saves and ends
                reRun = false;
            }
            FileWriter myWriter = new FileWriter("FileName.txt");
            System.out.println(ANSI_RED + highScores[0] + " " + names[0] + ANSI_RESET);//printing the highest score in red
            myWriter.write(highScores[0] + " " + names[0] + "\n");
            for (int x = 1; x < (textFileLength - 1); x++) {//printing the bulk of the data
                System.out.println(highScores[x] + " " + names[x]);
                myWriter.write(highScores[x] + " " + names[x] + "\n");
            }
            System.out.println(highScores[textFileLength - 1] + " " + names[textFileLength - 1]);//printing the final lines
            myWriter.write(highScores[textFileLength - 1] + " " + names[textFileLength - 1]);
            myWriter.close();
        }

    }
}
