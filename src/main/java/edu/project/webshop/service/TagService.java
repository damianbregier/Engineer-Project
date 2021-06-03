package edu.project.webshop.service;

import edu.project.webshop.entity.Course;
import edu.project.webshop.entity.Tag;
import edu.project.webshop.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTagById(int id) {
        Optional<Tag> optional = tagRepository.findById(id);
        Tag tag = null;
        if (optional.isPresent()) {
            tag = optional.get();
        } else {
            throw new RuntimeException(" Tag o takim id nie został znaleziony :: " + id);
        }
        return tag;
    }
}
