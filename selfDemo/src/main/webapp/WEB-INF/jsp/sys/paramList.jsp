<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 基础表格</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/pagination.css" rel="stylesheet" type="text/css" />

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>自定义响应式表格</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-5 m-b-xs">
                                <select class="input-sm form-control input-s-sm inline">
                                    <option value="0">请选择</option>
                                    <option value="1">选项1</option>
                                    <option value="2">选项2</option>
                                    <option value="3">选项3</option>
                                </select>
                            </div>
                            <div class="col-sm-4 m-b-xs">
                                <div data-toggle="buttons" class="btn-group">
                                    <label class="btn btn-sm btn-white">
                                        <input type="radio" id="option1" name="options">天</label>
                                    <label class="btn btn-sm btn-white active">
                                        <input type="radio" id="option2" name="options">周</label>
                                    <label class="btn btn-sm btn-white">
                                        <input type="radio" id="option3" name="options">月</label>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入关键词" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>

                                        <th></th>
                                        <th>变量名称</th>
                                        <th>变量类型</th>
                                        <th>变量值</th>
                                        <th>描述</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <input type="checkbox" checked class="i-checks" name="input[]">
                                        </td>
                                        <td>米莫说｜MiMO Show</td>
                                        <td><span class="pie">0.52/1.561</span>
                                        </td>
                                        <td>20%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>商家与购物用户的交互试衣应用</td>
                                        <td><span class="pie">6,9</span>
                                        </td>
                                        <td>40%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>天狼---智能硬件项目</td>
                                        <td><span class="pie">3,1</span>
                                        </td>
                                        <td>75%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>线下超市+线上商城+物流配送互联系统</td>
                                        <td><span class="pie">4,9</span>
                                        </td>
                                        <td>18%</td>
                                        <td>2014.11.11</td>
                                        <td><a href="table_basic.html#"><i class="fa fa-check text-navy"></i></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/content.min.js?v=1.0.0"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.pagination.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
</body>
</html>
