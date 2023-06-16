package com.ibm.rest;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
        // Construct the file path based on the filename
        Path filePath = Paths.get("images/" + filename);

        // Load the file as a resource
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            // Set the appropriate content type
            String contentType = "image/jpeg";

            // Return the image with proper headers
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(resource);
        } else {
            // Handle the case when the image does not exist
            return ResponseEntity.notFound().build();
        }
    }
}