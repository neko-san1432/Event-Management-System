package javad;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.model.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.quartz.Job;
import org.quartz.JobExecutionContext;


import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class Email implements Job {
  private final Gmail service;
  String testMail = "eventmanager@gmail.com";

  public Email() throws Exception {
    NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory)).setApplicationName("Event Management").build();
  }

  private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException {
    InputStream in = Email.class.getResourceAsStream("/client_secret_762985775959-0r7km4bhvur4no242mvifn0nq83khsr1.apps.googleusercontent.com.json");
    if (in == null) {
      throw new IOException("Resource not found: client_secret_762985775959-0r7km4bhvur4no242mvifn0nq83khsr1.apps.googleusercontent");
    }
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
    httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(String.valueOf(Paths.get("tokens").toFile()))))
    .setAccessType("offline")
    .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

    // Returns an authorized Credential object.
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  private void sentMail(String subject, String message) throws Exception {
    // Encode as MIME message
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage email = new MimeMessage(session);
    email.setFrom(new InternetAddress(testMail));
    email.addRecipient(TO, new InternetAddress("pyrrhuselcidgo@gmail.com"));
    email.setSubject(subject);
    email.setText(message);


    // Encode and wrap the MIME message into a gmail message
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    email.writeTo(buffer);
    byte[] rawMessageBytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
    Message msg = new Message();
    msg.setRaw(encodedEmail);

    try {
      // Create the draft message
      msg = service.users().messages().send("me", msg).execute();
      System.out.println("Message id: " + msg.getId());
      System.out.println(msg.toPrettyString());
    } catch (GoogleJsonResponseException e) {
      // TODO(developer) - handle error appropriately
      GoogleJsonError error = e.getDetails();
      if (error.getCode() == 403) {
        System.err.println("Unable to create draft: " + e.getDetails());
      } else {
        throw e;
      }
    }

  }
  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    try {
      new Email().sentMail("Invitation", "hi");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    System.out.println("run");
  }
}
