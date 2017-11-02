using PortalCG.Models;
using PortalCG.Models.JsonModels;
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
        // GET: Discipline
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult UploadFile(int idDiscipline, string disciplineName, int? courseOption, int? idCourse)
        {
            DisciplineUploadFileModel course = new DisciplineUploadFileModel()
            {
                IdDiscipline = idDiscipline,
                NameDiscipline = disciplineName,
                IdCourse = idCourse,
                CourseOptionRoute = courseOption
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