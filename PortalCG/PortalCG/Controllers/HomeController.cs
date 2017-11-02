using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class HomeController : Controller
    {
        //public async Task<ActionResult> Index()
        //{
        //    List<Course> CourseList = await CourseWebAPI.GetAllCourses();
        //    return View(CourseList);
        //}

        public ActionResult Index()
        {
            return View();
        }
        
    }
}