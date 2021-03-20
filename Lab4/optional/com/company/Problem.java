package com.company;

import java.util.*;

public class Problem {
    private List<Student> students = new LinkedList<>();
    private List<School> schools = new ArrayList<>();
    private Map<Student, List<School>> stdPrefMap = new HashMap<>();
    private Map<School, List<Student>> schPrefMap = new TreeMap<>();

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setSchPrefMap(Map<School, List<Student>> schPrefMap) {
        this.schPrefMap = schPrefMap;
    }

    public void setStdPrefMap(Map<Student, List<School>> stdPrefMap) {
        this.stdPrefMap = stdPrefMap;
    }

    public Map<School, List<Student>> getSchPrefMap() {
        return schPrefMap;
    }

    public Map<Student, List<School>> getStdPrefMap() {
        return stdPrefMap;
    }
}
