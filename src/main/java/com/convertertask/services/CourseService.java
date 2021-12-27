package com.convertertask.services;

import com.convertertask.entities.Course;
import com.convertertask.entities.Valute;
import com.convertertask.repositories.CourseRepository;
import com.convertertask.repositories.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CourseService {

    @Autowired
    CbrfService cbrfService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ValuteRepository valuteRepository;

    @Autowired
    ParserService parserService;

    public Date getLastDate(){
        Sort sort = Sort.by("date").descending();
        Pageable pageable =  PageRequest.of(0, 1, sort);
        Optional<Course> course =  courseRepository.findAll(pageable).get().findFirst();
        if (course.isPresent()){
            return course.get().getDate();
        }
        else {
            return null;
        }
    }

    public Optional<Course> getActualCourse(Valute valute){
        Optional<Course> course = Optional.ofNullable(courseRepository.findByValute(valute));
        return course;
    }

    public void updateCourses() throws ChangeSetPersister.NotFoundException {
        Document document = cbrfService.getActualData();
        NodeList list = document.getElementsByTagName("Valute");

        for (int i = 0;i<list.getLength();i++){
             Course course = parserService.parseCourse(list.item(i));
             Valute valuteFromXml = parserService.parseValute(list.item(i));

             Optional<Valute> actualValute = Optional.ofNullable(valuteRepository.findByCharCode(valuteFromXml.getCharCode()).get(0));
             if (actualValute.isPresent()){
                 course.setValute(actualValute.get());
                 courseRepository.save(course);
             }
             else {
                 throw new ChangeSetPersister.NotFoundException();
             }
        }
    }
}
