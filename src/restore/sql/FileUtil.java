package restore.sql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * @author ob
 */
public class FileUtil {

    public static void appendTo(String filePath, String content) {
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(filePath, "rw");
            long fileLength = randomFile.length();
            //如果直接清除，指针位置需要往前移动
//            if(fileLength > 50000) {
//                clear(filePath);
//                fileLength = 0;
//            }
            randomFile.seek(fileLength);
            randomFile.writeUTF(content);
            randomFile.writeUTF("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(randomFile != null) {
                try {
                    randomFile.close();
                    randomFile = null;
                } catch (IOException e) {}
            }
        }
    }

    public static void appendTo2(String filePath, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"));
            writer.append(content);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {}
            }
        }
    }

    public static void clear(String filePath) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(filePath));
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
                writer = null;
            }
        }
    }

    public static void main(String[] args) {
        String fileName = "D:\\Java\\Workspaces\\IdeaProjects\\RestoreSql\\restore.sql";
        String content = "new append!";
        FileUtil.appendTo(fileName, content);
    }

}