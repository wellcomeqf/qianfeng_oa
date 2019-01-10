var pageSize = 3;
var total;

$(function(){
    loadOrgPage(0)
})

function loadOrgPage(pageCorrent) {
    var param = new Object();
    param.pageSize = pageSize
    param.total = total
    param.pageStart = pageCorrent*pageSize
    $.ajax({
        url:"getPageNumber",
        data:param,
        type:"get",
        async:true,
        success:function (data) {
            $("#pageNumber").html(data)
        }
    })

}