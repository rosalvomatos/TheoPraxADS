using System.ComponentModel.DataAnnotations;

namespace PortalCG.Util.Enum
{
    public enum TypeCourseFileEnum
    {
        [Display(Name = "PPC")]
        PPC = 1,

        [Display(Name = "Acervo")]
        ACERVO = 2,

        [Display(Name = "Matriz")]
        MATRIZ = 3,

        [Display(Name = "Ato de Autorização")]
        ATO = 4
    }
}