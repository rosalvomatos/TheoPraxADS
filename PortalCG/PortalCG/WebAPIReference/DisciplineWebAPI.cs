using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using System.Net.Http;
using PortalCG.Models.JsonModels;
using Newtonsoft.Json;

namespace PortalCG.WebAPIReference
{
    public class DisciplineWebAPI
    {
        private static string url = ConfigData.path + "disciplina/";

        public static async Task<List<Discipline>> GetDisciplinesByCourse(int idCourse)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "GetByCurso?id=" + idCourse);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Discipline> obj = JsonConvert.DeserializeObject<List<Discipline>>(data);
            return obj;
        }
    }
}