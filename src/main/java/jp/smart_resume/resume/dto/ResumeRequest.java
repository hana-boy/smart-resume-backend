package jp.smart_resume.resume.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Data transfer object representing the full structure of a resume request.
 * This class is used as the input for resume generation via the REST API.
 */
@Data
public class ResumeRequest {
    /** Basic personal information of the user. */
    private BasicInfo basicInfo;

    /** Summary or professional overview of the user. */
    private Summary summary;

    /** List of the user's skills. */
    private List<Skill> skills;

    /** List of the user's work history, including companies and projects. */
    private List<WorkHistory> workHistory;

    /** List of certifications obtained by the user. */
    private List<Certification> certifications;

    /** Activities such as study groups and self-learning. */
    private Activities activities;

    /**
     * Represents basic personal information.
     */
    @Data
    public static class BasicInfo {
        private String name;
        private String createdAt;
        private String email;
        private Map<String, String> links;
    }

    /**
     * Represents a professional summary or overview.
     */
    @Data
    public static class Summary {
        private String overview;
        private List<String> highlights;
    }

    /**
     * Represents a specific skill possessed by the user.
     */
    @Data
    public static class Skill {
        private String category;
        private String name;
        private String experience;
        private String level;
    }

    /**
     * Represents a record of employment, including projects.
     */
    @Data
    public static class WorkHistory {
        private Company company;
        private List<Project> projects;
    }

    /**
     * Represents detailed information about a company.
     */
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

    /**
     * Represents details of a project within a company.
     */
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

    /**
     * Represents a group of technologies used in a project.
     */
    @Data
    public static class Technologies {
        private List<String> languages;
        private List<String> frameworks;
        private List<String> db;
        private List<String> os;
        private List<String> infra;
        private List<String> tools;
    }

    /**
     * Represents a time period using "from" and "to" fields.
     */
    @Data
    public static class Period {
        private String from;
        private String to;
    }

    /**
     * Represents a professional certification.
     */
    @Data
    public static class Certification {
        private String name;
        private String date;
    }

    /**
     * Represents study groups and self-learning activities.
     */
    @Data
    public static class Activities {
        private List<String> studyGroups;
        private List<String> selfLearning;
    }
}
