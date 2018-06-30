layui.extend({
    admin: '{/}../../static/js/admin'
});

layui.use(['form', 'table', 'jquery', 'admin'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        admin = layui.admin;
    $("#btnImport").click(function(){
        //点击后禁用按钮
        $(this).addClass("layui-btn-disabled");
        $.post(
            //url
            "../../item/indexlib/import",
            //data
            null,
            //success
            function(data){
              console.log(data);
                $("#btnImport").removeClass("layui-btn-disabled");
            },
            //dataType
            'json'
        )
       /* alert("aaaaaaa")
        //按钮回复
        $(this).removeClass("layui-btn-disabled")*/
        /*$(this).removeClass("layui-btn-disabled");*/
    })

});