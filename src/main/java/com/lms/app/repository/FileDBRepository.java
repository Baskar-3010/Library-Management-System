package com.lms.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.app.entities.FileDB;



@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
