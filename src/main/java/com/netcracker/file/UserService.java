package com.netcracker.file;

import com.netcracker.model.User;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
@Service
public final class UserService {

    public static void fileWrite(User person){
        File log = new File("person.txt");
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(person.getLastName()+" ");
            bufferedWriter.write(person.getFirsName()+" ");
            bufferedWriter.write(person.getAge()+" ");
            bufferedWriter.write(person.getSalaryLevel()+" ");
            bufferedWriter.write(person.getEmail()+" ");
            bufferedWriter.write(person.getPlaceOfWork());
            bufferedWriter.write("\n");
            bufferedWriter.close();
            System.out.println("Writing to the file was successful");
        } catch(IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

    public static User fileCheckUser(User person){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("person.txt"), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean b = false;
        for (String line : lines) {
            String[] split = line.split(" ");
            for (String word : split) {
                if(b){
                    if (word.equalsIgnoreCase(person.getFirsName())) {
                        System.out.println("Найдено");
                        return stringToUser(split);
                    } else b=false;
                }
                if (word.equalsIgnoreCase(person.getLastName())) {
                    b = true;
                }
            }
        }
        System.out.println("Не найдено");
        return null;
    }

    public static User fileUploadedUser(byte[] file){
        String strFile = new String(file);
        String[] splitStr = strFile.split(" ");
        User person = stringToUser(splitStr);
        fileWrite(person);
        return  person;
    }
    public static User stringToUser(String[] str){
        if(str.length < 5) return null;
        if(Pattern.matches("[a-zA-Z]+",str[0])
                &&Pattern.matches("[a-zA-Z]+",str[1])
                &&Pattern.matches("(\\w\\.?)+@[\\w\\.-]+\\.\\w{2,4}",str[4])) {
            User person1 =  new User();
            person1.setLastName(str[0]);
            person1.setFirsName(str[1]);
            person1.setAge(Integer.parseInt(str[2]));
            person1.setSalaryLevel(Integer.parseInt(str[3]));
            person1.setEmail(str[4]);
            person1.setPlaceOfWork(str[5]);
            return person1;
        }else  return null;
    }

}
