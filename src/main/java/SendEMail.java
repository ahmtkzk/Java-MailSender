import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEMail {

    private final String userEmail = "insertUserEmail"; //Sender email address
    private final String password = "insertUserpassword"; //Sender email password

    private final String mailSender = "mail@gmail.com"; //Sender email address
    private final String mailReceiver = "mail@gmail.com"; //Receiver email address

    private final String emailSubject = "Test Email Subject"; //Email subject
    final String content = "Test mail content"; //Email content

    public void sendEmail() {

        Session newSession = Session.getInstance(this.mailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, password);
            }
        });

        try {

            Message Demo_Message = new MimeMessage(newSession);

            Demo_Message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailReceiver));
            Demo_Message.setFrom(new InternetAddress(mailSender));
            Demo_Message.setSubject(emailSubject);
            Demo_Message.setText(content);
            Demo_Message.setSentDate(new Date());

            Transport.send(Demo_Message);

            System.out.println("Your email has been sent successfully!");

        } catch (final MessagingException e) {
            System.out.println("Email sending failed!");
            e.printStackTrace();
        }

    }

    public Properties mailProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return props;

    }

}
