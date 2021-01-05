package edu.project.webshop.controller;

import edu.project.webshop.entity.Course;
import edu.project.webshop.service.CourseService;
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

        return findPaginatedCourses(1,"name", "asc", model);
    }

    @GetMapping(value="/showNewCourseForm")
    public ModelAndView showNewCourseForm(){
        ModelAndView modelAndView = new ModelAndView();
        Course course = new Course();
        modelAndView.addObject("course", course);
        modelAndView.setViewName("new_course");
        return modelAndView;
    }

    @PostMapping("/showNewCourseForm")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/coursesView";
    }

    @GetMapping("/showCourseFormForUpdate/{id}")
    public String showCourseFormForUpdate(@PathVariable ( value = "id") int id, Model model) {

        // get employee from the service
        Course course = courseService.getCourseById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id) {

        // call delete employee method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }

}
