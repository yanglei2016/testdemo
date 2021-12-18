 $(function(){
	
	$("#search").click(function(){
		var fromStation=$("#fromStation").val();
		if(fromStation==""){
			$("#fromStationText").css({"border": "1px solid red","color":"#999"});
			return;
		}
		var toStation=$("#toStation").val();
		if(toStation==""){
			$("#toStation").css({"border": "1px solid red","color":"#999"});
			return;
		}
		var startDate=$("#startDate").val();
		setCookie("fromStation",fromStation,60);
		setCookie("fromStationText",$("#fromStationText").val(),60);
		setCookie("toStation",toStation,60);
		setCookie("toStationText",$("#toStationText").val(),60);
	});
	
	$("#checkbox_All").click(function(){
		if($(this).is(':checked')){
			$("input[name=cc_type]").prop("checked","checked");
		}else{
			$("input[name=cc_type]").removeProp("checked");
		}
	});
	
	var tbody="";
	$("input[name=cc_type]").click(function(){
		if(tbody!="")
			$("#tbody").html(tbody);
		tbody=$("#tbody").html();
		var cc_type = "";
		$("input[name=cc_type]:checkbox:checked").each(function(){
			cc_type += $(this).val();
		});
		var html="";
		$("#tbody tr").each(function(){
			var trainCode = $(this).find(":checkbox").val().substring(0,1);
			if(cc_type.indexOf(trainCode) >= 0){
				html += "<tr align='center'>"+ $(this).html() +"</tr>";
			}
		})
		if(html==""){
			html=tbody;
		}
		$("#tbody").html(html);
	});
	
	$("#myModal").on("hide.bs.modal", function () {
		flag=true;
	});
	var tbody="";
	$("#autoPlay").click(function(){
		var myAuto =document.getElementById('myaudio');
		if($(this).val()=='播放'){
			myAuto.play();
			$(this).val("停止")
		}else{
			myAuto.pause();
			myAuto.currentTime = 0;
			$(this).val("播放");
		}
	})
	
	$("#user_name").val(getCookie("user_name"));
	$("#password").val(getCookie("password"));
	
	//登录
	$("#loginBtn").click(function(){
		var user_name = $.trim($("#user_name").val());
		var password = $.trim($("#password").val());
		if(user_name == null || user_name == ''){
			alert("请输入用户名！");
			return ;
		}
		if(password == null || password == ''){
			alert("请输入密码！");
			return ;
		}
		
		var code = "";
		var hovObj = $(document.getElementById('imgIFrame').contentWindow.document.body).find(".touclick-hov");
		var imagePosition = $(document.getElementById('imgIFrame').contentWindow.document.body).find("#touclick-image").offset();
		//console.log(imagePosition);
		hovObj.each(function(){
			//console.log($(this).attr("left"));
			//console.log($(this).attr("top"));
			var left = parseInt($(this).attr("left")) + 3 - parseInt(imagePosition.left);
			var top = parseInt($(this).attr("top")) - 16 - parseInt(imagePosition.top);
			code += "," + left + "," + top;
		})
		code = code.substring(1);
		var data = {randCode:code};
		//console.log(data);
		$.ajax({
			type : "GET",
			isTakeParam: false,
			dataType: "json",
			crossDomain: true,
			beforeSend: function(k) {
                k.setRequestHeader("If-Modified-Since", "0");
                k.setRequestHeader("Cache-Control", "no-cache")
            },
        	data: data,
			url : path + "/index/checkRandCodeAnsyn.do",
			success : function(data) {
				if(data.data.msg == 'TRUE' && data.data.result == 1){
					 $.ajax({
							type : "GET",
							isTakeParam: false,
							dataType: "json",
							crossDomain: true,
			            	data: {randCode:code, user_name:user_name, password:password},
							url : path + "/index/loginAysnSuggest.do",
							success : function(result) {
								if(result.respCode == 0){
									setCookie("user_name", user_name, 15);
									setCookie("uName", result.data, 15);
									setCookie("password", password, 15);
									location.href = path + "/index/query.do";
								}else{
									alert(result.respMsg);
									$("#imgIFrame").attr("src", path + "/index/img.do");
								}
							}
						});
				}else{
					alert("验证码错误！");
					$("#imgIFrame").attr("src", path + "/index/img.do");
				}
			}
		});
	});
	
	
	$("#savePassengerBtn").click(function(){
		var ids = "";
		$("input[name=passengerIndex]:checkbox:checked").each(function(){
			ids += "," + $(this).val();
		});
		
		if(ids != null && ids != ''){
			setCookie("passengerIds", ids.substring(1), 15);
			$("#passengerSpan").text(ids.substring(1));
		}else{
			alert("请选择乘车人！");
			return ;
		}
	});
})