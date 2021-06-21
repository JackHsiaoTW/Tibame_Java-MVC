<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>HW21_06</title>
<style type="text/css">
.del {
	display: none;
}
</style>
<script type="text/javascript">
	function init() {
		let myFile = document.getElementById("myFile");
		let preview = document.getElementById('preview');
		let btn = document.getElementById("btn")
		// 2. 對myFile物件註冊change事件 - 改變選擇的檔案時觸發
		myFile.addEventListener("change", function() {
			if (this.files) {
				for (let i = 0; i < this.files.length; i++) {
					let file = this.files[i];
					if (file.type.indexOf("image") > -1) {
						let reader = new FileReader();
						reader.addEventListener("load", function(e) {
							let label = document.createElement("label");
							let img = document.createElement("img");
							img.setAttribute("height", "200px");
							img.setAttribute("weight", "150px");
							let checkbox = document.createElement("input");
							checkbox.setAttribute("type", "checkbox");
							checkbox.setAttribute("class", "checkbox");
							checkbox.setAttribute("name","checked");
							console.log(checkbox)
							img.src = e.target.result;
							preview.append(label);
							label.append(checkbox);
							label.append(img);
						});
						reader.readAsDataURL(file);
					} else {
						alert('請上傳圖片！');
					}
					btn.addEventListener("click", function(e) {
						e.preventDefault();
						let delcheck = document
								.getElementsByClassName("checkbox")
						for (let j = 0; j < delcheck.length; j++) {
							if (delcheck[j].checked === true) {
								delcheck[j].parentElement.classList.add("del")
								
							}
						}
					})
				}
			}
		})
	}
	window.onload = init;
</script>
</head>

<body>
	<FORM
		action="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do"
		method="post" enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<label>請上傳圖片檔案</label>
				<!-- 這裡一定要有一個<input type="file">的元素，當上傳點 -->
				<input type="file" name="upfile1" id="myFile" multiple>
			</div>
			<div class="row"></div>
			<div class="row">
				<label>檔案內容: </label>
				<div>
					<input id="btn" type="submit" value="刪除"></input>
				</div>
				<label>體驗編號:</label> <input type="text" name="exper_photo_no">
				<input type="hidden" name="action" value="upDate_By_Exper_Photo_No">
				<input type="submit" value="上傳">
				<div id="preview"></div>
			</div>
		</div>
	</FORM>

</body>

</html>