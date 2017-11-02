using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Util;
using PortalCG.Util.Enum;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class CourseController : Controller
    {
        // GET: Course
        public ActionResult Index()
        {
            return View();
        }

        public async Task<ActionResult> AllCourses()
        {
            List<Course> CourseList = await CourseWebAPI.GetAllCourses();
            return View(CourseList);
        }

        public async Task<ActionResult> GraduationCourse()
        {
            List<Course> CourseList = await CourseWebAPI.GetCoursesByType("GetGrad");
            return View(CourseList);
        }


        public ActionResult UploadFile(int idCourse, string courseName)
        {
            CourseUploadFileModel course = new CourseUploadFileModel()
            {
                IdCourse = idCourse,
                NameCourse = courseName
            };
            return View(course);
        }

        [HttpPost]
        public ActionResult UploadFile(CourseUploadFileModel course)
        {
            for (int i = 0; i < Request.Files.Count; i++)
            {
                HttpPostedFileBase arquivo = Request.Files[i];

                if (arquivo.ContentLength > 0)
                {
                    if (arquivo.ContentType.Equals("application/pdf"))
                    {
                        var type = Request.Files.Keys[i];
                        string fileName = SetFileName(type);
                        fileName = fileName + "_" + course.IdCourse + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, arquivo);
                    }
                }
            }
            return null;
        }

        string SetFileName(string idType)
        {
            int type = Int32.Parse(idType);
            return ((TypeCourseFileEnum)type).ToString();
        }

    }
}