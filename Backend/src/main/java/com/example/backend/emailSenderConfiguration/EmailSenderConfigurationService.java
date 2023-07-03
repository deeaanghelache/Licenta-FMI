package com.example.backend.emailSenderConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderConfigurationService {
    private final JavaMailSender mailSender;

    public EmailSenderConfigurationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(String email, String firstName, String lastName) {
        String userName = firstName + " " + lastName;

        SimpleMailMessage messageToBeSent = new SimpleMailMessage();
        messageToBeSent.setTo(email);
        messageToBeSent.setSubject("Welcome to WanderJoy world!");
        messageToBeSent.setText("Dear " + userName + ",\n" +
                "\n" +
                "Welcome to Travel Adventures! We're thrilled to have you as a member of our travel community. Get ready to embark on exciting journeys, capture your travel memories, and discover new destinations.\n" +
                "\n" +
                "At Travel Adventures, we're committed to making your travel experiences memorable and hassle-free. Here's a quick overview of what you can expect:\n" +
                "\n" +
                "Plan your trips: Use our intuitive trip planning tool to create itineraries, add attractions, and organize your travel schedule effortlessly. Explore various destinations, find hidden gems, and make the most of your adventures.\n" +
                "Journal your memories: Capture your travel stories, photos, and experiences in your personal travel journal. Share your adventures with friends and family, and relive those unforgettable moments whenever you like." +
                "Once again, welcome to Travel Adventures! Start planning, journaling, and exploring today. Let your travel adventures begin!\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                userName + "\n" +
                "WanderJoy Team");

        mailSender.send(messageToBeSent);
    }
}
