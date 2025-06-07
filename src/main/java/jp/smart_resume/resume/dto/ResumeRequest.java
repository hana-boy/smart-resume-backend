package jp.smart_resume.resume.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ResumeRequest {
    private BasicInfo basicInfo;
    private Summary summary;
    private List<Skill> skills;
    private List<WorkHistory> workHistory;
    private List<Certification> certifications;
    private Activities activities;

    @Data
    public static class BasicInfo {
        private String name;
        private String createdAt;
        private String email;
        private Map<String, String> links;
    }

    @Data
    public static class Summary {
        private String overview;
        private List<String> highlights;
    }

    @Data
    public static class Skill {
        private String category;
        private String name;
        private String experience;
        private String level;
    }

    @Data
    public static class WorkHistory {
        private Company company;
        private List<Project> projects;
    }

    @Data
    public static class Company {
        private String name;
        private String employmentType;
        private String industry;
        private String capital;
        private int employees;
        private boolean listed;
        private Period period;
    }

    @Data
    public static class Project {
        private String title;
        private Period period;
        private int teamSize;
        private int engineers;
        private String description;
        private List<String> phases;
        private String role;
        private String challenges;
        private String contributions;
        private String outcomes;
        private Technologies technologies;
    }

    @Data
    public static class Technologies {
        private List<String> languages;
        private List<String> frameworks;
        private List<String> db;
        private List<String> os;
        private List<String> infra;
        private List<String> tools;
    }

    @Data
    public static class Period {
        private String from;
        private String to;
    }

    @Data
    public static class Certification {
        private String name;
        private String date;
    }

    @Data
    public static class Activities {
        private List<String> studyGroups;
        private List<String> selfLearning;
    }
}
