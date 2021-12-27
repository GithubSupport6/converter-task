package com.convertertask.services;

import com.convertertask.entities.Course;
import com.convertertask.entities.Valute;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class ParserService {

    public String getTagValue(Node node, String name){
        NodeList list = node.getChildNodes();
        for (int index = 0;index<list.getLength();index++){
            if (list.item(index).getNodeName() == name){
                return list.item(index).getTextContent();
            }
        }
        return null;
    }

    public Valute parseValute(Node node){
        Node currentChild = node.getFirstChild();


        int numCode = Integer.parseInt(currentChild.getTextContent());

        currentChild = currentChild.getNextSibling();
        String charCode = currentChild.getTextContent();

        currentChild = currentChild.getNextSibling();
        currentChild = currentChild.getNextSibling();
        String name = currentChild.getTextContent();

        Valute valute = new Valute(0,numCode,charCode,name);
        return valute;
    }

    public Course parseCourse(Node node){
        Course course = new Course();
        int nominal = Integer.parseInt(getTagValue(node,"Nominal"));
        double value = Double.parseDouble(getTagValue(node,"Value").replace(',','.'));
        course = new Course(nominal,value);
        return course;
    }

}
