using PortalCG.Models.JsonModels;
using System.Collections.Generic;

namespace PortalCG.Models.ViewModels
{
    public class DisciplineIndexViewModel
    {
        public Course Course { get; set; }
        public Teacher Teacher{ get; set; }
        public List<Discipline> DisciplineList { get; set; }
    }
}