using PortalCG.Models.JsonModels;
using System.Collections.Generic;

namespace PortalCG.Models.ViewModels
{
    public class TeacherIndexViewModel
    {
        public Course Course { get; set; }
        public Discipline Discipline { get; set; }
        public List<Teacher> TeacherList { get; set; }
    }
}