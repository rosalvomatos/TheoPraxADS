using PortalCG.Models;
using PortalCG.Models.JsonModels;
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
            List<string> urlList = new List<string>();
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
                        var uploadPath = Server.MapPath("~/Content/Uploads");
                        bool exists = Directory.Exists(uploadPath);
                        if (!exists)
                            Directory.CreateDirectory(uploadPath);
                        var c = arquivo;
                        string caminhoArquivo = Path.Combine(@uploadPath, fileName);
                        arquivo.SaveAs(caminhoArquivo);
                        urlList.Add(caminhoArquivo);
                    }
                }
            }
            if (urlList?.Count > 0)
            {
                teste(urlList);
            }
            return null;
        }

        string SetFileName(string idType)
        {
            int type = Int32.Parse(idType);
            return ((TypeCourseFileEnum)type).ToString();
        }

        void teste(List<string> urlList)
        {
            FtpWebRequest request = (FtpWebRequest)WebRequest.Create("ftp://ftp.dlptest.com/");
            request.Method = WebRequestMethods.Ftp.UploadFile;

            // This example assumes the FTP site uses anonymous logon.  
            request.Credentials = new NetworkCredential("dlpuser@dlptest.com", "fwRhzAnR1vgig8s");

            foreach (var item in urlList)
            {
                // Copy the contents of the file to the request stream.  
                StreamReader sourceStream = new StreamReader(item);
                byte[] fileContents = Encoding.UTF8.GetBytes(sourceStream.ReadToEnd());
                sourceStream.Close();
                request.ContentLength = fileContents.Length;

                Stream requestStream = request.GetRequestStream();
                requestStream.Write(fileContents, 0, fileContents.Length);
                requestStream.Close();

                FtpWebResponse response = (FtpWebResponse)request.GetResponse();

                response.Close();
            }
        }
    }
}