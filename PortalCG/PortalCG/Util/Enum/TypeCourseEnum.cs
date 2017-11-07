using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace PortalCG.Util.Enum
{
    public enum TypeCourseEnum
    {
        [Description("Graduação")]
        GRADUATION = 1,
        [Description("Pós Graduação")]
        POSTGRADUATE = 2
    }
}