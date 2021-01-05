package edu.project.webshop.service;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Tag;
import edu.project.webshop.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
