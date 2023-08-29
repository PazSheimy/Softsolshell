package spazserpa.spazsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import spazserpa.spazsoftware.model.ApplicationForm;
import spazserpa.spazsoftware.service.EmailService;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CarrerController {

    private static final Logger logger = LoggerFactory.getLogger(CarrerController.class);

    @Autowired
    private EmailService emailService;

    @GetMapping("/carrers")
    public String carrers() {
        return "carrers";
    }

    @PostMapping("/submit_application")
    public String submitApplication(@ModelAttribute ApplicationForm applicationForm) throws Exception { // notice the
                                                                                                        // 'throws
                                                                                                        // Exception'

        // Construct email content based on applicationForm's data
        String subject = "New Job Application from " + applicationForm.getName();
        String content = "Name: " + applicationForm.getName() + "\n"
                + "Email: " + applicationForm.getEmail() + "\n"
                + "Message: " + applicationForm.getMessage();

        // Save the uploaded file temporarily
        File tempFile = File.createTempFile("uploaded_", "_" + applicationForm.getResume().getOriginalFilename());
        applicationForm.getResume().transferTo(tempFile);

        // Send the email with attachment (assuming your emailService has this method)
        emailService.sendMessageWithAttachment("infotjournal@gmail.com", subject, content, tempFile.getAbsolutePath());

        // Optionally, delete the temp file after sending the email
        tempFile.delete();

        // Log the details
        logger.info("Received application from: {}", applicationForm.getName());
        logger.info("Email: {}", applicationForm.getEmail());
        logger.info("Message: {}", applicationForm.getMessage());

        return "submission_success"; // navigate to a success page
    }
}
