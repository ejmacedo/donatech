package com.donatech.projetodonatech.controller;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.donatech.projetodonatech.exceptions.StorageFileNotFoundException;
import com.donatech.projetodonatech.services.StorageService;



@Controller
@RequestMapping("/api/files")
public class ImageController {
    
	
	private final StorageService storageService;

	public ImageController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> listUploadedFiles(Model model) throws IOException {

		return new ResponseEntity<Object>(storageService.loadAll(), HttpStatus.OK);
		
	}

	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)  
	@ResponseBody
	public ResponseEntity<?> serveFile(@PathVariable(value = "name") String filename) {
		 Resource file = storageService.loadAsResource(filename);
	        if(file == null){
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
	        }
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}