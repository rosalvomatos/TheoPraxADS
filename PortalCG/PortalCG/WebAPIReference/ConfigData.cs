using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PortalCG.WebAPIReference
{
    public class ConfigData
    {
        public static string path
        {
            get
            {
                return "http://ws-tp.apphb.com/api/";
            }
            private set { }
        }
    }
}