package com.example.demo21;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.sql.Date;

@RestController
public class TestController {
    @PostMapping(value = "/test", consumes = "application/xml")
    public ResponseEntity<String> test(@RequestBody String msg) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(msg)));
            return new ResponseEntity<>(doc.getElementsByTagName("BusinessPartnerID").item(0).getTextContent(), org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/test2", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> test2(@RequestBody MT_BusinessPartnerRetrieveRespMsg  msg){
        return new ResponseEntity<>(msg.BusinessPartnerRetrieveResultData.BusinessPartnerItem.BusinessPartnerID+"", org.springframework.http.HttpStatus.OK);
//        return new ResponseEntity<>(msg.paco(), org.springframework.http.HttpStatus.OK);
    }
}

record TestXml(String paco){
}
class ContactPerson {
    public String InternalID;
}

 class SenderParty {
    public ContactPerson ContactPerson;
}

 class MessageHeader {
    public String UUID;
    public String ReferenceUUID;
    public Date CreationDateTime;
    public String SenderBusinessSystemID;
    public String RecipientBusinessSystemID;
    public SenderParty SenderParty;
}

 class BusinessPartnerItem {
    public int BusinessPartnerID;
}

 class BusinessPartnerRetrieveResultData {
    public BusinessPartnerItem BusinessPartnerItem;
}

 class Item {
    public String Note;
}

 class Log {
    public int BusinessDocumentProcessingResultCode;
    public Item Item;
}

 class MT_BusinessPartnerRetrieveRespMsg {
    public MessageHeader MessageHeader;
    public BusinessPartnerRetrieveResultData BusinessPartnerRetrieveResultData;
    public Log Log;
    public String n0;
    public String prx;
    public String text;
}

