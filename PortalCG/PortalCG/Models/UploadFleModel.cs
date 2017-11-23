using PortalCG.Util.Enum;
using System.Collections.Generic;

namespace PortalCG.Models
{
    public class CourseUploadFileModel
    {
        public string CodeCourse { get; set; }
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
        public string CodeDiscipline { get; set; }
        public string NameDiscipline { get; set; }
        public FileViewModel File { get; set; }
        public string CodeCourse { get; set; }
        public int? CourseOptionRoute { get; set; }
        public int? OptionRoute { get; set; }
        public string CodeTeacher { get; set; }
    }

    public class InstitutionalUploadFileModel
    {
        public int IdType { get; set; }
        public FileViewModel File { get; set; }
    }

    public class FileViewModel
    {
        public int IdType { get; set; }
        public string Name { get; set; }
    }
}