<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from www.zi-han.net/theme/hplus/login_v2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:49 GMT -->
<head>
</head>

<body>
  <script type="text/javascript">  
  var pageSize = 10;//设置每页显示条数
  var total;//数据总条数
  function pagination() {
	  LoadData(0)
      $("#Pagination").pagination(total, {
          callback : PageCallback,
          prev_text : '上一页',
          next_text : '下一页',
          items_per_page : pageSize,
          num_display_entries : 4, //连续分页主体部分显示的分页条目数
          num_edge_entries : 1
      //两侧显示的首尾分页的条目数
      })
  }
  function PageCallback(index, jq) { //前一个表示您当前点击的那个分页的页数索引值，后一个参数表示装载容器。 
     LoadData(index);
  }
/*   function LoadData(pageIndex) {
      $.ajax({
          type : "get",
          url : url+"?pageSize=" + pageSize + "&pageIndex=" + pageIndex,
          async : false,
          dataType : "json",
          date:jsonData,
          success : function(ObjData) {
        	  return ObjData;
          }
      })
  } */
  </script>
</body>

</html>
