using System.Collections.Generic;
using System.Threading.Tasks;
using System.Net.Http;
using PortalCG.Models.JsonModels;
using Newtonsoft.Json;

namespace PortalCG.WebAPIReference
{
    public class TeacherWebAPI
    {
        private static string url = ConfigData.path + "professor/";

        public static async Task<List<Teacher>> GetAllTeachers()
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Teacher> obj = JsonConvert.DeserializeObject<List<Teacher>>(data);
            return obj;
        }

        public static async Task<List<Teacher>> GetTeachersByCourse(int idCourse)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "GetByCurso?id=" + idCourse);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Teacher> obj = JsonConvert.DeserializeObject<List<Teacher>>(data);
            return obj;
        }

        public static async Task<List<Teacher>> GetTeachersByDiscipline(int idDiscipline)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "GetByDisciplina?id=" + idDiscipline);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Teacher> obj = JsonConvert.DeserializeObject<List<Teacher>>(data);
            return obj;
        }

        public static async Task<Teacher> GetTeacherById(int id)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "Get?id=" + id);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            Teacher obj = JsonConvert.DeserializeObject<Teacher>(data);
            return obj;
        }
    }
}