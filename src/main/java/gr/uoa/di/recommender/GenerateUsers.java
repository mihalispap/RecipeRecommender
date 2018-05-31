package gr.uoa.di.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uoa.di.recommender.model.Recipe;
import gr.uoa.di.recommender.model.User;
import gr.uoa.di.recommender.utils.Utilities;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class GenerateUsers {

    public static void main(String[] args) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();

        new File(args[0]).mkdirs();

        for(int i=0;i<Integer.valueOf(args[1]);i++){
            User user = new User();

            user.setUserId((long) i);
            user.setFirstName(generateName());
            user.setLastName(generateSurname());
            user.setEmail(user.getFirstName().toLowerCase()+"@email.com");
            user.setPassword("test");

            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            Files.write(Paths.get(args[0]+"/user_"+i
                            +".json"), jsonResult.getBytes(),
                    StandardOpenOption.CREATE);
        }

        System.out.println();

    }

    private static String generateString(){
        return RandomStringUtils.random(7, true, false);
    }

    private static String generateName() throws Exception{
        ClassLoader classLoader = GenerateUsers.class.getClassLoader();
        String content = new String(Files.readAllBytes(
                Paths.get(classLoader.getResource("firstnames/all.txt").toURI())));
        Random rand = new Random();
        int noLines = StringUtils.countMatches(content, "\n");
        return Files.readAllLines(
                Paths.get(classLoader.getResource("firstnames/all.txt").toURI()))
                .get(rand.nextInt(noLines));
    }

    private static String generateSurname() throws Exception{
        ClassLoader classLoader = GenerateUsers.class.getClassLoader();
        String content = new String(Files.readAllBytes(
                Paths.get(classLoader.getResource("surnames/all.txt").toURI())));
        Random rand = new Random();
        int noLines = StringUtils.countMatches(content, "\n");
        return Files.readAllLines(
                Paths.get(classLoader.getResource("surnames/all.txt").toURI()))
                .get(rand.nextInt(noLines));
    }



}
