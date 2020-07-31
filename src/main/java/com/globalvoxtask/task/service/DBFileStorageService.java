package com.globalvoxtask.task.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.globalvoxtask.task.exception.FileStorageException;
import com.globalvoxtask.task.exception.MyFileNotFoundException;
import com.globalvoxtask.task.model.DBFile;
import com.globalvoxtask.task.repository.DBFileRepository;

@Service
public class DBFileStorageService
{
	private static final Logger logger = LoggerFactory.getLogger(DBFileStorageService.class);

	@Autowired
	private DBFileRepository dbFileRepository;

	// service method to validate & save file
	public DBFile storeFile(MultipartFile file) throws FileStorageException
	{
		String loggerMessage = "";

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// file type will be here
		// we can validate file from backend
		String fileType = file.getContentType();

		// only images allowed
		if (fileType.indexOf("image") != -1)
		{
			try
			{
				DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
				return dbFileRepository.save(dbFile);
			}
			catch (IOException ex)
			{
				String errorMsg = "Could not store file " + fileName + ". Please try again!";
				logger.error(errorMsg, ex);
				throw new FileStorageException(errorMsg, ex);
			}
		}
		else
		{
			loggerMessage = "Invalid file type " + fileType + ". Please try again!";

			logger.error(loggerMessage);
			throw new FileStorageException(loggerMessage);
		}

	}

	// service method to fetch file
	public DBFile getFile(String fileId)
	{
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
}
