using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            //return RedirectToAction("AllCourses", "Course");
            return RedirectToAction("AllLeaders", "Leader");
        }

        public ActionResult Login()
        {
            return View();
        }
    }
}