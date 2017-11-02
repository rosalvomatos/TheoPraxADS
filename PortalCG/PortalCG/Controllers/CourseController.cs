﻿using PortalCG.Models;
using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
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
            CourseList.ForEach(x => { x.OptionRoute = (int)CourseOptionRouteEnum.ALL; });
            return View(CourseList);
        }

        public async Task<ActionResult> GraduationCourse()
        {
            List<Course> CourseList = await CourseWebAPI.GetCoursesByType("GetGrad");
            return View(CourseList);
        }

        public async Task<ActionResult> AllDisciplines(int id)
        {
            Course course = await CourseWebAPI.GetCourseById(id);
            List<Discipline> DisciplineList = await DisciplineWebAPI.GetDisciplinesByCourse(id);
            DisciplineList.ForEach(x => { x.clickable = false; });
            DisciplineIndexViewModel disciplineIndex = new DisciplineIndexViewModel
            {
                Course = course,
                DisciplineList = DisciplineList
            };
            return View(disciplineIndex);
        }

        public ActionResult UploadFile(int idCourse, string courseName, int option)
        {
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
                        string fileName = SetFileName(type);
                        fileName = fileName + "_" + course.IdCourse + ".pdf";
                        UploadFileUtil.FTPUpload(fileName, file);
                    }
                }
            }
            //CHANGE
            string action = "";
            switch (course.OptionRoute)
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
            if (course.OptionRoute != (int)CourseOptionRouteEnum.INDIVIDUAL)
            {
                return RedirectToAction(action, "Course");
            }
            return RedirectToAction(action, "Course", new { id = course.IdCourse });
        }

        string SetFileName(string idType)
        {
            int type = Int32.Parse(idType);
            return ((TypeCourseFileEnum)type).ToString();
        }

    }
}