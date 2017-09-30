package com.example.project.exemplo.Adapter.Interface;

import com.example.project.exemplo.Mapper.Json.CourseJson;

public interface ICourseListener {
    public void showAuthorizationAct(CourseJson courseJson);
    public void showTeacher(CourseJson courseJson);
    public void showCoursePedagogicalPlan(CourseJson courseJson);
    public void showDiscipline(CourseJson courseJson);
    public void showBibliographicCollection(CourseJson courseJson);
    public void showCurriculum(CourseJson courseJson);
}
