using PortalCG.Models.JsonModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PortalCG.Models.ViewModels
{
    public class DisciplineIndexViewModel
    {
        public Course Course { get; set; }
        public List<Discipline> DisciplineList { get; set; }
    }
}