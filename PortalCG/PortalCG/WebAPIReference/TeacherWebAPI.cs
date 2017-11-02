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
    public class TeacherWebAPI
    {
        private static string url = ConfigData.path + "professor/";

        public static async Task<List<Teacher>> GetTeachersByCourse(int idCourse)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "?GetByCurso?id=" + idCourse);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Teacher> obj = JsonConvert.DeserializeObject<List<Teacher>>(data);
            return obj;
        }
    }
}