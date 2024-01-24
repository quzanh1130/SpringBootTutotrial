package com.tutorial.apidemo.SpringBootTutorial.controllers;

import com.tutorial.apidemo.SpringBootTutorial.models.ResponseObject;
import com.tutorial.apidemo.SpringBootTutorial.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.xml.xpath.XPath;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //Inject Storage Service here
    @Autowired
    private IStorageService storageService;
    // This controller receive file/image from client
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            //save files to a folder => use a service
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "upload file successfully", generatedFileName)
            );
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("fail", exception.getMessage(), "")
            );
        }
    }

    //get image's url
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("")
    //How to load all upload files
    public ResponseEntity<ResponseObject> getUploadFiles(){
        try{
            List<String> urls = storageService.loadAll().
                    map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List file successfully", urls));
        } catch ( Exception exception){
            return ResponseEntity.ok(new ResponseObject("fail", "List file successfully", new String[] {}));
        }
    }

}
