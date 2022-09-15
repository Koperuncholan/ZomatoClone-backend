package com.nseit.ZomatoClone.controller;


import com.nseit.ZomatoClone.model.File;
import com.nseit.ZomatoClone.response.APIResponse;
import com.nseit.ZomatoClone.service.DishService;
import com.nseit.ZomatoClone.service.FileService;
import com.nseit.ZomatoClone.utils.FileDownloadUtil;
import com.nseit.ZomatoClone.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(value = {"http://localhost:3000"})
@RequestMapping("/api/")
public class FileController {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private DishService dishService;

    @Autowired
    private FileService fileService;

    @Autowired
    private APIResponse apiResponse;

    @PostMapping("/uploadFile")
    public ResponseEntity<APIResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//
//        String filecode = FileUploadUtil.saveFile(fileName, multipartFile, resourceLoader);
//
//        FileDownloadUtil downloadUtil = new FileDownloadUtil();
//        Resource resource = null;
//        try {
//            resource = downloadUtil.getFileAsResource(filecode);
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().build();
//        }


        File file = new File();
//        file.setLocation(FileUploadUtil.getFilePath(fileName) + "/" + resource.getFilename());
        file.setImage(ImageUtility.compressImage(multipartFile.getBytes()));
        file = fileService.uploadFile(file);

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(file);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
