/**
 * 居中弹出新窗口
 * 
 * @return String
 */
function popWindow(model,url,width,height){
    xposition=0; yposition=0;
    if ((parseInt(navigator.appVersion) >= 4 ))
    {
    xposition = (screen.width - width) / 2;     //窗体居中的x坐标
    yposition = (screen.height - height) / 2;   //窗体居中的y坐标
    }
    if(model==1){//"dialogWidth:300px;dialogHeight:120px;center:yes;help:No;status:no;resizable:Yes;edge:sunken"
        theproperty= "dialogWidth:"+width+";"         //打开窗口的属性
        + "dialogHeight:"+height+";" 
        + "location:0;center:1;help:0;" 
        + "menubar:0;"
        + "resizable:1;"
        + "scrollbars:1;"
        + "status:0;" 
        + "titlebar:0;"
        + "toolbar:0;"
        + "hotkeys:0;"
        + "screenx:" + xposition + ";" //仅适用于Netscape
        + "screeny:" + yposition + ";" //仅适用于Netscape
        + "dialogLeft:" + xposition + ";" //IE
        + "dialogTop:" + yposition; //IE 
    	window.showModalDialog(url,"_bank",theproperty );     //打开窗口
    }else {
        theproperty= "width="+width+","         //打开窗口的属性
        + "height="+height+"," 
        + "location=0," 
        + "menubar=0,"
        + "resizable=1,"
        + "scrollbars=1,"
        + "status=0," 
        + "titlebar=0,"
        + "toolbar=0,"
        + "hotkeys=0,"
        + "screenx=" + xposition + "," //仅适用于Netscape
        + "screeny=" + yposition + "," //仅适用于Netscape
        + "left=" + xposition + "," //IE
        + "top=" + yposition; //IE 
    	window.open(url,"_bank",theproperty );     //打开窗口
    }
    
}

function $(objId){ return document.getElementById(objId);}

/**
 * 清除前后空字符
 * 
 * @return String
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 取文件名后缀
 * 
 * @return String
 */
String.prototype.suffix = function() {
	return this.match(/^(.*)(\.)(.{1,8})$/)[3];
}

/**
 * 验证字符串是否全由数字组成
 * 
 * @return 由数字组成返回true,否则返回false.
 */
function checkNum(str) {
	if (str.match(/\d/) == null)
		return false;// alert("输入数值错误！");
	else
		return true;// alert("数值正确！");
}

/**
 * 验证邮件地址格式是否正确
 * 
 * @param mail地址
 * @return 正确返回true,否则返回false.
 */
function ismail(mail) {
	return (new RegExp(
			/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)
			.test(mail));
}

/**
 * 限制输入字符串的长度
 * 
 * @param str 要判断的字符串
 * @param limitLength 限制的长度
 * @return 正确返回true,否则返回false.
 */
function checkStrLength(str,limitLength) {
	var n=0;
	for(var i=0;i<str.length;i++){
		var code = str.charCodeAt(i);
		if(code>255){
			n=n+2;
		}else {
			n=n+1
		}
	}
	if(n>limitLength){
		return false;
	}
	return true;
}

/****************************************************************
 * 日期的加减   javascript                                                         
 *                                                              *
 * @param type "加减的类型" NumDay "加减的值" dtDate "被加减的日期"
 *              
 * @return    yyyyMM                                            *
 ****************************************************************/
// addDate("5",5,"2004/12/1 00:00:00")
function addDate(type,NumDay,dtDate){
	//var date = new Date(dtDate);//传入的是非日期类型 
	var date = dtDate; //传入的是日期类型
	type = parseInt(type) //类型 
	lIntval = parseInt(NumDay)//间隔
	switch(type){
		case 6 ://年
			date.setYear(date.getYear() + lIntval)
			break;
		case 7 ://季度
			date.setMonth(date.getMonth() + (lIntval * 3) )
			break;
		case 5 ://月
			date.setMonth(date.getMonth() + lIntval)
			break;
		case 4 ://天
			date.setDate(date.getDate() + lIntval)
			break
		case 3 ://时
			date.setHours(date.getHours() + lIntval)
			break
		case 2 ://分
			date.setMinutes(date.getMinutes() + lIntval)
			break
		case 1 ://秒
			date.setSeconds(date.getSeconds() + lIntval)
			break;
		default:

	} 
	return date.getYear() +'-' +  (date.getMonth()+1) + '-' +date.getDate()+ ' '+ date.getHours()+':'+date.getMinutes()+':'+date.getSeconds()
}

/***************************************************************
*javascript取小数
*
*   item 需要保留小数的值
*   pos  需要保留几位小数
*
***************************************************************/
function chgXdec(item,pos){	
	var src = (item*100).toString();   
	//舍小数用Math.floor，四舍五入用Math.round
	var v = Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);//
	//alert(v+'%');
}


String.prototype.replaceAll  = function(s1,s2){   
	return this.replace(new RegExp(s1,"gm"),s2);   
} 