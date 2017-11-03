using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.Util;
using PortalCG.Util.Enum;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
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
            ViewBag.Url = Url.Action("AllCourses");
            List<Course> CourseList = await CourseWebAPI.GetAllCourses();
            CourseList.ForEach(x => { x.OptionRoute = (int)CourseOptionRouteEnum.ALL; });
            return View(CourseList);
        }

        public async Task<ActionResult> GraduationCourses()
        {
            ViewBag.Url = Url.Action("GraduationCourses");
            List<Course> CourseList = await CourseWebAPI.GetCoursesByType("GetGrad");
            CourseList.ForEach(x => { x.OptionRoute = (int)CourseOptionRouteEnum.GRADUATION; });
            return View(CourseList);
        }

        public async Task<ActionResult> PostGraduateCourses()
        {
            ViewBag.Url = Url.Action("PostGraduateCourses");
            List<Course> CourseList = await CourseWebAPI.GetCoursesByType("GetPosGrad");
            CourseList.ForEach(x => { x.OptionRoute = (int)CourseOptionRouteEnum.POSTGRADUATE; });
            return View(CourseList);
        }

        public async Task<ActionResult> AllDisciplines(int id, int option)
        {
            ViewBag.Url = Url.Action("AllDisciplines", "Discipline");
            Course course = await CourseWebAPI.GetCourseById(id);
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetDisciplinesByCourse(id);
            DisciplineList.ForEach(x =>
            {
                x.ShowTeachers = false;
                x.CourseOptionRoute = option;
                x.IdCourse = id;
            });
            DisciplineIndexViewModel disciplineIndex = new DisciplineIndexViewModel
            {
                Course = course,
                DisciplineList = DisciplineList
            };
            return View(disciplineIndex);
        }

        public async Task<ActionResult> AllTeachers(int id)
        {
            ViewBag.Url = Url.Action("AllTeachers", "Teacher");
            Course course = await CourseWebAPI.GetCourseById(id);
            List<Teacher> TeacherList = await TeacherWebAPI.GetTeachersByCourse(id);
            TeacherList.ForEach(x =>
            {
                x.ShowDisciplines = false;
                x.IdCourse = id;
            });
            TeacherIndexViewModel teacherIndex = new TeacherIndexViewModel
            {
                Course = course,
                TeacherList = TeacherList
            };
            return View(teacherIndex);
        }

        public async Task<ActionResult> DetailsCourse(int id)
        {
            ViewBag.Url = Url.Action("AllCourses");
            DetailsCourseViewModel detailsCourse = new DetailsCourseViewModel();
            var DisciplineList = await DisciplineWebAPI.GetDisciplinesByCourse(id);
            DisciplineList.ForEach(x =>
            {
                x.ShowTeachers = false;
                x.CourseOptionRoute = (int)CourseOptionRouteEnum.INDIVIDUAL;
                x.IdCourse = id;
            });

            detailsCourse.Course = await CourseWebAPI.GetCourseById(id);
            detailsCourse.DisciplineList = DisciplineList;
            var TeacherList = await TeacherWebAPI.GetTeachersByCourse(id);
            TeacherList.ForEach(x =>
            {
                x.ShowDisciplines = false;
                x.IdCourse = id;
            });
            detailsCourse.TeacherList = TeacherList;
            return View(detailsCourse);
        }

        public ActionResult UploadFile(int idCourse, string courseName, int option)
        {
            ViewBag.Url = Url.Action(GetRoute(option <= 3 ? option : 1));
            CourseUploadFileModel course = new CourseUploadFileModel()
            {
                IdCourse = idCourse,
                NameCourse = courseName,
                OptionRoute = option
            };
            return View(course);
        }

        [HttpPost]
        public ActionResult UploadFile(CourseUploadFileModel course)
        {
            for (int i = 0; i < Request.Files.Count; i++)
            {
                HttpPostedFileBase file = Request.Files[i];

                if (file.ContentLength > 0)
                {
                    if (file.ContentType.Equals("application/pdf"))
                    {
                        var type = Request.Files.Keys[i];
                        string fileName = GetFileName(type);
                        fileName = fileName + "_" + course.IdCourse + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, file);
                    }
                }
            }
            //CHANGE
            string action = GetRoute(course.OptionRoute);

            if (course.OptionRoute != (int)CourseOptionRouteEnum.INDIVIDUAL)
            {
                return RedirectToAction(action, "Course");
            }
            return RedirectToAction(action, "Course", new { id = course.IdCourse });
        }

        string GetFileName(string idType)
        {
            int type = Int32.Parse(idType);
            return ((TypeCourseFileEnum)type).ToString();
        }

        public static string GetRoute(int option)
        {
            string action = "";
            switch (option)
            {
                case 1:
                    action = "AllCourses";
                    break;
                case 2:
                    action = "GraduationCourses";
                    break;
                case 3:
                    action = "PostGraduateCourses";
                    break;
                case 4:
                    action = "DetailsCourse";
                    break;
                default:
                    action = "AllCourses";
                    break;
            }
            return action;
        }
    }
}