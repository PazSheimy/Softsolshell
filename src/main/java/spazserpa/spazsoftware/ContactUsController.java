package spazserpa.spazsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spazserpa.spazsoftware.service.EmailService;

@Controller
public class ContactUsController {

    @Autowired
    private EmailService emailService; // assuming you have this service

    @GetMapping("/contactus")

    public String contactus() {
        return "contactus";
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            Model model) {
        String subject = "New Message from " + name;
        String content = "From: " + email + "\n\nMessage:\n" + message;

        emailService.sendSimpleMessage("infotjournal@gmail.com", subject, content); // use your email sending logic

        model.addAttribute("message", "Message sent successfully!");
        return "contactus"; // redirect back to the aboutus page
    }

}
