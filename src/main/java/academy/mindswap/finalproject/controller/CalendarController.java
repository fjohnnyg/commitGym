package academy.mindswap.finalproject.controller;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

//import static academy.mindswap.finalproject.GoogleCalendarApi.CalendarQuickstart.getCredentials;

@RestController
@RequestMapping("https://www.googleapis.com/calendar/v3/calendars/calendarId/")
public class CalendarController {

    private static final String APPLICATION_NAME = "My Calendar App";
    private static final String SERVICE_ACCOUNT_EMAIL = "<YOUR_SERVICE_ACCOUNT_EMAIL>";
    private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "path/to/your/key.p12";
    private static final String CALENDAR_ID = "<YOUR_CALENDAR_ID>";
/*
    @PostMapping("/events")
        public ResponseEntity<String> createEvent(@RequestBody Event eventBody) {
        try {
            // Create an instance of the Calendar service
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            Calendar calendarService = new Calendar.Builder(httpTransport, jsonFactory, getCredentials(httpTransport).getRequestInitializer())
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // Create an instance of the Event class and set the properties
            Event event = new Event()
                    .setSummary(eventBody.getSummary())
                    .setDescription(eventBody.getDescription())
                    .setStart(new EventDateTime().setDateTime(new DateTime(String.valueOf(eventBody.getStart()))))
                    .setEnd(new EventDateTime().setDateTime(new DateTime(String.valueOf(eventBody.getEnd()))))
                    .setLocation(eventBody.getLocation());

            // Insert the event into the calendar and get the event ID
            Event createdEvent = calendarService.events().insert("primary", event).execute();
            String eventId = createdEvent.getId();

            // Return a success response with the event ID
            return ResponseEntity.ok().body(eventId);
        } catch (IOException | GeneralSecurityException e) {
            // Handle any exceptions that may occur during the API call
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

 */

}