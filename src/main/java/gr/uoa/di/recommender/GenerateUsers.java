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
        String jsonResult;

        User konstantina = new User();
        konstantina.setPassword("test");
        konstantina.setFirstName("Konstantina");
        konstantina.setLastName("Stamoulou");
        konstantina.setEmail("konstantina@foodie.com");
        konstantina.setUserId((long)0);

        Files.write(Paths.get(args[0]+"/user_0"
                        +".json"), objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(konstantina).getBytes(),
                StandardOpenOption.CREATE);

        User mihalis = new User();
        mihalis.setPassword("test");
        mihalis.setFirstName("Mihalis");
        mihalis.setLastName("Papakonstantinou");
        mihalis.setEmail("mihalis@foodie.com");
        mihalis.setUserId((long)1);

        Files.write(Paths.get(args[0]+"/user_1"
                        +".json"), objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(mihalis).getBytes(),
                StandardOpenOption.CREATE);

        for(int i=2;i<Integer.valueOf(args[1]);i++){
            User user = new User();

            user.setUserId((long) i);
            user.setFirstName(generateName());
            user.setLastName(generateSurname());
            user.setEmail(user.getFirstName().toLowerCase()+"@email.com");
            user.setPassword("test");

            jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
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
