using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.WebAPIReference;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class TeacherController : Controller
    {
        public async Task<ActionResult> AllTeachers()
        {
            List<Teacher> DisciplineList = await TeacherWebAPI.GetAllTeachers();
            DisciplineList.ForEach(x =>
            {
                x.ShowDisciplines = true;
            });
            return View(DisciplineList);
        }

        public async Task<ActionResult> AllDisciplines(int id)
        {
            Teacher teacher = await TeacherWebAPI.GetTeacherById(id);
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetDisciplinesByTeacher(id);
            DisciplineList.ForEach(x =>
            {
                x.ShowTeachers = false;
                x.IdTeacher = id;
            });
            DisciplineIndexViewModel disciplineIndex = new DisciplineIndexViewModel
            {
                Teacher = teacher,
                DisciplineList = DisciplineList
            };
            return View(disciplineIndex);
        }
    }
}