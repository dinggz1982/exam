<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>标签可视化实验</title>
<link href="/tag/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/tag/jquery.min.js"></script>
<script type="text/javascript" src="/tag/bootstrap.min.js"></script>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/>
<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
canvas {
	/* background: blue; */
	border: 1px solid black;
}

#coordinate {
	color: red;
}

#table {
	width: 800px;
	padding-top: 5px;
}

#op {
	width: 800px;
	padding-bottom: 5px;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row" style="text-align: center;">
		<h2>标签云实验</h2>
		<div id="op">
			&nbsp;<input type="button" id="Undo" value="Undo"
				class="btn btn-success" />&nbsp; &nbsp;<input type="button"
				id="Redo" value="Redo" class="btn btn-success" />&nbsp; &nbsp;<input
				type="button" id="CWL" value="Change Words List"
				class="btn btn-success" />&nbsp; &nbsp;<input type="button"
				id="Save" value="Save" class="btn btn-success" />&nbsp; <select
				id="img_type">
				<option value="jpg">jpg</option>
				<option value="png">png</option>
				<option value="bmp">bmp</option>
			</select> &nbsp;<input type="button" value="Edit WordList"
				class="btn btn-success" id="EditWL" data-toggle="modal"
				data-target="#myModal" />&nbsp; <br />
		</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="height: 550px;border: 1px solid black;">
				<table class="table">
      <tbody>
        <tr>
          <td>选中的词汇：<span id="selectWord"></span></td>
        </tr>
         <tr>
          <td>词频：<span id="wordFreq"></span></td>
        </tr>
         <tr>
          <td>颜色：<input type="color" id="wordColor" />&nbsp;&nbsp;<span id="colorValue"></span></td>
        </tr>
         <tr>
          <td>词汇位置：x:<span id="wordX"></span>,y:<span id="wordY"></span></td>
        </tr>
		</tbody>
		</table>	
				
			</div>
			<div class="col-md-8"><canvas id="canvas" width="800" height="550"></canvas></div>
		</div>
	</div>
	<div id="cv_div" align="center">
		<br /> <input type="button" id="WordsList" value="Show Words List"
			class="btn btn-success" />&nbsp; <input type="button" id="showBox"
			value="Show Box" class="btn btn-success" />&nbsp; <input
			type="button" id="Word_level" value="Word-level Box"
			class="btn btn-success" />&nbsp; <input type="button"
			id="Letter_level" value="Letter-level Box" class="btn btn-success" />&nbsp;
		<input type="button" id="reLayout" value="ReLayout"
			class="btn btn-success" />&nbsp; <input type="button" id="ReLayout2"
			value="ReLayout2" class="btn btn-success" />&nbsp;
		<!-- <input type="button" id="Cforces" value="Center Forces"/>&nbsp; -->
		<input type="button" id="compact" value="Compact"
			class="btn btn-success" />&nbsp;
		<!--   		<input type="button" id="preserve" value="Preserve"/>&nbsp; -->
		<!-- <input type="button" id="test" value="test"/>&nbsp; -->
		<br />
		<div id="table" align="center">
			<table width="80%" align="center">
				<tr>
					<td align="left">鼠标位置:<span id="coordinate">0:0</span></td>
					<td align="right"><span id="word"></span>&nbsp;&nbsp;
						<div id="font" style="display:none">
							color:&nbsp;<input type="color" id="color" />&nbsp; font:&nbsp;<select
								id="font_type">
								<option value="Arial">Arial</option>
								<option value="Courier">Courier</option>
								<option value="Serif">Serif</option>
								<option value="Teen">Teen</option>
								<option value="Verdana">Verdana</option>
								<option value="Georgia">Georgia</option>
								<option value="Times">Times</option>
							</select>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	
	<script type="text/javascript" src="/tag/mywc_test.js"></script>
	<script type="text/javascript">
  		//window.onload = originalWordle;
  		var mylist = [${wordString}];
		var wlist=[mylist];
  		window.onload = changeWL;
		document.getElementById("ReLayout2").onclick = originalWordle;
		//document.getElementById("Cforces").onclick = centerWordle;
		document.getElementById("reLayout").onclick = changeLayout;
		//document.getElementById("showBox").onclick = showBox;
		document.getElementById("showBox").onclick = function(){
			showBox("red");
		};
		document.getElementById("Word_level").onclick = function(){
			Word_levelBox("black");
		};
		document.getElementById("Letter_level").onclick = function(){
			Letter_levelBox("red");
		};
		document.getElementById("compact").onclick = compact;
		//document.getElementById("preserve").onclick = preserve;

		document.getElementById("Undo").onclick = undo;
		document.getElementById("Redo").onclick = redo;
		document.getElementById("CWL").onclick = changeWL;
		document.getElementById("Save").onclick = function(){
			var type =  document.getElementById("img_type").value;
			var topic_id=${};
			download(type);
		};
		document.getElementById("WordsList").onclick = WordsList;
		//document.getElementById("test").onclick = test;
        document.getElementById("EditWL").onclick = showWLinModal;
        document.getElementById("clearWL").onclick = clearWL;
        document.getElementById("CWLBP").onclick = inputWL;

	</script>
</body>
</html>
