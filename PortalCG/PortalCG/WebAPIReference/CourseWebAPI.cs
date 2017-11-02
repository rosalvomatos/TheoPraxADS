﻿using Newtonsoft.Json;
using PortalCG.Models.JsonModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;

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
            string dados = await response.Content.ReadAsStringAsync();
            List<Course> obj = JsonConvert.DeserializeObject<List<Course>>(dados);
            return obj;
        }

        public static async Task<List<Course>> GetCoursesByType(string type)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + type);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string dados = await response.Content.ReadAsStringAsync();
            List<Course> obj = JsonConvert.DeserializeObject<List<Course>>(dados);
            return obj;
        }
    }
}