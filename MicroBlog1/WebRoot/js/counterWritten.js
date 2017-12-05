function checkContent() {
	var maxlimit = 140;
	var content = document.getElementById("writeContent");
	var length = content.value.length;<!--content.length;-->
	

	var contentMsg = document.getElementById( "counterWritten"   );
	if(length>140) {
		var contentStr=String(content.value);
		content.value=contentStr.substr(0,[140]);

	}
	else
		contentMsg.innerHTML =  "<font color='red'>"  
                        + (maxlimit-length)   
                        + "</font> words remained";
}
function readyToGet(){
	var searchInput=document.getElementById("searchInput");
	var searchInputStr=String(searchInput.value);
	searchInput.value="";
}
function searchForTopic(){
}
