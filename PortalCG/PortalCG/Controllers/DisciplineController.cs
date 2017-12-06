using PortalCG.Extensions;
using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.Util;
using PortalCG.Util.Enum;
using PortalCG.WebAPIReference;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    //[Authorize(Users = "*")]
    public class DisciplineController : Controller
    {
        public async Task<ActionResult> AllDisciplines()
        {
            ViewBag.Url = Url.Action("AllDisciplines");
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetAllDisciplines();
            DisciplineList.ForEach(x =>
            {
                x.OptionRoute = (int)DisciplineOptionRouteEnum.ALL;
                x.ShowTeachers = true;
            });
            return View(DisciplineList);
        }

        public async Task<ActionResult> AllTeachers(string code)
        {
            ViewBag.Url = Url.Action("AllTeachers", "Teacher");
            Discipline discipline = await DisciplineWebAPI.GetDisciplineById(code);
            List<Teacher> TeacherList = await TeacherWebAPI.GetTeachersByDiscipline(code);
            TeacherList.ForEach(x =>
            {
                x.ShowDisciplines = false;
                x.CodeCourse = code;
            });
            TeacherIndexViewModel teacherIndex = new TeacherIndexViewModel
            {
                Discipline = discipline,
                TeacherList = TeacherList
            };
            return View(teacherIndex);
        }

        public ActionResult UploadFile(string codeDiscipline, string disciplineName, int? courseOption, string codeCourse, int? option, string codeTeacher)
        {
            ViewBag.Url = Url.Action("AllDisciplines");
            DisciplineUploadFileModel course = new DisciplineUploadFileModel()
            {
                CodeDiscipline = codeDiscipline,
                NameDiscipline = disciplineName,
                CodeCourse = codeCourse,
                CourseOptionRoute = courseOption,
                OptionRoute = option,
                CodeTeacher = codeTeacher
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
                        string fileName = discipline.CodeDiscipline + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, arquivo);
                    }
                }
            }
            if (discipline.CourseOptionRoute != null)
            {
                string action = CourseController.GetRoute((int)discipline.CourseOptionRoute);

                if (discipline.CourseOptionRoute != (int)CourseOptionRouteEnum.INDIVIDUAL)
                {
                    return RedirectToAction(action, "Course").Success("Arquivo enviado com sucesso");
                }
                return RedirectToAction(action, "Course", new { code = discipline.CodeCourse }).Success("Arquivo enviado com sucesso");
            }
            else if (discipline.CodeTeacher != null)
            {
                return RedirectToAction("AllTeachers", "Teacher").Success("Arquivo enviado com sucesso");
            }
            else
            {
                return RedirectToAction("AllDisciplines", "Discipline").Success("Arquivo enviado com sucesso");
            }
        }

    }
}