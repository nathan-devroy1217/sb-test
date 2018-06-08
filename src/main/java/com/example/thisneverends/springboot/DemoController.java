package com.example.thisneverends.springboot;

import models.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RequestMapping("/web")
@Controller
public class DemoController {

    @RequestMapping(value = "/paramCheck", params = "from", headers = "X-API-KEY")
    public String paramCheck(@RequestHeader HttpHeaders headers, Model model) {
        System.out.println(headers.getHost());
        System.out.println(headers.get("X-API-KEY"));
        return "paramsGood";
    }

    @RequestMapping("/formCrap")
    public String formCrap(){
        return "formCrap";
    }

    @RequestMapping("/handleForm")
    public String handleForm(@RequestParam("file") MultipartFile file) {
        try{
            if(!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                FileOutputStream fos = new FileOutputStream("/Users/ndevroy/dummy/" + file.getOriginalFilename());
                fos.write(bytes);
                fos.close();
                System.out.println("File uploaded successfully");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Errors with upload");
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            System.out.println("Errors with upload");
            e.printStackTrace();
            return "error";
        }
        return "operationComplete";
    }


    @RequestMapping("/createAccount")
    public String createAccount(Model model) {
        model.addAttribute("aNewAccount", new Account());
        return "createAccount";
    }


    @RequestMapping("/doCreate")
    public String doCreate(@Valid @ModelAttribute("aNewAccount") Account account, BindingResult result) {

        System.out.println("Do create: new account info: " + account.getFirstName() + " " + account.getLastName());
        System.out.println("Going off and creating a new account with values provided");

        int num = 1;
        if(result.hasErrors()) {
            System.out.println("Form has errors");
            for(ObjectError error : result.getAllErrors()) {
                System.out.println("Error " + num++ + ": " + error.toString());
            }
            return "error";
        }
        return "accountCreated";
    }
}
