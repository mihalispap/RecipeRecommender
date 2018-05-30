package gr.uoa.di.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uoa.di.recommender.model.Recipe;
import gr.uoa.di.recommender.model.User;
import gr.uoa.di.recommender.utils.Utilities;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Generate {

    public static void main(String[] args) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();

        new File(args[0]).mkdirs();

        for(int i=0;i<Integer.valueOf(args[1]);i++){
            User user = new User();

            user.setUserId((long) i);
            user.setFirstName(generateString());
            user.setLastName(generateString());
            user.setEmail(user.getFirstName()+"@email.com");
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

}
