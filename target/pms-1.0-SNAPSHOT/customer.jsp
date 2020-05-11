<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>客户信息管理</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function chooseAll() {
            //jquery的好处：1.当我们为一组元素设置某个属性的时候，就相当于为这一组中的每个元素设置了这个属性
            //              2.解决了大部分的浏览器兼容性
            $("#list-table").find("input").attr("checked", true);
        }
        function reverseAll() {
            $("#list-table").find("input").each(function () {
                $(this).attr("checked",!$(this).attr("checked"));
            })
        }
        
        function deleteAll() {
            var ids="";
            $("#list-table").find("input:checked").each(function () {
               var cid= $(this).val();
               ids=ids+cid+",";
            })
            ids=ids.substring(0,ids.length-1);

            if(confirm("您确认要删除该数据么？")){
                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/Customer/delete/"+ids,
                    data:{"_method":"delete"},
                    success:function (msg) {
                        if(msg.statusCode == 200){
                            window.location.href="${pageContext.request.contextPath}/Customer/list";
                        }else{
                            alert(msg.message);
                            window.location.href="${pageContext.request.contextPath}/Customer/list";
                        }
                    }
                })
            }}
    </script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td >
                        当前位置:客户信息管理>>客户信息
                    </td>
                    <td>
                        <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/customer-add.jsp';" value='添加客户信息' />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='${pageContext.request.contextPath}/Customer/search' method='get'>
    <table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select name='cid' style='width:150'>
                                <option value='0'>选择类型...</option>
                                <option value='1'>客户所在公司名称</option>
                                <option value='2'>联系人姓名</option>
                            </select>
                        </td>
                        <td width='70'>
                            关键字：
                        </td>
                        <td width='160'>
                            <input type='text' name='keyword' value="${keyword}"  style='width:120px' />
                        </td>
                        <td width='110'>
                            <select name='orderby' style='width:120px'>
                                <option value='0'>排序...</option>
                                <option value='0'>按着id升序</option>
                                <option value='1'>按着id降序</option>
                            </select>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<!--  内容列表   -->
<form name="form2">

    <table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
        </tr>
        <tr align="center" bgcolor="#FAFAF1" height="22">
            <td width="4%">选择</td>
            <td width="6%">序号</td>
            <td width="10%">联系人</td>
            <td width="10%">公司名称</td>
            <td width="8%">添加时间</td>
            <td width="8%">联系电话</td>
            <td width="10%">操作</td>
        </tr>

        <c:forEach items="${list}" var="customer" varStatus="index">
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
                <td><input name="id" type="checkbox" id="id" value="${customer.id}" class="np"></td>
                <td>${index.count}</td>
                <td>${customer.companyperson}</td>
                <td align="center">${customer.comname}</td>
                <td>
                    <fmt:formatDate value="${customer.addtime}" pattern="yyyy-MM-dd"/>
                </td>
                <td>${customer.comphone}</td>
                <td><a href="${pageContext.request.contextPath}/Customer/edit/${customer.id}">编辑</a> | <a href="${pageContext.request.contextPath}/Customer/detail/${customer.id}">查看详情</a></td>
            </tr>
        </c:forEach>

        <tr bgcolor="#FAFAF1">
            <td height="28" colspan="12">
                &nbsp;
                <a href="javascript:chooseAll()" class="coolbg">全选</a>
                <a href="javascript:reverseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deleteAll()" class="coolbg">&nbsp;删除&nbsp;</a>
                <a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
            </td>
        </tr>
        <tr align="right" bgcolor="#EEF4EA">
            <td height="36" colspan="12" align="center"><!--翻页代码 --></td>
        </tr>
    </table>

</form>


</body>
</html>