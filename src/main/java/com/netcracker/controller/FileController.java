package com.netcracker.controller;

import com.netcracker.file.FilePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
    public class FileController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                FilePerson.fileUploadedPerson(file.getBytes());
                return "You have successfully uploaded the file <br />"+
                        "<a href=\"/\">Go to the main page</a>";
            } catch (Exception e) {
                return " You failed to upload the file => " + e.getMessage()+" <br />"+
                        "<a href=\"/\">Go to the main page</a>";
            }
        } else {
            return " You failed to upload the file because it is empty. <br />"+
                    "<a href=\"/\">Go to the main page</a>";
        }
    }

}