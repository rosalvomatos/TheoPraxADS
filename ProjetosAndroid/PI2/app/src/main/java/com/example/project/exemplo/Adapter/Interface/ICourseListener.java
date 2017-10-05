package com.example.project.exemplo.Adapter.Interface;

import com.example.project.exemplo.Mapper.Json.CourseJson;

public interface ICourseListener {
    void showAuthorizationAct(CourseJson courseJson);
    void showTeacher(CourseJson courseJson);
    void showCoursePedagogicalPlan(CourseJson courseJson);
    void showDiscipline(CourseJson courseJson);
    void showBibliographicCollection(CourseJson courseJson);
    void showCurriculum(CourseJson courseJson);
}
