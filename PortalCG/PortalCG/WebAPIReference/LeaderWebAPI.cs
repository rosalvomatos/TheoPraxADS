using Newtonsoft.Json;
using PortalCG.Models.JsonModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;

namespace PortalCG.WebAPIReference
{
    public class LeaderWebAPI
    {
        private static string url = ConfigData.path + "ConfigDirigente/";

        public static async Task<List<Leader>> GetAllLeaders()
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Leader> obj = JsonConvert.DeserializeObject<List<Leader>>(data);
            return obj;
        }
    }
}