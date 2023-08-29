package spazserpa.spazsoftware.model;

import org.springframework.web.multipart.MultipartFile;

public class ApplicationForm {
    private String name;
    private String email;
    private MultipartFile resume; // for file uploads
    private String message;

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for resume
    public MultipartFile getResume() {
        return resume;
    }

    // Setter for resume
    public void setResume(MultipartFile resume) {
        this.resume = resume;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }
}