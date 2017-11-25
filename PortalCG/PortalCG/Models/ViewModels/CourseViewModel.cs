using PortalCG.Models.JsonModels;
using System.Collections.Generic;

namespace PortalCG.Models.ViewModels
{
    public class DetailsCourseViewModel
    {
        public Course Course { get; set; }
        public List<Discipline> DisciplineList { get; set; }
        public List<Teacher> TeacherList { get; set; }
    }
}