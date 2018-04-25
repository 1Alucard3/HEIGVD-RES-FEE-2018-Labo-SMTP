import com.sun.istack.internal.localization.NullLocalizable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String argv[]) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Groupe existant [1] ou nouveau groupe [2] ?");
        int victimGroup = 0;
        try {
            victimGroup = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Existing group we would like to add some victims
        if (victimGroup == 1) {
            System.out.println("Indiquez le groupe concerné (par exemple G1 ou G13)");
            String designedGroup = null;
            try {
                designedGroup = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Designed group is : " + designedGroup);

        } else if (victimGroup == 2) {  // Create a new group

            // Based on existing groups, get next available ID
            int newGroupID = getNextGroupID();

            System.out.println("Enter email addresses you would like to add to this group (comma separated)");
            String inputStr = null;
            try {
                // Typical input : "test@gmail.com, username@cff.ch, rts@ssr.ch"
                inputStr = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Explode email addresses into pieces
            List<String> emails = Arrays.asList(inputStr.split("\\s*,\\s*"));
            Path file = Paths.get("src/victims/victims-G" + newGroupID);
            try {
                Files.write(file, emails, Charset.forName("UTF-8"));
                // Files.write(file, emails, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public static File[] listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();

        return fList;
    }

    public static int getNextGroupID() {
        File[] fileList = listFiles("src/victims");

        // A dynamic integer array
        List<Integer> groupIdList = new ArrayList<Integer>();

        for (File file: fileList) {
            String regex = ".*G([0-9]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                groupIdList.add(Integer.parseInt(matcher.group(1)));
            }
        }

        Collections.sort(groupIdList);
        int max = 0;
        for (Integer id: groupIdList) {
            if (id > max) {
                max = id;
            }
        }

        // New victims group ID
        int newGroupID = max + 1;

        return newGroupID;
    }
}
