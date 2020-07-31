package com.globalvoxtask.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
