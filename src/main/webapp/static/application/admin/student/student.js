var pageSize = 3;
var total;

$(function(){
    getPage(0)
    loadOrgPage(0)
})

function getPage(pageCorrent) {
    var param = new Object();
    param.pageStart = pageCorrent*pageSize;
    param.pageSize = pageSize;
    $.ajax({
        url:"/student/getList",
        async:true,
        type:"GET",
        data:param,
        success:function (data) {
            $("#getPage").html(data);
        }
    })
}

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