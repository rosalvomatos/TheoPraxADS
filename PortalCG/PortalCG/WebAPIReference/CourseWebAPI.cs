﻿using Newtonsoft.Json;
using PortalCG.Models.JsonModels;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;

namespace PortalCG.WebAPIReference
{
    public class CourseWebAPI
    {
        private static string url = ConfigData.path + "curso/";

        public static async Task<List<Course>> GetAllCourses()
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Course> obj = JsonConvert.DeserializeObject<List<Course>>(data);
            return obj;
        }

        public static async Task<List<Course>> GetCoursesByType(string type)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + type);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Course> obj = JsonConvert.DeserializeObject<List<Course>>(data);
            return obj;
        }

        public static async Task<Course> GetCourseById(string code)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "Get?cod=" + code);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            Course obj = JsonConvert.DeserializeObject<Course>(data);
            return obj;
        }
    }
}