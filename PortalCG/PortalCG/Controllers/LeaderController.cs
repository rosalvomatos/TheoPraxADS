using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class LeaderController : Controller
    {
        public async Task<ActionResult> AllLeaders()
        {
            List<Leader> AllLeaderList = await LeaderWebAPI.GetAllLeaders();
            var LeaderViewIndex = GetNames(AllLeaderList);
            return View(LeaderViewIndex);
        }


        private List<LeaderViewModel> GetNames(List<Leader> leaderList)
        {
            List<LeaderViewModel> leaderListTemp = new List<LeaderViewModel>();

            leaderList.ForEach(x =>
            {
                var temp = leaderList.Where(t => t.Chave.Contains(x.Chave)).ToList();
                if (temp?.Count() > 1)
                {
                    leaderListTemp.Add(new LeaderViewModel()
                    {
                        LeaderTitle = x,
                        LeaderContentList = temp
                    });
                }
            });
            return leaderListTemp;
        }
    }
}