using PortalCG.Models.JsonModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PortalCG.Models.ViewModels
{
    public class LeaderViewModel
    {
        public Leader LeaderTitle { get; set; }
        public List<Leader> LeaderContentList { get; set; }
    }
}