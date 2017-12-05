   // 缩放图片
   function imgToSize(oBool) {
     var oImg = document.all('oImg');
     oImg.style.zoom = parseInt(oImg.style.zoom) + (oBool ? 10 : -10) + '%';
	 document.getElementById("height").value=parseInt(oImg.height)*parseInt(oImg.style.zoom)/100;
	 document.getElementById("width").value=parseInt(oImg.width)*parseInt(oImg.style.zoom)/100;
   }

   // 拖动图片
   var oBoolean = false, oLeftSpace = 0, oTopSpace = 0;
   function mStart() {
      oBoolean = true;
      if (oBoolean&&window.event.button==1) {
         var oImg = document.all('oImg');
         oLeftSpace = window.event.clientX - oImg.style.pixelLeft;
         oTopSpace = window.event.clientY - oImg.style.pixelTop;
		 
      }
   }

   function mEnd() {
      oBoolean = false;
   }

   function drag() {
      if (oBoolean) {
         var oImg = document.all('oImg');
         oImg.style.pixelLeft = window.event.clientX - oLeftSpace;
         oImg.style.pixelTop = window.event.clientY - oTopSpace;
		 document.getElementById("showH").value=oImg.offsetLeft;
         document.getElementById("showV").value=oImg.offsetTop;
         
         return false;
      }
   }