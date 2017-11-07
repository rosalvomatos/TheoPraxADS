using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace PortalCG.Util.Enum
{
    public enum TypeCourseFileEnum
    {
        [Description("PPC")]
        PPC = 1,

        [Description("Acervo")]
        ACERVO = 2,

        [Description("Matriz")]
        MATRIZ = 3,

        [Description("Ato de Autorização")]
        ATO = 4
    }
}