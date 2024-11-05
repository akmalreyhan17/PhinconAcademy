package com.example.session16n.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session16n.configuration.ObjectMapperBuilder;
import com.example.session16n.model.Group;
import com.example.session16n.model.Person;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Service
public class TryoutService {

        public void testjson() throws IOException {

                // String json = "{ \"id\" : 1, \"name\" : \"John Doe\" }";

                // String json = "[{ \"id\" : 1, \"name\" : \"John Doe\" }"
                // + "{ \"id\" : 2, \"name\" : \"John Wick\" }]";

                // String json = "{\"number\": 1, \"member\": [\r\n" + //
                // " {\r\n" + //
                // " \"id\": 1,\r\n" + //
                // " \"name\": \"John Doe\"\r\n" + //
                // " },\r\n" + //
                // " {\r\n" + //
                // " \"id\": 2,\r\n" + //
                // " \"name\": \"John Wick\"\r\n" + //
                // " }\r\n" + //
                // " ]\r\n" + //
                // "}";

                // ObjectMapper objectMapper = new ObjectMapper();
                // List<Person> person = objectMapper
                // .readValue(json, new TypeReference<List<Person>>() {
                // });

                // Group group = objectMapper.readValue(json, Group.class);
                // Person p1 = group.getMember().get(0);
                // Person p2 = group.getMember().get(1);

                // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                // false);
                // objectMapper.setSerializationInclusion(Include.NON_NULL);

                // JsonNode node = objectMapper.readTree(json);
                // String name = node.get("name").asText();

                // Person person = new Person(1, "John Doe");
                // String json = objectMapper.writeValueAsString(person);

                // ObjectMapper mapper = new ObjectMapperBuilder()
                // .dateFormat()
                // .build();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                // JsonFactory jfactory = new JsonFactory();
                // JsonGenerator jGenerator = jfactory
                // .createGenerator(stream, JsonEncoding.UTF8);

                // jGenerator.writeStartObject();
                // jGenerator.writeNumberField("id", 1);
                // jGenerator.writeStringField("name", "John Doe");
                // jGenerator.writeEndObject();
                // jGenerator.close();

                String json = new String(stream.toByteArray(), "UTF-8");

                JsonFactory jfactory = new JsonFactory();
                JsonParser jParser = jfactory.createParser(json);
                Person person = new Person();

                while (jParser.nextToken() != JsonToken.END_OBJECT) {
                        String fieldname = jParser.getCurrentName();

                        if (fieldname.equals("id")) {
                                jParser.nextToken();
                                person.setId(jParser.getIntValue());
                                return;
                        }

                        if (fieldname.equals("name")) {
                                jParser.nextToken();
                                person.setName(jParser.getText());
                        }
                }
                jParser.close();

                System.out.println(json);
                // System.out.println(person);
                // System.out.println(name);
                // System.out.println(p1);
                // System.out.println(mapper);
        }

        public void testxml() throws JsonProcessingException, JAXBException {

                // XmlMapper mapper = new XmlMapper();
                // Person person = new Person(1, "John Doe");
                // String xml = mapper.writeValueAsString(person);

                // String xml = "<Person><id>1</id><name>John Doe</name></Person>";
                // XmlMapper mapper = new XmlMapper();
                // Person person = mapper.readValue(xml, Person.class);

                // Person person = new Person(1, "John Doe");
                // StringWriter writer = new StringWriter();

                // JAXBContext context = JAXBContext.newInstance(Person.class);
                // Marshaller mar = context.createMarshaller();
                // mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                // mar.marshal(person, writer);

                // String xml = writer.toString();
                
                String xml = "<Person><id>1</id><name>John Doe</name></Person>";
                JAXBContext context = JAXBContext.newInstance(Person.class);
                Unmarshaller unmar = context.createUnmarshaller();
                Person person = (Person) unmar.unmarshal(new StringReader(xml));
                
                

                System.out.println(person);
        }
}
