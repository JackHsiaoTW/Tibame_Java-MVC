function init() {
    let member_pic = document.getElementById("memberPic");
    let preview = document.getElementById("preview");

    member_pic.addEventListener('change', function(e) {
        let files = e.target.files;
        // console.log(files);

        if (files) {
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                if (file.type.indexOf('image') > -1) {
                    // console.log(file.name);
                    // console.log(file.size);
                    // console.log(file.type);

                    let reader = new FileReader();
                    reader.addEventListener('load', function(e) {
                    	let oldImg = document.querySelector("#preview img");
                    	if(oldImg){
                    		oldImg.remove();
                    	}
                    	
                        let result = e.target.result;
                        let img = document.createElement("img");
                        img.setAttribute('src', result);
                        img.setAttribute('style', "width:100px; height:100px");
                        preview.append(img);
                    });

                    reader.readAsDataURL(file);
                } else {
                    alert("請上傳圖片");
                }
            }
//            addPicJs();
//            showPic();
        }
    });
    
    
    // 檢查密碼
    let sendRegister = document.getElementById("sendRegister");
    if(sendRegister){
    	sendRegister.addEventListener('click', function(e) {
        	console.log('checkPwd:'+checkPwd());
        	if(!checkPwd()){
        		alert("密碼不一致，請重新輸入密碼!");
        		e.preventDefault();
        	}
        });
    }
    
    

}

function showPic(){
	let preview = document.getElementById("preview");
	preview.classList.add('show');
	
}

function addPicJs(){
    // let jsSrc = `<link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">`;
    let jsSrc = document.createElement('link');
    jsSrc.setAttribute('href','/CEA102G311111/resources/css/register.css');
    jsSrc.setAttribute('rel','stylesheet');
    let title = document.getElementsByTagName("title");
    console.log("-------");
    console.log(jsSrc);
    title[0].before(jsSrc);
}

function checkPwd(){
	let password = document.getElementById("password").value;
	let repeatPassword = document.getElementById("repeatPassword").value;
	let isCorrect = true;
	if(password !== repeatPassword){
		isCorrect = false;
	}
	console.log('password:'+password);
	console.log('repeatPassword:'+repeatPassword);
	return isCorrect;
}



window.addEventListener('load', init);