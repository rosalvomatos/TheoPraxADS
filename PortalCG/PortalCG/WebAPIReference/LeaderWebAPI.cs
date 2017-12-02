using Newtonsoft.Json;
using PortalCG.Models.JsonModels;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web;

namespace PortalCG.WebAPIReference
{
    public class LeaderWebAPI
    {
        private static string url = ConfigData.path + "ConfigDirigente";

        public static async Task<List<Leader>> GetAllLeaders()
        {
            //REMOVE AFTER TEST
            url = "http://tp-ws.somee.com/api/configdirigente";
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Leader> obj = JsonConvert.DeserializeObject<List<Leader>>(data);
            return obj;
        }

        public static async Task<List<Leader>> GetLeadersByKey(string key)
        {
            HttpClient httpClient = new HttpClient();
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url + "?chave=" + key);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string data = await response.Content.ReadAsStringAsync();
            List<Leader> obj = JsonConvert.DeserializeObject<List<Leader>>(data);
            return obj;
        }

        public static async Task SaveLeaderAsync(Leader leader)
        {
            WebRequest request = WebRequest.Create(url + "/post");
            request.Method = "POST";
            string postData = JsonConvert.SerializeObject(leader);
            byte[] byteArray = Encoding.UTF8.GetBytes(postData);
            request.ContentType = "application/json; charset=utf-8";
            request.ContentLength = byteArray.Length;
            Stream dataStream = request.GetRequestStream();
            dataStream.Write(byteArray, 0, byteArray.Length);
            dataStream.Close();
            WebResponse response = request.GetResponse();
            dataStream = response.GetResponseStream();
            StreamReader reader = new StreamReader(dataStream);
            string responseFromServer = reader.ReadToEnd();
            reader.Close();
            dataStream.Close();
            response.Close();
        }
    }
}