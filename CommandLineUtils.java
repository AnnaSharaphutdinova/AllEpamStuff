package epam;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.*;

public class CommandLineUtils {
    File file = new File(".");
    String d=file.getAbsolutePath();
    //set to the user's home directory
    private  static String s = System.getProperty("user.home");


    public static void main(String[] args) throws IOException {


    }

    public static void сhangeDirectory(String str) throws IOException {
        s = str;
        System.out.println(s);
   }
    public static String getDir() {
        return  s;
    }

    public static void copyFile(String fileName, String targetAdress) {
        System.out.println("Look here"+getDir()); // ЗДЕСЬ ОН ПЕЧАТАЕТ СТАРУЮ  ДИРЕКТОРИЮ
        Path path = Paths.get(getDir() + File.separator+fileName);
        System.out.println(path);
        if (!Files.exists(path)) return;
        File t = new File(targetAdress);
        String tar = t.getAbsolutePath();
        Path target = Paths.get(t+ File.separator+fileName);
        try {
            Files.copy(path, target);
        } catch (IOException e) {
            System.out.println("Oops this file already exists");
        }

    }
    static public void fileRecord(String adress, String content) throws IOException {
        File file = new File("yourNewFile.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.flush();
        fw.close();
        Path source = Paths.get("yourNewFile.txt");
        Path target = Paths.get(adress , "yourNewFile.txt");
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
    //shows the conntent of the directory
    public static List<String>  showTheWholeThing(String adress) throws IOException {

        List<String> list = new ArrayList<>();
        ShowContent fileVisitor = new ShowContent();
        Path target = Paths.get(adress);
        String a = (Files.walkFileTree(target, fileVisitor)).toString();
        String []arr  =a.split("\\t");
        for (String s : a.split(" ")) {
            System.out.println(s + " hey");
            list.add(s);
        }
        return list;

    }
    //writes a compliment
    public static void secretMethod() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"Desktop","MyHumbleCompliment2.txt");
        Files.createFile(path);
        List<String> lines = Arrays.asList("You are beautiful and amazing creature! I love you!");
        Files.write(path, lines, Charset.forName("UTF-8"));
  }

    // checks if the link is valid
    public static void isLinkValid(String s) {
        Pattern pattern = Pattern.compile("(\\w*)([(\\.com)|(\\.org)|(\\.ru)|(\\.net)])([/\\W*\\.]*)?");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            System.out.println("Link is not appropriate");
            return;
        } else {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) new URL(s).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(500);
                connection.setReadTimeout(500);
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                    System.out.println("Link is valid");
                    System.out.println(connection);
                } else {
                    System.out.println("fail: "+connection.getResponseCode()+", "+connection.getResponseMessage());
                }
            } catch (MalformedURLException e) {
                System.out.println("no such link");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection!=null) connection.disconnect();
            }
        }



    }

    //deletes a directory
    public static void deleteDirectory(String adress) throws IOException {
        Path target = Paths.get(adress);
        Files.walkFileTree(target, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });

    }

    private static void helpFunc(Calendar date) {
        if ((date.getTime().getDay() == 5) | (date.getTime().getDay() == 6)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            System.out.print("You may come "+sdf.format(date.getTime())+" ");
            if (date.getTime().getDay() == 5) date.add(Calendar.DAY_OF_WEEK, 3);
            else if (date.getTime().getDay() == 6) date.add(Calendar.DAY_OF_WEEK, 2);
            switch (date.getTime().getDate() % 3) {
                case (0):
                    System.out.println(" 8 till 12");
                    break;
                case (1):
                    System.out.println(" 16 till 20");
                    break;
                case (2):
                    System.out.println(" 12 till 16");
                    break;

            }
            return;
        } else {
            date.add(Calendar.DAY_OF_WEEK, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            System.out.print("You may come "+sdf.format(date.getTime())+" ");
            switch (date.getTime().getDate() % 3) {
                case (0):
                    System.out.println(" 8 till 12");
                    break;
                case (1):
                    System.out.println(" 16 till 20");
                    break;
                case (2):
                    System.out.println(" 12 till 16");
                    break;

            }
            return;
        }
    }

    //tells when one should visit a doctor (according to doctor's scheldule)
    public static void visitDoctor() {
        Calendar currentDate= Calendar.getInstance();
        if (currentDate.getTime().getDay()== 6 | currentDate.getTime().getDay()== 7) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            System.out.print("You may come "+sdf.format(currentDate.getTime())+" ");
            if (currentDate.getTime().getDay() == 6) {
                currentDate.add(Calendar.DAY_OF_WEEK, 2);
                switch (currentDate.getTime().getDate() % 3) {
                    case (0) :
                        System.out.println("8 till 12");
                        break;
                    case (1):
                        System.out.println("16 till 20");
                        break;
                    case (2):
                        System.out.println("12 till 16");
                        break;
                }
            }
            return;
        }
        switch (currentDate.getTime().getDate() % 3) {
            case(0):
                Calendar date = Calendar.getInstance();

                date.set(Calendar.HOUR_OF_DAY, 12);
                date.set(Calendar.MINUTE, 0);
                if (currentDate.getTime().after(date.getTime())) {
                    helpFunc(date);
                } else {
                    System.out.print("You may come today 8 till 12");
                     }
                    return;



            case (1):
                Calendar date1 = Calendar.getInstance();
                date1.set(Calendar.HOUR_OF_DAY, 20);
                date1.set(Calendar.MINUTE, 0);
                if (currentDate.getTime().after(date1.getTime())) {
                    helpFunc(date1);
                } else {
                    System.out.print("You may come today 16 till 20");
                }
                return;

            case (2):
                Calendar date2 = Calendar.getInstance();
                date2.set(Calendar.HOUR_OF_DAY, 16);
                date2.set(Calendar.MINUTE, 0);
                if (currentDate.getTime().after(date2.getTime())) {
                    helpFunc(date2);
                } else {
                    System.out.print("You may come today 16 till 20");
                }
                return;

        }
        System.out.println(currentDate.getTime().getHours());
        }

        //shows the content in thr dir sorted by second letter (just because i can do it dude)
    public static void getSortedContent(String adress) {
        Path dir = Paths.get(adress);
        List<String> collection = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            for (Path entry : stream) {
                collection.add((entry.getFileName()).toString());
            }
            Collections.sort(collection, (a,b)->(a.substring(1)).compareTo(b.substring(1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : collection) {
            System.out.println(s);
        }
    }

    //get the stuff from zip    works for windows
    public static void zipOut(String s) throws IOException {
        Path check = Paths.get(s);

        if (!Files.exists(check)) {
            System.out.println("no such archive found");
            return;
        }
        ZipFile zip = new ZipFile(s);
        Enumeration inZip = zip.entries();
        List<ZipEntry> zipEntries = new ArrayList<>();
        Path path = Paths.get("Fucking_output");
        Files.createDirectories(path);

        while (inZip.hasMoreElements()) {
            zipEntries.add((ZipEntry) inZip.nextElement());
        }
        for (ZipEntry zp : zipEntries) {
            System.out.println(zp.getName());

            if (new  File(zp.getName()).isDirectory()) {
                String newAdress = (zp.getName()).replace("C:", "Fucking_output");
                Path dir = Paths.get(newAdress);
                Files.createDirectories(dir);
                System.out.println(Files.exists(dir));
            }
        }
        for (ZipEntry zp : zipEntries) {
            if (!(new  File(zp.getName()).isDirectory())) {
                InputStream ip = zip.getInputStream(zp);
                String newAdress = (zp.getName()).replace("C:", "Fucking_output");
                OutputStream os = null;
                System.out.println((zp.getName()).split(":?\\\\").length);
                os = new FileOutputStream(newAdress);


                BufferedOutputStream bos = new BufferedOutputStream(os, 2048);
                byte[] buffer = new byte[2048];
                int count;
                while ((count = ip.read(buffer)) > -1) {
                    bos.write(buffer, 0, count);
                }
                bos.flush();
                bos.close();
                ip.close();
            }
        }

    }

        //put stuff into the zip
    public static  void zipIn(String s) {
        class ForZip{
            private void doZip(File dir, ZipOutputStream out) throws IOException {
                for (File f : dir.listFiles()) {

                    out.putNextEntry(new ZipEntry(f.getPath()));

                    if (f.isDirectory()){
                        doZip(f, out);

                    }
                    else {
                        write(new FileInputStream(f), out);
                    }
                }
            }

            private  void write(InputStream in, OutputStream out) throws IOException {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) >= 0)
                    out.write(buffer, 0, len);
                in.close();
            }

        }
        ForZip fz = new ForZip();
        File file = new File(s);
        if (!file.exists()) {
            System.out.println("Not a directory path");
            return;
        } else {
            System.out.println("ok");
        }

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream("archiveeeee.zip"))){
            fz.doZip(file, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


class ShowContent extends SimpleFileVisitor<Path> {
    private long overallSize;
    private long overallCount;
    private long matchedCount;
    private long matchedSize;


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        overallSize +=attrs.size();

        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }


}