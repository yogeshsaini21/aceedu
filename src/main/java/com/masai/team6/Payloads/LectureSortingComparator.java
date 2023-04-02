package com.masai.team6.Payloads;

import java.util.Comparator;

import com.masai.team6.Entities.Lecture;

public class LectureSortingComparator implements Comparator<Lecture> {
 
    @Override
    public int compare(Lecture lect1, Lecture lect2) {
        return lect1.getStartTime().compareTo(lect2.getStartTime());
    }
}