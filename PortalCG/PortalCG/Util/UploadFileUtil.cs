using System.IO;
using System.Net;
using System.Text;
using System.Web;

namespace PortalCG.Util
{
    public class UploadFileUtil
    {
        //CHANGE
        private static string userName = "dlpuser@dlptest.com";
        private static string path = "ftp://ftp.dlptest.com/";
        private static string password = "fwRhzAnR1vgig8s";

        public static void FTPUpload(string fileName, HttpPostedFileBase file)
        {
            FtpWebRequest request = (FtpWebRequest)WebRequest.Create(path + fileName);
            request.Method = WebRequestMethods.Ftp.UploadFile;

            request.Credentials = new NetworkCredential(userName, password);

            StreamReader sourceStream = new StreamReader(file.InputStream);
            byte[] fileContents = Encoding.UTF8.GetBytes(sourceStream.ReadToEnd());
            sourceStream.Close();
            request.ContentLength = fileContents.Length;
            Stream requestStream = request.GetRequestStream();
            requestStream.Write(fileContents, 0, fileContents.Length);
            requestStream.Close();
            FtpWebResponse response = (FtpWebResponse)request.GetResponse();
            response.Close();
        }
    }
}