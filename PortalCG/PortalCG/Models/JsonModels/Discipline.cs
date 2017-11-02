﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PortalCG.Models.JsonModels
{
    public class Discipline
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public int CH { get; set; }
        public bool clickable { get; set; }
        public int? IdCourse { get; set; }
        public int? CourseOptionRoute { get; set; }
    }
}