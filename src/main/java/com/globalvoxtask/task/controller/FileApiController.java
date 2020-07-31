package com.globalvoxtask.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.globalvoxtask.task.exception.FileStorageException;
import com.globalvoxtask.task.model.DBFile;
import com.globalvoxtask.task.service.DBFileStorageService;

@RestController
public class FileApiController extends BaseController
{

	private static final Logger logger = LoggerFactory.getLogger(FileApiController.class);

	@Autowired
	private DBFileStorageService dbFileStorageService;

	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{
		DBFile dbFile;
		try
		{
			dbFile = dbFileStorageService.storeFile(file);
			return ResponseEntity.ok()
					.body("file saved with ID :" + dbFile.getId());
		}
		catch (FileStorageException e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/view-file/{fileId}")
	public ResponseEntity<Resource> viewFile(@PathVariable String fileId)
	{
		logger.info("image file requested. ID = " + fileId);

		// find file from database by id
		DBFile dbFile;
		try
		{
			dbFile = dbFileStorageService.getFile(fileId);
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(dbFile.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
					.body(new ByteArrayResource(dbFile.getData()));
		}
		catch (Exception e)
		{
			logger.error("image file requested. ID = " + fileId + " not found.. cause =", e.getMessage());
			return new ResponseEntity<Resource>(null, null, HttpStatus.NOT_FOUND);
		}

	}

}
