/*得到XMLHttpRequest对象*/  
function getXHR(){  
    var xmlHttp;  
    try {  
        xmlHttp=new XMLHttpRequest();  
    }catch(e)  
    {  
        try{  
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");  
        }  
        catch(e)  
        {  
            try{  
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");  
            }  
            catch(e)  
            {  
                alert("你的浏览器不支持ajax");  
                return false;  
            }  
              
        }  
          
    }  
    return xmlHttp;  
}  