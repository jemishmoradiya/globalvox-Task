package com.globalvoxtask.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class DBFile
{

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;

	public DBFile()
	{

	}

	public DBFile(String fileName, String fileType, byte[] data)
	{
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
}
