using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.Util;
using PortalCG.Util.Enum;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class DisciplineController : Controller
    {
        public async Task<ActionResult> AllDisciplines()
        {
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetAllDisciplines();
            DisciplineList.ForEach(x =>
            {
                x.OptionRoute = (int)DisciplineOptionRouteEnum.ALL;
                x.ShowTeachers = true;
            });
            return View(DisciplineList);
        }

        public async Task<ActionResult> AllTeachers(int id)
        {
            Discipline discipline = await DisciplineWebAPI.GetDisciplineById(id);
            List<Teacher> TeacherList = await TeacherWebAPI.GetTeachersByDiscipline(id);
            TeacherList.ForEach(x =>
            {
                x.ShowDisciplines = false;
                x.IdCourse = id;
            });
            TeacherIndexViewModel teacherIndex = new TeacherIndexViewModel
            {
                Discipline = discipline,
                TeacherList = TeacherList
            };
            return View(teacherIndex);
        }

        public ActionResult UploadFile(int idDiscipline, string disciplineName, int? courseOption, int? idCourse, int? option)
        {
            DisciplineUploadFileModel course = new DisciplineUploadFileModel()
            {
                IdDiscipline = idDiscipline,
                NameDiscipline = disciplineName,
                IdCourse = idCourse,
                CourseOptionRoute = courseOption,
                OptionRoute = option
            };
            return View(course);
        }

        [HttpPost]
        public ActionResult UploadFile(DisciplineUploadFileModel discipline)
        {
            for (int i = 0; i < Request.Files.Count; i++)
            {
                HttpPostedFileBase arquivo = Request.Files[i];

                if (arquivo.ContentLength > 0)
                {
                    if (arquivo.ContentType.Equals("application/pdf"))
                    {
                        string fileName = discipline.IdDiscipline + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, arquivo);
                    }
                }
            }
            if (discipline.CourseOptionRoute != null)
            {
                string action = CourseController.getRoute((int)discipline.CourseOptionRoute);

                if (discipline.CourseOptionRoute != (int)CourseOptionRouteEnum.INDIVIDUAL)
                {
                    return RedirectToAction(action, "Course");
                }
                return RedirectToAction(action, "Course", new { id = (int)discipline.IdCourse });
            }
            else
            {
                return RedirectToAction("AllDisciplines", "Discipline");
            }
        }
        
    }
}