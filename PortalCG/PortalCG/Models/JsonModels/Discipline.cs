namespace PortalCG.Models.JsonModels
{
    public class Discipline
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public int CH { get; set; }
        public int? OptionRoute { get; set; }
        public int? IdCourse { get; set; }
        public int? CourseOptionRoute { get; set; }
        public int? IdTeacher { get; set; }

        public bool ShowTeachers { get; set; }
    }
}