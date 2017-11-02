using PortalCG.Models.JsonModels;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class HomeController : Controller
    {
        public async Task<ActionResult> Index()
        {
            List<Course> CourseList = await CourseWebAPI.GetAllCourses();
            return View(CourseList);
        }
        
    }
}