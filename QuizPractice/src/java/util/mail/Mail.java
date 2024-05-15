package util.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

public class Mail {
    // Email: vannghibg03@gmail.com
    // Password: fqzp yxqg mrrh spxx

    private static final String from = "nghinv03@gmail.com";
    private static final String password = "jqpi dhev mrtr nmha";

    public static boolean sendEmail(String to, String subject, String content) {
        // Properties : declare properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Set session
        Session session = Session.getInstance(props, auth);

        // Create a message
        MimeMessage msg = new MimeMessage(session);

        try {
            // set content type
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Set sender
            msg.setFrom(from);

            // Set receiver
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Set title email
            msg.setSubject(subject);

            // Set date
            msg.setSentDate(new Date());

            // set content
            msg.setContent(content, "text/HTML; charset=UTF-8");

            // send mail
            Transport.send(msg);
            System.out.println("Send email successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Have an error in process to send email");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendMailOTP(String to, String code) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Your OTP Code");

            // Email content
            message.setText("Your OTP code is: " + code);

            // Send the message
            Transport.send(message);
            return true; // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace(); // Replace with proper logging
            return false; // Failed to send email
        }
    }

    public static boolean sendMailVerify(String recipient, String code, String linkActive) {
        // Properties : declare properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Account Verification");

            // Email content
            String emailContent = buildEmailContent(code, linkActive);
            message.setContent(emailContent, "text/html");

            // Send the message
            Transport.send(message);
            return true; // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace(); // Replace with proper logging
            return false; // Failed to send email
        }
    }

    private static String buildEmailContent(String code, String linkActive) {
        // Build the email content dynamically using the provided code and linkActive
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Account Verification</title>\n"
                + "    <style>\n"
                + "        /* Your CSS styles */\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h1>Verify Your Account</h1>\n"
                + "        <p>Thank you for signing up! To complete your registration, please click the button below to verify your account:</p>\n"
                + "        <a href=\"" + linkActive + "?token=" + code + "\" class=\"btn\">Verify Account</a>\n"
                + "        <p>If you did not create an account, you can safely ignore this email.</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

}
