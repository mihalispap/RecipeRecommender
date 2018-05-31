package gr.uoa.di.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uoa.di.recommender.model.User;
import gr.uoa.di.recommender.model.UserRating;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class GenerateUserRatings {

    public static void main(String[] args) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();

        float max = 5.0f;
        float min = 0.0f;

        new File(args[0]).mkdirs();

        Random rand = new Random();
        for(int i=0;i<Integer.valueOf(args[1]);i++){

            UserRating userRating = new UserRating();
            userRating.setUserId((long) rand.nextInt(Integer.valueOf(args[2])));
            userRating.setRecipeId((long) rand.nextInt(Integer.valueOf(args[3])));
            userRating.setRating(rand.nextDouble() * (max - min) + min);

            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userRating);
            Files.write(Paths.get(args[0]+"/userrating_"+i
                            +".json"), jsonResult.getBytes(),
                    StandardOpenOption.CREATE);
        }

        System.out.println();

    }

}
