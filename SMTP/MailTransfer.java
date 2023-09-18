package sample;
//package com.sendemail;
// Import statements for necessary Java libraries and JavaMail API classes

import java.io.IOException; // Import for handling Input/Output exceptions
import java.util.Date; // Import for working with Date objects
import java.util.Properties; // Import for managing email configuration properties
import javax.mail.Authenticator; // Import for email authentication
import javax.mail.Message; // Import for creating email messages
import javax.mail.MessagingException; // Import for handling email-related exceptions
import javax.mail.Multipart; // Import for creating multi-part email messages
import javax.mail.PasswordAuthentication; // Import for authentication with email credentials
import javax.mail.Session; // Import for creating email sessions
import javax.mail.Transport; // Import for sending email
import javax.mail.internet.AddressException; // Import for handling email address exceptions
import javax.mail.internet.InternetAddress; // Import for representing email addresses
import javax.mail.internet.MimeBodyPart; // Import for creating parts of a MIME message
import javax.mail.internet.MimeMessage; // Import for creating MIME messages
import javax.mail.internet.MimeMultipart; // Import for creating multi-part MIME messages
public class MailTransfer {
    public static void sendEmailWithFileAttachments(String host_id, String port_num, final String mailFrom,
                                                    final String security_code, String mailTo, String regarding,
                                                    String text, String[] file_dir) throws AddressException, MessagingException {

        // Create a Properties object to store email configuration properties
        Properties p = new Properties();
        // Set the SMTP host property
        p.put("mail.smtp.host", host_id);
        // Set the SMTP port property
        p.put("mail.smtp.port", port_num);
        // Enable SMTP authentication
        p.put("mail.smtp.auth", "true");
        // Enable STARTTLS encryption
        p.put("mail.smtp.starttls.enable", "true");
        // Set the email user
        p.put("mail.user", mailFrom);
        // Set the email password
        p.put("mail.password", security_code);

        // Create an Authenticator object for authentication
        Authenticator a = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // Return the email address and password for authentication
                return new PasswordAuthentication(mailFrom, security_code);
            }
        };

        // Create a Session using the Properties and Authenticator
        Session s = Session.getInstance(p, a);

        // Create a new email message
        Message message = new MimeMessage(s);
        // Set the sender's email address
        message.setFrom(new InternetAddress(mailFrom));
        // Set the recipient's email address
        InternetAddress[] to_addr = {new InternetAddress(mailTo)};
        message.setRecipients(Message.RecipientType.TO, to_addr);
        // Set the email subject
        message.setSubject(regarding);
        // Set the email sent date
        message.setSentDate(new Date());

        // Create a MimeBodyPart for the email text
        MimeBodyPart txt = new MimeBodyPart();
        // Set the email text content as HTML
        txt.setContent(text, "text/html");

        // Create a MimeMultipart to handle multiple parts of the email
        Multipart multi_part = new MimeMultipart();
        // Add the text part to the email
        multi_part.addBodyPart(txt);

        // Check if there are file attachments
        if (file_dir != null && file_dir.length > 0) {
            // Loop through the file attachments
            for (String file : file_dir) {
                // Create a MimeBodyPart for each attachment
                MimeBodyPart attachment = new MimeBodyPart();
                try {
                    // Attach the file to the email
                    attachment.attachFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Add the attachment to the email
                multi_part.addBodyPart(attachment);
            }
        }

        // Set the multi-part content as the email's content
        message.setContent(multi_part);

        // Send the email using the Transport class
        Transport.send(message);
    }

    public static void main(String[] args) {
        // SMTP Information
        String host_id = "smtp.gmail.com";
        String port_num = "587";
        String mailFrom = "124157037@sastra.ac.in";
        String security_code = "zjcmxfrcorxainbr";

        // Message info
        String mailTo = "neerajakallamadi@gmail.com";
        String regarding = "Hey! I have sent something for you using SMTP.";
        String text = "I have new files for you.";

        // Attachments
        String[] file_dir = new String[1];
        file_dir[0] = "C:\\Users\\NEERAJA\\Desktop\\cn-proj(final)\\SMTP\\smtp_txt.txt";
		//file_dir[0] = "C:\\Users\\NEERAJA\\Desktop\\cn-proj(final)\\SMTP\\smtp_docx.docx";
		//file_dir[0] = "C:\\Users\\NEERAJA\\Desktop\\cn-proj(final)\\SMTP\\smtp_image.png";
		//file_dir[0] = "C:\\Users\\NEERAJA\\Desktop\\cn-proj(final)\\SMTP\\smtp_audio.mp3";
		//file_dir[0] = "C:\\Users\\NEERAJA\\Desktop\\cn-proj(final)\\SMTP\\smtp_video.mp4";
        try {
            // Send the email with attachments
            sendEmailWithFileAttachments(host_id, port_num, mailFrom, security_code, mailTo, regarding, text, file_dir);
            System.out.println("Email sent successfully!!");
        } catch (Exception exep) {
            System.out.println("Could not send email, Please check for the code once!");
            exep.printStackTrace();
        }
    }
}


// Brief Overview of above code:
// This Java code defines a program for sending an email with file attachments using the JavaMail API. 
// It starts by configuring the SMTP server settings such as host, port, and authentication credentials.
// The sendEmailWithFileAttachments method is responsible for constructing the email message with a specified recipient, subject, text, and any attached files. 
// It utilizes JavaMail classes to create and send the email.
// In the main method, SMTP information, message details, and file attachments are set, and the email is sent using the sendEmailWithFileAttachments method.
// If successful, it prints a confirmation message; otherwise, it handles and displays any exceptions that might occur during the email sending process.
