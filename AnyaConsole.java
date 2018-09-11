package epam;
import epam.CommandLineUtils;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AnyaConsole {
    public static void main(String[] args) {
        System.out.println(AnyaConsole.class.getName());
        Scanner scanner =new Scanner(System.in);
        String a;
        while ( !(a = scanner.nextLine()).equals("exit")) {
            String [] arr = a.split(" ");
            switch (arr[0]) {
                case "visitDoctor":
                    CommandLineUtils.visitDoctor();
                    break;
                case "changeDirectory":
                    if (arr.length == 1) {
                        File file = new File(".");
                        String d = file.getAbsolutePath();
                        try {
                            CommandLineUtils.сhangeDirectory(d);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        try {
                            CommandLineUtils.сhangeDirectory(arr[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "copyFile":
                    if (arr.length == 1) {
                        System.out.println("No file, no path");
                        break;
                    } else if (arr.length ==2) {
                        File file = new File(".");
                        String d = file.getAbsolutePath();
                        CommandLineUtils.copyFile(arr[1], d);
                        break;
                    } else if (arr.length ==3) {
                        CommandLineUtils.copyFile(arr[1], arr[2]);
                        break;
                    }
                case "fileRecord":
                    File file = new File(".");
                    String d = file.getAbsolutePath();
                    String content = "";
                    if (arr.length == 1) {
                        try {
                            epam.CommandLineUtils.fileRecord(d, content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (arr.length == 2) {
                        try {
                            CommandLineUtils.fileRecord(arr[1], content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (arr.length == 3) {
                        try {
                            CommandLineUtils.fileRecord(arr[1], arr[2]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "isLinkValid":
                    if (arr.length == 1) {
                        System.out.println("No link  provided");
                        break;
                    } else if (arr.length ==2) {
                        CommandLineUtils.isLinkValid(arr[1]);
                        break;
                    }
                case "secretMethod":
                    try {
                        CommandLineUtils.secretMethod();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "deleteDirectory":
                    if (arr.length == 1) {
                        System.out.println("No path provided");
                        break;
                    } else if (arr.length == 2) {
                        try {
                            CommandLineUtils.deleteDirectory(arr[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "zipIn":
                    if (arr.length == 1) {
                        System.out.println("No path provided");
                        break;
                    } else if (arr.length ==2) {
                        CommandLineUtils.zipIn(arr[1]);
                        break;
                    }
                case "zipOut":
                    if (arr.length == 1) {
                        System.out.println("No path provided");
                        break;
                    } else if (arr.length == 2) {
                        try {
                            CommandLineUtils.zipOut(arr[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                case "showTheWholeThing":
                    if (arr.length == 1) {
                        File fil = new File(".");
                        String dd = fil.getAbsolutePath();
                        try {
                            CommandLineUtils.showTheWholeThing(dd);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (arr.length == 2) {
                        try {
                            CommandLineUtils.showTheWholeThing(arr[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
            }
        }

    }
}
