package util.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
}
