package dk.bec.bookanything.service;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

}
