package com.nseit.ZomatoClone.service;

import com.nseit.ZomatoClone.model.File;
import com.nseit.ZomatoClone.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File uploadFile(File file){
        return fileRepository.save(file);
    }

}
