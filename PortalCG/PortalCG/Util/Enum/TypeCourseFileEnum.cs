using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

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