<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuidMe會員驗證Email</title>
</head>
<body>
<%
	String account = request.getParameter("account");
	String token = request.getParameter("token");
	String url = "http:localhost:8081/xxxxx?token="+token;
%>
	<tbody>
		<tr>
			<td width="30" style="display: block; width: 30px">&nbsp;&nbsp;&nbsp;</td>
			<td style=""><table border="0" cellspacing="0" cellpadding="0"
					style="border-collapse: collapse">
					<tbody>
						<tr>
							<td style=""><p
									style="padding: 0; margin: 0; text-align: center; color: #000000; font-size: 25px"><%= account%>，歡迎使用
									GuidMe！</p>
								<p
									style="padding: 0; margin: 0; text-align: center; color: #565a5c; font-size: 18px">
									首先感謝您註冊成為GuidMe會員，為了使您的註冊即日生效<br>敬請啟用您的帳戶以完成最後註冊的程序!
								</p></td>
						</tr>
						<tr style="">
							<td height="30" colspan="1" style="line-height: 30px">&nbsp;</td>
						</tr>
						<tr>
							<td style=""><a href="<%= url%>"
								target="_blank"
								rel="noopener noreferrer" data-auth="NotApplicable"
								style="color: #3b5998; text-decoration: none; display: block; width: 370px"><table
										border="0" width="100%" cellspacing="0" cellpadding="0"
										style="border-collapse: collapse">
										<tbody>
											<tr>
												<td
													style="border-collapse: collapse; border-radius: 3px; text-align: center; display: block; border: solid 1px #009fdf; padding: 10px 16px 14px 16px; margin: 0 2px 0 auto; min-width: 80px; background-color: #47A2EA"><a
													href="<%= url%>"
													target="_blank" rel="noopener noreferrer"
													data-auth="NotApplicable"
													style="color: #3b5998; text-decoration: none; display: block"><center>
															<font size="3"><span
																style="font-family: &amp; amp; quot; Helvetica Neue&amp;amp; quot; , Helvetica , Roboto, Arial, sans-serif, serif, EmojiFont; white-space: nowrap; font-weight: bold; vertical-align: middle; color: rgb(253, 253, 253); font-size: 16px; line-height: 16px;">確認你的電子郵件地址</span></font>
														</center></a></td>
											</tr>
										</tbody>
									</table></a></td>
						</tr>
						<tr style="">
							<td height="30" colspan="3" style="line-height: 30px">&nbsp;</td>
						</tr>
						<tr>
							<td style="border-top: solid 1px #DBDBDB"></td>
						</tr>
					</tbody>
				</table></td>
			<td width="30" style="display: block; width: 30px">&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</tbody>
</body>
</html>
