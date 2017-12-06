using PortalCG.Extensions;
using PortalCG.Models;
using PortalCG.Util;
using PortalCG.Util.Enum;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    //[Authorize(Users = "*")]
    public class InstitutionalController : Controller
    {

        public ActionResult UploadFile(int id)
        {
            ViewBag.Url = Url.Action("UploadFile");
            InstitutionalUploadFileModel model = new InstitutionalUploadFileModel()
            {
                IdType = id
            };
            return View(model);
        }

        [HttpPost]
        public ActionResult UploadFile(InstitutionalUploadFileModel model)
        {
            for (int i = 0; i < Request.Files.Count; i++)
            {
                HttpPostedFileBase arquivo = Request.Files[i];

                if (arquivo.ContentLength > 0)
                {
                    if (arquivo.ContentType.Equals("application/pdf"))
                    {
                        string fileName = SetFileName(model.IdType) + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, arquivo);
                    }
                }
            }
            return RedirectToAction("AllCourses", "Course").Success("Arquivo enviado com sucesso");
        }

        string SetFileName(int idType)
        {
            return ((TypeInstitutionalFileEnum)idType).ToString();
        }
    }
}