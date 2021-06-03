package edu.project.webshop.repository;

import edu.project.webshop.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByTagName(String name);
    Course getCourseById(int id);
}
