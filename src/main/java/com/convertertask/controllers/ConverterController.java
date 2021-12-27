package com.convertertask.controllers;


import com.convertertask.entities.Convertation;
import com.convertertask.entities.Course;
import com.convertertask.entities.Valute;
import com.convertertask.services.ConverterService;
import com.convertertask.services.CourseService;
import com.convertertask.services.HistoryService;
import com.convertertask.services.ValuteService;
import com.convertertask.models.ConvertModel;
import com.convertertask.util.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
public class ConverterController {

    @Autowired
    ValuteService valuteService;

    @Autowired
    ConverterService converterService;

    @Autowired
    CourseService courseService;

    @Autowired
    HistoryService historyService;

    @Autowired
    DateMapper dateMapper;

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/getNames")
    public ResponseEntity<List<String>> getNamesAndCodes(){
        List<String> names = valuteService.getValutesNamesAndCodes();
        return names != null &&  !names.isEmpty()
                ? new ResponseEntity<>(names, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody ConvertModel model){

        String f = model.getFrom().split(" ")[0];
        Valute from = valuteService.getByCharCode(f);
        Optional<Course> fromCourse = courseService.getActualCourse(from);
        try {
            if (fromCourse.isEmpty()) {
                courseService.updateCourses();
                fromCourse = courseService.getActualCourse(from);
            }
        }
        catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String t = model.getTo().split(" ")[0];
        Valute to = valuteService.getByCharCode(t);
        Optional<Course> toCourse = courseService.getActualCourse(to);

        //Add convertation in history
        String res = converterService.convert(model.getAmount(),fromCourse.get(),toCourse.get()).toString();

        Convertation convertation = new Convertation(model.getFrom(),
                model.getTo(),
                Double.parseDouble(res),
                dateMapper.getSqlDate(new Date()),
                fromCourse.get()
        );

        historyService.addConvertation(convertation);
        return new ResponseEntity<String>(res,HttpStatus.OK);
    }

    @GetMapping(path = "/getHistory",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Convertation> getHistory(){
         List<Convertation> list = historyService.getHistory();
         return list;
    }

    @GetMapping("/get")
    public String onAuth(){
        return "You should be auth to see this";
    }

}
