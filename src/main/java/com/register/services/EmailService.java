package com.register.services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;



@Service
@RequiredArgsConstructor
public class EmailService {
 private SpringResourceTemplateResolver templateResolver;
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;

  @Async
  public void send(
      String to,
      String username,
      String templateName,
      String confirmationUrl
  ) throws javax.mail.MessagingException {
    if (!StringUtils.hasLength(templateName)) {
      templateName = "confirm-email";
    }
    javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(
        mimeMessage,
        MimeMessageHelper.MULTIPART_MODE_MIXED,
        StandardCharsets.UTF_8.name()
    );
    Map<String, Object> properties = new HashMap<>();
    properties.put("username", username);
    properties.put("confirmationUrl", confirmationUrl);

    Context context = new Context();
    context.setVariables(properties);

    helper.setFrom("babiodun21@gmail.com");
    helper.setTo(to);
    helper.setSubject("Welcome to our platform");

    String template = templateEngine.process(templateName, context);

    helper.setText(template, true);

    mailSender.send(mimeMessage);
  }

 
}
