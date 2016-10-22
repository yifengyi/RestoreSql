package restore.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * @author ob
 */
public class FileUtil {

    public static void appendTo(String filePath, String content) {
        try {
            RandomAccessFile randomFile = new RandomAccessFile(filePath, "rw");
            long fileLength = randomFile.length();
            if(fileLength > 50000) {
                clear(filePath);
                fileLength = 0;
            }
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.writeBytes("\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(new File(filePath));
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "D:\\Java\\Workspaces\\IdeaProjects\\RestoreSql\\restore.sql";
        String content = "new append!";
        FileUtil.appendTo(fileName, content);
    }

}