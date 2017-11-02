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

        public static async Task<List<Discipline>> GetAllDisciplines()
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Discipline> obj = JsonConvert.DeserializeObject<List<Discipline>>(data);
            return obj;
        }

        public static async Task<List<Discipline>> GetDisciplinesByCourse(int idCourse)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "GetByCurso?id=" + idCourse);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Discipline> obj = JsonConvert.DeserializeObject<List<Discipline>>(data);
            return obj;
        }

        public static async Task<Discipline> GetDisciplineById(int id)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "Get?id=" + id);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            Discipline obj = JsonConvert.DeserializeObject<Discipline>(data);
            return obj;
        }

        public static async Task<List<Discipline>> GetDisciplinesByTeacher(int idTeacher)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "GetByProfessor?id=" + idTeacher);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Discipline> obj = JsonConvert.DeserializeObject<List<Discipline>>(data);
            return obj;
        }
    }
}