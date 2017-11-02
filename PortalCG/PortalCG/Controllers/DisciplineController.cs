using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Util;
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

        public ActionResult UploadFile(int idDiscipline, string disciplineName)
        {
            DisciplineUploadFileModel course = new DisciplineUploadFileModel()
            {
                IdDiscipline = idDiscipline,
                NameDiscipline = disciplineName
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
                        string fileName = "MATRIZ";
                        fileName = fileName + "_" + discipline.IdDiscipline + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, arquivo);
                    }
                }
            }
            return RedirectToAction("AllDisciplines", "Discipline");
        }
    }
}