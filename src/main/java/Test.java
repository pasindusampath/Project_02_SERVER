import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void excCommand(String new_dir) throws IOException {

        String path = new Test().getClass().getResource("allowall.policy").getPath().toString();
        System.out.println(path);
        StringBuilder builder1 = new StringBuilder();
        char[] chars = path.toCharArray();
        for (int i = 1; i < chars.length ; i++) {
            builder1.append(chars[i]);
        }
        path=builder1.toString();
        String[] split = path.split("/");
        split[split.length-1]="";
        split[split.length-2]="";
        builder1.delete(0,builder1.length());
        for (String s : split) {
            builder1.append(s+"\\");
        }
        builder1.deleteCharAt(builder1.length()-1);

        System.out.println(path=builder1.toString());
        System.out.println(path);
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c" ,"cd "+path," && tree && cd classes && rmic ChatServer && start rmiregistry" +
                " &&java -Djava.security.policy=allowall.policy Main");
        builder.redirectErrorStream(true);
        Process p = builder.start();

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    public static void main1(String[] args) {
        // CMD commands to be executed
        String[] commands = {
                "echo Hello, this is a CMD command!",
                "dir", // Replace with any other CMD command you want to execute
                "ping 127.1.1.1"
                // Add more CMD commands here as needed
        };

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c");

            for (String command : commands) {
                builder.command().add(command);
            }

            builder.redirectErrorStream(true);
            Process process = builder.start();

            // Read the output from CMD and print it
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Wait for the CMD process to complete
            int exitCode = process.waitFor();

            System.out.println("CMD commands executed. Exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            excCommand("no");
            //main1(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
