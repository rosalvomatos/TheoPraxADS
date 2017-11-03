$(document).ready(function () {
    EqualizeHeight();
    $("#search").keyup(function () {
        var valSearch = $(this).val();
        var searchable = $(".searchable");
        searchable.each(function (index, element) {
            var valElement = $(element).html().trim().toUpperCase();
            if (valElement.indexOf(valSearch.toUpperCase()) !== -1) {
                $(element).parent().parent().parent().parent().show();
            } else {
                $(element).parent().parent().parent().parent().hide();
            }
        });
        EqualizeHeight();
    });
    function EqualizeHeight() {
        var elements = $(".portlet");
        var maxHeigthTitle = 0;
        var maxHeigthContent = 0;
        var maxHeigthButtons = 0;
        elements.each(function (index, element) {
            var portletTitle = $(element).find(".portlet-title").height();
            var portletContent = $(element).find(".course-content").height();
            var portletButtons = $(element).find(".course-buttons").height();
            if (portletTitle > maxHeigthTitle) {
                maxHeigthTitle = portletTitle;
            }
            if (portletContent > maxHeigthContent) {
                maxHeigthContent = portletContent;
            }
            if (portletButtons > maxHeigthButtons) {
                maxHeigthButtons = portletButtons;
            }
        });
        elements.each(function (index, element) {
            $(element).find(".portlet-title").height(maxHeigthTitle);
            $(element).find(".course-content").height(maxHeigthContent);
            $(element).find(".course-buttons").height(maxHeigthButtons);
        });
    }
    $(window).resize(function () {
        EqualizeHeight();
    });
})