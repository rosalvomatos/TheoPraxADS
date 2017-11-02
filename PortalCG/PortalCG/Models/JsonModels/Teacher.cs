using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PortalCG.Models.JsonModels
{
    public class Teacher
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string ModContrato { get; set; }
        public int? IdCourse { get; set; }
        public int? CourseOptionRoute { get; set; }

        public bool ShowDisciplines { get; set; }
    }
}