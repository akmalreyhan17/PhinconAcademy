package com.example.session35.service;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import jakarta.servlet.ServletContext;

@Service
public class ResourceService {

    @Autowired
    ServletContext servletContext;

    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    @Autowired
    ResourceLoader resourceLoader;

    public void loadCustomResource() throws IOException {
        String location = "myproc:resourceId123";
        Resource resource = resourceLoader.getResource(location);

        if (resource.exists()) {
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Loaded content: " + content);
        } else {
            System.out.println("Resource not found: " + location);
        }
    }

    public void loadResources() throws IOException {
        Resource[] resources = resourcePatternResolver.getResources("classpath*:templates/*.html");
        for (Resource resource : resources) {
            System.out.println("Found resource: " + resource.getFilename());
        }
    }

    public void loadResorces() {
        // Classpath resource
        Resource classpathResource = resourceLoader.getResource("classpath:config/app.properties");

        // File system resource
        Resource fileResource = resourceLoader.getResource("file:/path/to/myfile.txt");

        // HTTP resource
        Resource httpResource = resourceLoader.getResource("https://example.com/resource");

        // FTP resource
        Resource ftpResource = resourceLoader.getResource("ftp://example.com/file.txt");

        // JAR resource
        Resource jarResource = resourceLoader.getResource("jar:file:/path/to/myjar.jar!/META-INF/config.properties");
    }

    public void any() {
        // Resource resource = resourceLoader.getResource("classpath:data/sample.txt");

        // Resource resource = new ClassPathResource("data/config.json");

        // String absolutePath =
        // "C:/Users/Akmal/Documents/projects/session35/document/file.txt";
        // String relativePath = "/document/file.txt";

        // Resource resourceA = new FileSystemResource(absolutePath);
        // Resource resourceB = new FileSystemResource(relativePath);

        // try {
        // Resource urlResource = new
        // UrlResource("http://example.com/secured-resource");

        // URLConnection connection = urlResource.getURL().openConnection();
        // // Set Authorization header
        // connection.setRequestProperty("Authorization", "Bearer yourAccessToken");

        // InputStream inputStream = connection.getInputStream();
        // System.out.println(inputStream);

        // } catch (MalformedURLException e) {
        // System.out.println(e.getMessage());
        // } catch (IOException e) {
        // System.out.println(e.getMessage());
        // }

        // byte[] content = "This is a dynamically generated file".getBytes();

        // Resource resource = new ByteArrayResource(content);

        // Simulate an input stream from which the resource will be read (this could
        // come from any source, such as an API)
        // ByteArrayInputStream inputStream = new ByteArrayInputStream("Streamed
        // data".getBytes());

        // Resource resource = new InputStreamResource(inputStream);

        // Resource resource = new ServletContextResource(servletContext,
        // "/WEB-INF/sample.txt");

        // System.out.println(resource);
        // System.out.println(resourceB);

        // BufferedReader reader = null;
        // try {
        // reader = new BufferedReader(new FileReader("sample.txt"));
        // String line;
        // while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // if (reader != null) {
        // try {
        // reader.close(); // Must manually close the resource
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }

        try (BufferedReader reader = new BufferedReader(new FileReader("sample.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource resource = resourceLoader.getResource("file:C:/data/sample.bin");
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] data = inputStream.readAllBytes();
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));
                FileWriter writer = new FileWriter("output.txt")) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
