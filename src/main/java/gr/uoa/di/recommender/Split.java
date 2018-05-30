package gr.uoa.di.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uoa.di.recommender.model.Recipe;
import gr.uoa.di.recommender.utils.Utilities;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Split {

    public static void main(String[] args) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        Recipe[] recipes = objectMapper.readValue(Utilities.readFile(args[0], StandardCharsets.UTF_8),
                Recipe[].class);

        new File(args[1]).mkdirs();

        for(int i=0;i<recipes.length;i++){

            recipes[i].setRecipeId(i);
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(recipes[i]);
            Files.write(Paths.get(args[1]+"/recipe_"+i
                            +".json"), jsonResult.getBytes(),
                    StandardOpenOption.CREATE);

        }

        System.out.println();

    }

}
