package com.globalvoxtask.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.globalvoxtask.task.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String>
{

}
