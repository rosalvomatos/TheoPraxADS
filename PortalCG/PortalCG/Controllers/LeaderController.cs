﻿using PortalCG.Models.JsonModels;
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
                var leaderMapped = GetNames(leaderFound).FirstOrDefault();
                return View(leaderMapped);
            }
            return RedirectToAction("AllLeaders");
        }

        [HttpPost]
        public async Task<ActionResult> Edit(LeaderViewModel leader)
        {
            if(leader.LeaderToInsert?.Count > 0)
            {
                await SaveNewLeaderAsync(leader);
            }
            return null;
        }

        private async Task SaveNewLeaderAsync(LeaderViewModel leader)
        {
            await LeaderWebAPI.SaveLeaderAsync(new Leader
            {
                Chave = "TESTE",
                Valor = "32"
            });
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
    }
}