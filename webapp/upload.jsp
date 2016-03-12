<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>保存头像</title>
		<script src="js/jquery.min.js" type="text/javascript"></script>
		<script src="js/jquery.Jcrop.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrapValidator.min.css" />
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
		<link rel="stylesheet" href="demo_files/demos.css" type="text/css" />
		<style type="text/css">
		　#target {
					max-width:440px;
					max-height:400px;
					margin-right: 15px;
				}
		
		</style>
	</head>
	

	<body>

		<script type="text/javascript">


		</script>

		<table align="center" width="50%" style="margin: 50px 10%;">
			<tr>
				<td>
					<s:fielderror cssStyle="color:red" />
				</td>
			</tr>
		</table>
		
		<s:form action="upload.action" theme="simple" method="post"
			enctype="multipart/form-data" onsubmit="return checkCoords();">

			<table align="center" width="50%" border="0" id="table_upload">
				<tr>
					<td>
						上传头像
					</td>
					<td id="more" >
						<s:file name="upload" onchange="submit();" labelSeparator="sd"></s:file>
						
					</td>
				</tr>
			</table>

		</s:form>
		<input type="text" value="${realName }" id="flag_img" style="display: none;"/>
		<table id="table_img" align="center">
			<tr>
				<td>
					<img src="upload/file/${realName }" width="440px" height="400px" id="target" alt="Flowers" onload=""/>
					
				</td>
				<td>
					<div style="width:100px;height:100px;overflow:hidden;">
						<img src="upload/file/${realName }" width="100px" height="100px" id="preview" alt="Preview" />
					</div>
				</td>
					<td>
					<form action="user!SaveImage.action">
							<input type="hidden" id="x" name="x">
					         <input type="hidden" id="y" name="y">
					         <input type="hidden" id="w" name="w">
	          				<input type="hidden" id="h" name="h">
	          				<input type="hidden" id="realW" name="realW"/>
							<input type="hidden" id="realH" name="realH"/>
	                  <div class="col-lg-12" style="width: 100%;" id="btn_sub">
	                      <button type="submit" class="btn btn-lg btn-primary btn-block">保存头像</button>
	                  </div>
                  </form>
                  </td>
			</tr>
			
		</table>
	</body>
	<script type="text/javascript">
	//测试代码
	$(document).ready(function(){
		if (($("#flag_img").val()=="") || ($("#flag_img").val()==null)) {
			$("#table_img").css("display", "none");
			$("#btn_sub").css("display", "none");
			$('#w').val(20);
		}else{
			$("#table_img").css("display", "block");
			$("#btn_sub").css("display", "block");
			$('#w').val("");
		}
	});
	
	//剪切核心代码
    jQuery(function($){

      // Create variables (in this scope) to hold the API and image size
      var jcrop_api, boundx, boundy;
      
      $('#target').Jcrop({
        onChange: updatePreview,
        onSelect: updatePreview,
        aspectRatio: 0
      },function(){
        // Use the API to get the real image size
        var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        $('#realW').val(boundx);
        $('#realH').val(boundx);
        // Store the API in the jcrop_api variable
        jcrop_api = this;
      });

      function updatePreview(c)
      {
        if (parseInt(c.w) > 0)
        {
          var rx = 100 / c.w;
          var ry = 100 / c.h;
          updateCoords(c);
          $('#preview').css({
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
          });
        }
      };

    });
    	function updateCoords(c){
    	  $('#x').val(c.x);
    	  $('#y').val(c.y);
    	  $('#w').val(c.w);
    	  $('#h').val(c.h);
    	};
    	  
    	function checkCoords(){
    	  if (parseInt($('#w').val())) {
    	    return true;
    	  };
    	  alert('请先选择要裁剪的区域后，再提交。');
    	  return false;
    	};
  </script>

</html>
