namespace PortalCG.Models.JsonModels
{
    public class Discipline
    {
        public string Codigo { get; set; }
        public string Nome { get; set; }
        public string CH { get; set; }
        public int? OptionRoute { get; set; }
        public string CodeCourse { get; set; }
        public int? CourseOptionRoute { get; set; }
        public string CodeTeacher { get; set; }

        public bool ShowTeachers { get; set; }
    }
}