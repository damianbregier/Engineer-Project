package edu.project.webshop.controller;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Tag;
import edu.project.webshop.entity.User;
import edu.project.webshop.service.CourseService;
import edu.project.webshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    TagService tagService;

    @GetMapping("/course_page/{pageNo}")
    public String findPaginatedCourses(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "coursesView";
    }

    @GetMapping("/coursesView")
    public String viewHomePage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Tag> listTags = tagService.getAllTags();
        modelAndView.addObject("listTags", listTags);

        return findPaginatedCourses(1,"name", "asc", model);
    }

    @GetMapping(value="/showNewCourseForm")
    public ModelAndView showNewCourseForm(){
        ModelAndView modelAndView = new ModelAndView();
        List <Tag> listTags = tagService.getAllTags();
        Course course = new Course();
        modelAndView.addObject("course", course);
        modelAndView.addObject("listTags", listTags);
        modelAndView.setViewName("new_course");
        return modelAndView;
    }

    @PostMapping("/saveCourse")
    public String saveCourse( @ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/coursesView";
    }

    @GetMapping("/showCourseFormForUpdate/{id}")
    public String showCourseFormForUpdate(@PathVariable ( value = "id") int id, Model model){

        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        List <Tag> listTags = tagService.getAllTags();
        model.addAttribute("listTags", listTags);
        return "update_course";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(@RequestParam int tagId, @ModelAttribute("course") Course course) {

        Tag tag = tagService.getTagById(tagId);
        course.setTag(tag);

        courseService.saveCourse(course);

        return "redirect:/coursesView";
    }


    @GetMapping("/deleteCourse/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id) {

        this.courseService.deleteCourseById(id);
        return "redirect:/coursesView";
    }

}
