using System.Web;
using System.Web.Optimization;

namespace PortalCG
{
    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/bundles/jquery").Include(
                        "~/Scripts/jquery-{version}.js"));

            bundles.Add(new ScriptBundle("~/bundles/jqueryval").Include(
                        "~/Scripts/jquery.validate*"));

            // Use the development version of Modernizr to develop with and learn from. Then, when you're
            // ready for production, use the build tool at http://modernizr.com to pick only the tests you need.
            bundles.Add(new ScriptBundle("~/bundles/modernizr").Include(
                        "~/Scripts/modernizr-*"));

            bundles.Add(new ScriptBundle("~/bundles/bootstrap").Include(
                      "~/Scripts/bootstrap.js",
                      "~/Scripts/respond.js"));

            bundles.Add(new StyleBundle("~/Content/css").Include(
                      "~/Content/bootstrap.css",
                      "~/Content/site.css"));

            bundles.Add(new StyleBundle("~/Mandatory/css").Include(
                "~/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css",
                "~/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css",
                "~/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css",
                "~/metronic/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
                ));

            bundles.Add(new StyleBundle("~/Global/css").Include(
                "~/metronic/assets/global/css/components-md.min.css",
                "~/metronic/assets/global/css/plugins-md.min.css"
                ));

            bundles.Add(new StyleBundle("~/Threme/css").Include(
                "~/metronic/assets/layouts/layout5/css/layout.min.css",
                "~/metronic/assets/layouts/layout5/css/themes/default.min.css",
                "~/metronic/assets/layouts/layout5/css/custom.min.css"
                ));

            bundles.Add(new ScriptBundle("~/Core/js").Include(
                "~/metronic/assets/global/plugins/jquery.min.js",
                "~/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js",
                "~/metronic/assets/global/plugins/js.cookie.min.js",
                "~/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js",
                "~/metronic/assets/global/plugins/jquery.blockui.min.js",
                "~/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" 
                ));

            bundles.Add(new ScriptBundle("~/PageLevelPlugin/js").Include(
                "~/metronic/assets/global/plugins/moment.min.js",
                "~/metronic/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js",
                "~/metronic/assets/global/plugins/morris/morris.min.js",
                "~/metronic/assets/global/plugins/morris/raphael-min.js",
                "~/metronic/assets/global/plugins/counterup/jquery.waypoints.min.js",
                "~/metronic/assets/global/plugins/counterup/jquery.counterup.min.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/amcharts.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/serial.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/pie.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/radar.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/themes/light.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/themes/patterns.js",
                "~/metronic/assets/global/plugins/amcharts/amcharts/themes/chalk.js",
                "~/metronic/assets/global/plugins/amcharts/ammap/ammap.js",
                "~/metronic/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js",
                "~/metronic/assets/global/plugins/amcharts/amstockcharts/amstock.js",
                "~/metronic/assets/global/plugins/fullcalendar/fullcalendar.min.js",
                "~/metronic/assets/global/plugins/horizontal-timeline/horizontal-timeline.js",
                "~/metronic/assets/global/plugins/flot/jquery.flot.min.js",
                "~/metronic/assets/global/plugins/flot/jquery.flot.resize.min.js",
                "~/metronic/assets/global/plugins/flot/jquery.flot.categories.min.js",
                "~/metronic/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js",
                "~/metronic/assets/global/plugins/jquery.sparkline.min.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js",
                "~/metronic/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js"
                ));

            bundles.Add(new ScriptBundle("~/Global/js").Include(
                "~/metronic/assets/global/scripts/app.min.js"
                ));

            bundles.Add(new ScriptBundle("~/Page/js").Include(
                "~/metronic/assets/pages/scripts/dashboard.min.js"
                ));

            bundles.Add(new ScriptBundle("~/Threme/js").Include(
                "~/metronic/assets/layouts/layout5/scripts/layout.min.js",
                "~/metronic/assets/layouts/layout5/scripts/demo.min.js",
                "~/metronic/assets/layouts/global/scripts/quick-sidebar.min.js",
                "~/metronic/assets/layouts/global/scripts/quick-nav.min.js"
                ));

            bundles.Add(new ScriptBundle("~/FileInput/js").Include(
                "~/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
                ));

            bundles.Add(new StyleBundle("~/FileInput/css").Include(
                "~/metronic/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
                ));
        }
    }
}
