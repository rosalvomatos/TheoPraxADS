using PortalCG.Util.Enum;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace PortalCG.Models
{
    public class CourseUploadFileModel
    {
        public int IdCourse { get; set; }
        public string NameCourse { get; set; }

        public int OptionRoute { get; set; }

        public List<FileViewModel> FileList = new List<FileViewModel>
        {
            new FileViewModel
            {
                IdType = (int) TypeCourseFileEnum.ACERVO
            },
            new FileViewModel
            {
                IdType = (int) TypeCourseFileEnum.ATO
            },
            new FileViewModel
            {
                IdType = (int) TypeCourseFileEnum.MATRIZ
            },
            new FileViewModel
            {
                IdType = (int) TypeCourseFileEnum.PPC
            }
        };
    }

    public class DisciplineUploadFileModel
    {
        public int IdDiscipline { get; set; }
        public string NameDiscipline { get; set; }
        public FileViewModel File { get; set; }
        public int? IdCourse { get; set; }
        public int? CourseOptionRoute { get; set; }
    }

    public class FileViewModel
    {
        public int IdType { get; set; }
        public string Name { get; set; }
    }
}