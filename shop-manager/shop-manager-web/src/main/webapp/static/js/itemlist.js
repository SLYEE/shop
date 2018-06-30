layui.extend({
    admin: '{/}../../static/js/admin'
});

layui.use(['form', 'table', 'jquery', 'admin','layer'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        admin = layui.admin,
        lay=layui.layer;
    table.render({
        //容器元素
        elem: '#articleList',
        //最小宽度
        cellMinWidth: 80,
        //列,field\title属于列属性，cols属于表格属性
        cols: [
            [{
                type: 'checkbox'
            }, {
                field: 'id', title: '商品编号', sort: true
            }, {
                field: 'catName', title: '分类名称'
            }, {
                field: 'title', title: '商品名称'
            }, {
                field: 'sellPoint', title: '商品卖点'
            }, {
                field: 'status', title: '商品状态', templet: '#shelfTpl'
            }, {
                field: 'operate', title: '操作', toolbar: '#operateTpl', unresize: true
            }]
        ],
        //数据渲染完的回调。你可以借此做一些其它的操作
        //这种是打印文字信息 选择使用模板templet: '#shelfTpl'
      /*  done:function(){
            $("[data-field='status']").children().each(function(){
                if($(this).text()=='1'){
                    $(this).text("正常")
                }else if($(this).text()=='2'){
                    $(this).text("下架")
                }
            })
        },
*/
        //通过URL进行数据绑定
        url: '../../items',
        //是否开启分页
        page: true,
        limits: [10, 50, 100]
        // ,done: function (res, curr, count) {
        //     // console.log(res);
        //     // console.log(curr);
        //     // console.log(count);
        //     $("[data-field='status']").children().each(function () {
        //         //每次遍历进来得到的this就是DOM对象
        //         //DOM--JQ===$(this)
        //         //JQ--DOM===[0],get(0)
        //         if ($(this).text() == '1') {
        //             //正常
        //             $(this).text('正常');
        //         } else if ($(this).text() == '2') {
        //             //下架
        //             $(this).text('下架');
        //         } else if ($(this).text() == '3') {
        //             //删除
        //             $(this).text('删除');
        //         }
        //     });
        // }
    });
    var active={
        //批量删除
        getCheckData:function(){
            //获取被选中的数据
            var status = table.checkStatus("articleList");
            var data = status.data;
            if(data.length>0){
                var ids=[];
                $(data).each(function(i){
                    ids.push(data[i].id)
                })
                $.post(//四个数据  url data success dataType
                    '../../item/batch',
                    {'ids[]':ids},
                    function(data){
                        if(data>0){
                            layer.msg('删除成功',{icon:1});
                            //删除后停留在当前页面
                            $('.layui-laypage-btn').click();


                        }else{
                            layer.msg('删除失败',{icon:2});
                        }
                    }/*,
                    'json'*/
                )

            }else{
                //未选中数据
                layer.msg('请选中商品,再操作')
            }

        },
        //模糊查询
        reload:function(){
            var title=$.trim( $("#title").val());
            if(title.length>0){
                table.reload('articleList',{
                    page:{curr:1},
                    where:{title:title}
                })
            }
        }
    }


    $(".demoTable .layui-btn").on('click',function(){
            var type = $(this).data('type');//属性 data-type="getCheckData"
            //三元表达式 if true 执行方法
             active[type]?active[type].call(this):'';
    })
    $(".we-search .layui-btn").on('click',function(){
            var type = $(this).data('type');//reload
            active[type]?active[type].call(this):'';
    });
   //开关状态反映到后台 这是一个监听事件
    form.on('switch(itemstatus)',function(obj){

            /*console.log(data.elem.parentElement.parentElement);*/

        console.log(obj.othis.parents('tr').find('[data-field=id] div').text());
    });
    /*table.on('tool(test1)',function(obj){

        console.log("1111111111");
    })
*/
    //id = $(data.elem.parentElement.parentElement.parentElement.childNodes[1].childNodes[0]).text();



























    //原来写好的
    /*var active = {
        reload: function(){
            //val() text() html()
            var title = $('#title').val();
            table.reload('articleList',{
                page:{curr:1},
                where:{title:title}
            });
        },
        getCheckData: function () { //获取选中数据
            var checkStatus = table.checkStatus('articleList'),
                data = checkStatus.data;
            if (data.length > 0) {
                layer.confirm('确认要删除吗？', function (index) {
                    //在前台页面把选中数据删除：找到所有被选中的
                    $(".layui-table-body .layui-form-checked").parents('tr').remove();
                    //形成一个ID的数组
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    //发出异步的请求去后台
                    //发出异步请求
                    $.post(
                        //url
                        '../../item/batch',
                        //data
                        {'ids[]': ids},
                        //success
                        function (data) {
                            if (data > 0) {
                                //提示用户删除成功
                                layer.msg('删除成功', {
                                    icon: 1
                                });
                            } else {
                                //提示用户删除失败
                                layer.msg('删除失败', {
                                    icon: 2
                                });
                            }
                        }
                    );
                });
            } else {
                layer.msg("请先选择需要删除的商品！");
            }
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');//getCheckData
        active[type] ? active[type].call(this) : '';
    });

    $('.we-search .layui-btn').on('click',function(){
        var type = $(this).data('type');//reload
        active[type]? active[type].call(this):'';//调用active中reload方法
    });
    form.on('switch(itemstatus)',function(data){
        console.log(data);
    });
*/

});