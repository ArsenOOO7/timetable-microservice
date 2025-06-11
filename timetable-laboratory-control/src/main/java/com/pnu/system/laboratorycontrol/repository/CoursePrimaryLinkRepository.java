package com.pnu.system.laboratorycontrol.repository;

import com.pnu.system.laboratorycontrol.domain.CoursePrimaryLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePrimaryLinkRepository extends JpaRepository<CoursePrimaryLink, String> {

    List<CoursePrimaryLink> findAllByCourseId(String courseId);

}
