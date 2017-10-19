<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/tree_view.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:58 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 树形视图</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row wrapper wrapper-content animated fadeInRight">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>Bootstrap Tree View 简介</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="buttons.html#">选项1</a>
                            </li>
                            <li><a href="buttons.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <p>Bootstrap Tree View 是一个简单而优雅的Bootstrap树形视图解决方案。
                        <br>您可以访问作者的
                        <a href="https://github.com/jonmiles/bootstrap-treeview" target="_blank">GitHub页面</a> 了解其用法及更多信息。</p>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理</h5>${menuJson}
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="buttons.html#">选项1</a>
                            </li>
                            <li><a href="buttons.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div id="treeview" class="test"></div>
                </div>
            </div>
        </div>
    </div>
    
    
     <div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">菜单管理</h4>
                    </div>
                  <div class="modal-body">
                  <form id="menuForm">
                  		<input type="hidden" name="pid" value=""/>
                  		<input type="hidden" name="id" value=""/>
                      <div class="form-group"><label>父级菜单</label> <input type="text" class="form-control" value=""/></div>
                      <div class="form-group"><label>菜单名称</label> <input type="text" placeholder="请输入菜单名称" class="form-control" name="menuName" value=""></div>
                      <div class="form-group"><label>请求链接</label> <input type="text" placeholder="请输入请求链接"class="form-control" name="linkUrl" value=""></div>
                      <div class="form-group"><label>菜单图标</label> <input type="text" placeholder="请输入菜单图标"class="form-control" name="iconCls" value=""></div>
                      <div class="form-group"><label>顺序编号</label> <input type="text" placeholder="请输入顺序编号"class="form-control" name="seqId" value=""></div>
                   <form>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                      <button type="button" class="btn btn-primary" onclick="saveNode();">保存</button>
                  </div>
              </div>
         </div>
     </div>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/content.min.js?v=1.0.0"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/treeview/bootstrap-treeview.js"></script>
    <script type="text/javascript">
    $(function() {
    	var  t = ${menuJson};
		$("#treeview").treeview({
			color : "#428bca",
    		data : t,
    		levels : 99,
    		showTags: true
    	})
    });
    
    function nodeAdd(e){
   	  var nodeid = $(e).parent().parent().attr('data-nodeid'); 
   	   //获取当前节点对象  
   	 var node = $('#treeview').treeview('getNode', nodeid); 
	   		$("input[name='pid']").val(node.id);
	    	$('#menuModal').modal('show');		
    }
    
    function saveNode(){
    	$.ajax({
               cache: true,
               type: "POST",
               url:ajaxCallUrl,
               data:$('#menuForm').serialize(),
               async: false,
               success: function(data) {
            	   $("#treeview").treeview("addNode", [node.id, { node: { text: $("input[name='menuName']").val(), href: "001005" } }]); 
               }
           });
    }

    </script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/tree_view.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:59 GMT -->
</html>
