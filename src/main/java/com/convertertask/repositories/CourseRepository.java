package com.convertertask.repositories;

import com.convertertask.entities.Course;
import com.convertertask.entities.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    public Course findByValute(Valute valute);

}
