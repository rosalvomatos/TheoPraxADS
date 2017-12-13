using PortalCG.WebAPIReference;
using System.IO;
using System.Net;
using System.Web;

namespace PortalCG.Util
{
    public class UploadFileUtil
    {

        private static string url = ConfigData.path + "Documentos/";


        public static void FTPUpload(string fileName, HttpPostedFileBase file)
        {
            WebClient client = new WebClient();
            var folder = HttpContext.Current.Server.MapPath("~/App_Data/Temp");
            if (!Directory.Exists(folder))
                Directory.CreateDirectory(folder);
            var path = Path.Combine(folder, fileName);
            file.SaveAs(path);
            client.UploadFile(url, "POST", path);
            if (File.Exists(path))
                File.Delete(path);
        }
    }
}