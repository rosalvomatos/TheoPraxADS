namespace PortalCG.Models.JsonModels
{
    public class Course
    {
        public int Id { get; set; }
        public string Nome { get; set; }
        public string CH { get; set; }
        public string Coordenador { get; set; }
        public int NotaMEC { get; set; }
        public string Mensalidade { get; set; }
        public int Tipo { get; set; }
        public int OptionRoute { get; set; }
    }
}