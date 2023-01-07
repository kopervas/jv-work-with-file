package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String[] result = {"supply", "buy", "result"};
    private final String mySeparetor = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(createReport(parseDataFromFile(fromFileName)), toFileName);
    }

    public int[] parseDataFromFile(String fileName) {
        String[] records;
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                records = line.split(mySeparetor);
                if (records[0].equals(result[0])) {
                    supplySum += Integer.parseInt(records[1]);
                } else if (records[0].equals(result[1])) {
                    buySum += Integer.parseInt(records[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return new int[]{supplySum, buySum};
    }

    public String createReport(int[] parseResult) {
        String tempStr = "";
        tempStr += result[0] + mySeparetor + parseResult[0] + System.lineSeparator();
        tempStr += result[1] + mySeparetor + parseResult[1] + System.lineSeparator();
        tempStr += result[2] + mySeparetor + (parseResult[0] - parseResult[1])
                + System.lineSeparator();
        return tempStr;
    }

    public void writeDataToFile(String report, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.append(report);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
