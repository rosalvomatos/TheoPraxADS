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
        //[Authorize(Users = "*")]
        public async Task<ActionResult> AllTeachers()
        {
            ViewBag.Url = Url.Action("AllTeachers");
            List<Teacher> DisciplineList = await TeacherWebAPI.GetAllTeachers();
            DisciplineList.ForEach(x =>
            {
                x.ShowDisciplines = true;
            });
            return View(DisciplineList);
        }

        public async Task<ActionResult> AllDisciplines(string code)
        {
            ViewBag.Url = Url.Action("AllDisciplines", "Discipline");
            Teacher teacher = await TeacherWebAPI.GetTeacherById(code);
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetDisciplinesByTeacher(code);
            DisciplineList.ForEach(x =>
            {
                x.ShowTeachers = false;
                x.CodeTeacher = code;
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