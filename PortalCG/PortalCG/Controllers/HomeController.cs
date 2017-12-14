using System.Web.Mvc;

namespace PortalCG.Controllers
{
    //[Authorize(Users = "*")]
    public class HomeController : Controller
    {
        public ActionResult Index()
        {

            return RedirectToAction("AllCourses", "Course");
        }
    }
}