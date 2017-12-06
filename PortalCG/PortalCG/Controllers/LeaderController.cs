using PortalCG.Extensions;
using PortalCG.Models.JsonModels;
using PortalCG.Models.ViewModels;
using PortalCG.WebAPIReference;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace PortalCG.Controllers
{
    public class LeaderController : Controller
    {
        public async Task<ActionResult> AllLeaders()
        {
            ViewBag.Url = Url.Action("AllLeaders");
            List<Leader> AllLeaderList = await LeaderWebAPI.GetAllLeaders();
            var LeaderViewIndex = GetNames(AllLeaderList);
            return View(LeaderViewIndex);
        }

        public async Task<ActionResult> Edit(string key)
        {
            ViewBag.Url = Url.Action("AllLeaders");
            if (!String.IsNullOrEmpty(key?.Trim()))
            {
                var leaderFound = await LeaderWebAPI.GetLeadersByKey(key);
                if (leaderFound?.Count > 0)
                {
                    var leaderMapped = GetNames(leaderFound).FirstOrDefault();
                    return View(leaderMapped);
                }
            }
            return RedirectToAction("AllLeaders");
        }

        [HttpPost]
        public ActionResult Edit(LeaderViewModel leader)
        {
            if (leader.LeaderToInsert?.Count > 0)
            {
                SaveNewLeaderFromExistsLeader(leader);
            }
            if (leader.LeaderToDelete?.Count > 0)
            {
                foreach (var item in leader.LeaderToDelete)
                {
                    DeleteLeader(item);
                }
            }
            LeaderWebAPI.UpdateLeaderAsync(leader.LeaderTitle);
            return RedirectToAction("AllLeaders").Success("Dirigente editado com sucesso");
        }

        private void SaveNewLeaderFromExistsLeader(LeaderViewModel leader)
        {
            var leaderRoot = leader.LeaderTitle;
            foreach (var item in leader.LeaderToInsert)
            {
                Guid c = Guid.NewGuid();
                LeaderWebAPI.SaveLeaderAsync(new Leader
                {
                    Chave = leaderRoot.Chave + "_" + c.ToString().Replace("-", ""),
                    Valor = item
                });
            }
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
                        LeaderContentList = temp.Where(e => !e.Chave.Equals(x.Chave)).ToList()
                    });
                }
            });
            return leaderListTemp;
        }

        public ActionResult Create()
        {
            ViewBag.Url = Url.Action("AllLeaders");
            return View(new LeaderViewModel());
        }

        [HttpPost]
        public ActionResult Create(LeaderViewModel leader)
        {
            Guid c = Guid.NewGuid();
            leader.LeaderTitle.Chave = c.ToString().Replace("-", "");
            LeaderWebAPI.SaveLeaderAsync(leader.LeaderTitle);
            if (leader.LeaderToInsert?.Count > 0)
            {
                SaveNewLeaderFromExistsLeader(leader);
            }
            return RedirectToAction("AllLeaders").Success("Dirigente cadastrado com sucesso");
        }

        [HttpGet]
        public async Task<ActionResult> Delete(string key, bool isTitle = false)
        {
            if (isTitle)
            {
                List<Leader> leaderList = await LeaderWebAPI.GetLeadersByKey(key);
                foreach (var item in leaderList)
                {
                    DeleteLeader(item.Chave);
                }
            }
            return RedirectToAction("AllLeaders").Success("Dirigente excluido com sucesso.");
        }

        private void DeleteLeader(string key)
        {
            LeaderWebAPI.DeleteLeaderAsync(key);
        }
    }
}