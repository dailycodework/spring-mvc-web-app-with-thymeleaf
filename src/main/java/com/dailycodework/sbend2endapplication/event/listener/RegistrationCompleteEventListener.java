package com.dailycodework.sbend2endapplication.event.listener;

import com.dailycodework.sbend2endapplication.event.RegistrationCompleteEvent;
import com.dailycodework.sbend2endapplication.registration.token.VerificationTokenService;
import com.dailycodework.sbend2endapplication.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author Sampson Alfred
 */
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //1. get the user
        user = event.getUser();
        //2. generate a token for the user
        String vToken = UUID.randomUUID().toString();
        //3. save the token for the user
        tokenService.saveVerificationTokenForUser(user, vToken);
        //4. Build the verification url
        String url = event.getConfirmationUrl()+"/registration/verifyEmail?token="+vToken;
        //5. send the email to the user
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }


    public void sendPasswordResetVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>"+
                "<p><b>You recently requested to reset your password,</b>"+"" +
                "Please, follow the link below to complete the action.</p>"+
                "<a href=\"" +url+ "\">Reset password</a>"+
                "<p> Users Registration Portal Service";
        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User theUser)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodeworks@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }



}
