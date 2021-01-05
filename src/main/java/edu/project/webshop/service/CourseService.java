package edu.project.webshop.service;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Tag;
import edu.project.webshop.entity.User;
import edu.project.webshop.repository.CourseRepository;
import edu.project.webshop.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TagRepository tagRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }


    public Course getCourseById(int id) {
        Optional<Course> optional = courseRepository.findById(id);
        Course course = null;
        if (optional.isPresent()) {
            course = optional.get();
        } else {
            throw new RuntimeException(" Kurs o takim id nie został znaleziony :: " + id);
        }
        return course;
    }

    public Course saveCourse(Course course){
        course.setName(course.getName());
        course.setDescription(course.getDescription());
        course.setAvatar(course.getAvatar());
        return courseRepository.save(course);
    }

    public void deleteCourseById(int id){
        courseRepository.deleteById(id);
    }

    public Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return courseRepository.findAll(pageable);
    }

}
