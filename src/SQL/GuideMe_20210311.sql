CREATE DATABASE IF NOT EXISTS guideme;
USE guideme;

DROP TABLE IF EXISTS `Emplo_Permi`;
DROP TABLE IF EXISTS `service_message`;
DROP TABLE IF EXISTS `favorite_exper`;
DROP TABLE IF EXISTS `Exper_Report`;
DROP TABLE IF EXISTS `Exper_Rate`;
DROP TABLE IF EXISTS `Exper_Application`;
DROP TABLE IF EXISTS `Exper_Order`;
DROP TABLE IF EXISTS `Exper_Photo`;
DROP TABLE IF EXISTS `Experience`;
DROP TABLE IF EXISTS `Exper_Type`;
DROP TABLE IF EXISTS `Message`;
DROP TABLE IF EXISTS `Article_Report`;
DROP TABLE IF EXISTS `Article_Replication`;
DROP TABLE IF EXISTS `Itine_Item`;
DROP TABLE IF EXISTS `Itine_Member`;
DROP TABLE IF EXISTS `Friend_Invitation`;
DROP TABLE IF EXISTS `Friend_List`;
DROP TABLE IF EXISTS `Order_Item`;
DROP TABLE IF EXISTS `Favorite_Product`;
DROP TABLE IF EXISTS `Product_Rate`;
DROP TABLE IF EXISTS `Credit_Card`;
DROP TABLE IF EXISTS `Notify`;
DROP TABLE IF EXISTS `Notification`;
DROP TABLE IF EXISTS `Liscense`;
-- DROP TABLE IF EXISTS `Exper_Order`;
-- DROP TABLE IF EXISTS `Experience`;
DROP TABLE IF EXISTS `Employee`;
DROP TABLE IF EXISTS `Function`;
DROP TABLE IF EXISTS `Article`;
DROP TABLE IF EXISTS `Attraction`;
DROP TABLE IF EXISTS `Itine`;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS `Product`;
DROP TABLE IF EXISTS `Member`;
DROP TABLE IF EXISTS `FAQ`;
DROP TABLE IF EXISTS `Promotion`;
DROP TABLE IF EXISTS `News`;

CREATE TABLE `Member` (
  `Member_No` int NOT NULL AUTO_INCREMENT COMMENT '會員ID',
  `Account` varchar(20) NOT NULL COMMENT '帳號',
  `Password` varchar(20) NOT NULL COMMENT '密碼',
  `Name` varchar(20) NOT NULL COMMENT '姓名',
  `ID_Number` varchar(10) NOT NULL COMMENT '身分證字號',
  `Birth_Date` date DEFAULT NULL COMMENT '出生日期',
  `Phone` varchar(20) DEFAULT NULL COMMENT '電話',
  `Email` varchar(30) NOT NULL COMMENT 'email',
  `Member_State` int(1) DEFAULT 0 COMMENT '會員狀態 \n0:未驗證 1:一般狀態  2:停權',
  `Member_Pic` LONGBLOB DEFAULT NULL COMMENT '會員照片',
  `Lisce_Pic1` LONGBLOB DEFAULT NULL COMMENT '證照照片1',
  `Lisce_Pic2` LONGBLOB DEFAULT NULL COMMENT '證照照片2',
  `Lisce_Pic3` LONGBLOB DEFAULT NULL COMMENT '證照照片3',
  `Lisce_Name1` varchar(30) DEFAULT NULL COMMENT '證照名稱1',
  `Lisce_Name2` varchar(30) DEFAULT NULL COMMENT '證照名稱2',
  `Lisce_Name3` varchar(30) DEFAULT NULL COMMENT '證照名稱3',
  PRIMARY KEY (`Member_No`)
);

CREATE TABLE `Employee` (
  `Emplo_No` int NOT NULL AUTO_INCREMENT COMMENT '員工ID',
  `Account` varchar(20) NOT NULL COMMENT '帳號',
  `Password` varchar(20) NOT NULL COMMENT '密碼',
  `Name` varchar(20) NOT NULL COMMENT '姓名',
  `Phone` varchar(20) NOT NULL COMMENT '電話',
  `Start_From` date NOT NULL COMMENT '到職日',
  `Emp_State` bit(1) DEFAULT NULL COMMENT '員工狀態 \n1:在職 0:離職',
  PRIMARY KEY (`Emplo_No`)
);

CREATE TABLE `Function` (
  `Funct_No` int NOT NULL AUTO_INCREMENT COMMENT '功能編號',
  `Funct_Name` varchar(40) NOT NULL COMMENT '功能名稱',
  PRIMARY KEY (`Funct_No`)
);

CREATE TABLE `Emplo_Permi` (
  `Emplo_No` int NOT NULL COMMENT '員工ID',
  `Funct_No` int NOT NULL COMMENT '功能編號',

  constraint fk_Emplo_Permi_Employee
  foreign key (Emplo_No) references `Employee` (Emplo_No),
  constraint fk_Emplo_Permi_Function
  foreign key (Funct_No) references `Function` (Funct_No),

  PRIMARY KEY (`Emplo_No`,`Funct_No`)
);

CREATE TABLE `service_message` (
  `Service_Message_No` int NOT NULL AUTO_INCREMENT COMMENT '客服訊息ID',
  `Member_No` int DEFAULT NULL COMMENT '會員ID',
  `Emplo_No` int DEFAULT NULL COMMENT '員工ID',
  `Content` varchar(1000) NOT NULL COMMENT '客服訊息內容',
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '客服訊息時間',
  `Direction` bit(1) DEFAULT NULL COMMENT '發話方向 \n1:員工到會員 0:會員到員工 ',

  constraint fk_service_message_Member
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_service_message_Employee
  foreign key (Emplo_No) references `Employee` (Emplo_No),

  PRIMARY KEY (`Service_Message_No`)
);

CREATE TABLE `Exper_Type` (
  `Exper_Type_No` int NOT NULL AUTO_INCREMENT COMMENT '體驗種類ID',
  `Exper_Type_Name` varchar(100) NOT NULL COMMENT '體驗種類名稱',
  PRIMARY KEY (`Exper_Type_No`)
);

CREATE TABLE `Experience` (
  `Exper_No` int NOT NULL AUTO_INCREMENT COMMENT '體驗ID',
  `Host_No` int DEFAULT NULL COMMENT '發表人ID',
  `Checker_No` int DEFAULT 1 COMMENT '審核者ID',
  `Name` varchar(200) NOT NULL COMMENT '體驗名稱',
  `Price` int NOT NULL COMMENT '體驗價格',
  `Exper_Descr` varchar(1000) DEFAULT NULL COMMENT '體驗敘述',
  `Exper_Status` int DEFAULT 1 COMMENT '體驗狀態 \n0:審核中 1:已審核 2:下架',
  `Exper_Type_No` int DEFAULT NULL COMMENT '體驗種類ID',

  constraint fk_Experience_Member
  foreign key (Host_No) references `Member` (Member_No),
  constraint fk_Experience_Employee
  foreign key (Checker_No) references `Employee` (Emplo_No),
  constraint fk_Experience_Exper_Type
  foreign key (Exper_Type_No) references `Exper_Type` (Exper_Type_No),

  PRIMARY KEY (`Exper_No`)
);

CREATE TABLE `Exper_Photo` (
  `Exper_Photo_No` int NOT NULL AUTO_INCREMENT COMMENT '體驗照片ID',
  `Exper_No` int NOT NULL COMMENT '體驗ID',
  `Photo` LONGBLOB DEFAULT NULL COMMENT '體驗照片',

  constraint fk_Exper_Photo_Experience
  foreign key (Exper_No) references `Experience` (Exper_No),

  PRIMARY KEY (`Exper_Photo_No`)
);

CREATE TABLE `favorite_exper` (
  `Member_No` int NOT NULL AUTO_INCREMENT COMMENT '會員ID',
  `Exper_No` int NOT NULL COMMENT '體驗ID',

  constraint fk_favorite_exper_Member
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_favorite_exper_Experience
  foreign key (Exper_No) references `Experience` (Exper_No),

  PRIMARY KEY (`Member_No`,`Exper_No`)
);

CREATE TABLE `Exper_Report` (
  `Report_No` int NOT NULL AUTO_INCREMENT COMMENT '檢舉單ID',
  `Reporter_No` int DEFAULT NULL COMMENT '檢舉人ID',
  `Reported_Exper_No` int DEFAULT NULL COMMENT '被檢舉體驗ID',
  `Reason` varchar(1000) NOT NULL COMMENT '檢舉理由',
  `Report_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '檢舉時間',
  `Reply_Content` varchar(1000) DEFAULT NULL COMMENT '回應內容',
  `Reply_Time` timestamp DEFAULT NULL  COMMENT '回應時間',
  `Is_Checked` int DEFAULT 0 COMMENT '檢舉體驗處理狀態 \n0:尚未處理  1:已成立 2:未成立',

  constraint fk_Exper_Report_Member
  foreign key (Reporter_No) references `Member` (Member_No),
  constraint fk_Exper_Report_Experience
  foreign key (Reported_Exper_No) references `Experience` (Exper_No),

  PRIMARY KEY (`Report_No`)
);

CREATE TABLE `Exper_Order` (
  `Exper_Order_No` int NOT NULL AUTO_INCREMENT COMMENT '體驗梯次ID',
  `Exper_No` int DEFAULT NULL COMMENT '體驗ID',
  `Apply_Start` timestamp NOT NULL COMMENT '報名開始時間',
  `Apply_End` timestamp NOT NULL COMMENT '報名結束時間',
  `Exper_Order_Start` timestamp NOT NULL COMMENT '體驗出發日期',
  `Exper_Order_End` timestamp NOT NULL COMMENT '體驗結束日期',
  `Exper_Max_Limit` int DEFAULT NULL COMMENT '報名人數上限',
  `Exper_Min_Limit` int DEFAULT NULL COMMENT '報名人數下限',
  `Exper_Now_Price` int DEFAULT NULL COMMENT '體驗當前售價',
  `Exper_Order_Status` int DEFAULT 0 COMMENT '體驗梯次出團狀態 \n0:尚未出團 1:取消出團 2:成功出團',
  `Exper_Apply_Sum` int DEFAULT NULL COMMENT '已報名人數',

  constraint fk_Exper_Order_Experience
  foreign key (Exper_No) references `Experience` (Exper_No),

  PRIMARY KEY (`Exper_Order_No`)
);

CREATE TABLE `Exper_Application` (
  `Exper_Appli_No` int NOT NULL AUTO_INCREMENT COMMENT '體驗報名ID',
  `Member_No` int DEFAULT NULL COMMENT '會員ID',
  `Exper_Order_No` int DEFAULT NULL COMMENT '體驗梯次ID',
  -- `Appli_Date` date NOT NULL DEFAULT (CURRENT_DATE) COMMENT '報名日期',
  `Number` int NOT NULL COMMENT '報名人數',
  `Sum` int NOT NULL COMMENT '總計金額',
  `Exper_Appli_Status` bit(1) DEFAULT NULL COMMENT '體驗報名狀態 \n0:確認出發 1:取消報名',
  `Exper_Payment_Status` int DEFAULT 0 COMMENT '體驗繳費狀態 \n0:未付款 1:已確認付款 2:退款中 3:已完成退款',
  `Exper_Appli_Memo` varchar(500) DEFAULT NULL COMMENT '體驗報名備註',

  constraint fk_Exper_Application_Member
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_Exper_Application_Exper_Order
  foreign key (Exper_Order_No) references `Exper_Order` (Exper_Order_No),

  PRIMARY KEY (`Exper_Appli_No`)
);

CREATE TABLE `Message` (
  `Message_No` int NOT NULL AUTO_INCREMENT COMMENT '訊息ID',
  `Speacker_No` int NOT NULL COMMENT '發話方ID',
  `Receiver_No` int NOT NULL COMMENT '接收方ID',
  `Content` varchar(200) NOT NULL COMMENT '訊息內容',
  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '訊息時間',
  `Direction` bit(1) DEFAULT NULL COMMENT '發話方向 \n1:Speacker到Receiver 0:Receiver到Speacker ',
  `IsRead` bit(1) NOT NULL COMMENT '已讀 \n0: 未讀 1:已讀',

  constraint fk_Message_MemberSpeacker
  foreign key (Speacker_No) references `Member` (Member_No),
  constraint fk_Message_MemberReceiver
  foreign key (Receiver_No) references `Member` (Member_No),

  PRIMARY KEY (`Message_No`)
);

CREATE TABLE `Article` (
  `Article_No` int NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `Publisher_No` int NOT NULL COMMENT '發表人ID',
  `Title` varchar(30) NOT NULL COMMENT '文章標題',
  `Content` varchar(1000) NOT NULL COMMENT '文章內容',
  `Publish_Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '發表時間',
  `Article_Status` bit(1) NOT NULL COMMENT '文章狀態 \n0:在架上 1:已下架',
  `Article_Pic` LONGBLOB DEFAULT NULL COMMENT '文章照片',

  constraint fk_Article_Member
  foreign key (Publisher_No) references `Member` (Member_No),

  PRIMARY KEY (`Article_No`,`Publisher_No`)
);

CREATE TABLE `Article_Report` (
  `Report_No` int NOT NULL AUTO_INCREMENT COMMENT '檢舉單ID',
  `Reporter_No` int DEFAULT NULL COMMENT '檢舉人ID',
  `Reported_Artic_No` int DEFAULT NULL COMMENT '被檢舉文章ID',
  `Reason` varchar(1000) NOT NULL COMMENT '檢舉理由',
  `Report_Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '檢舉時間',
  `Reply_Content` varchar(1000) NOT NULL COMMENT '回應內容',
  `Reply_Time` TIMESTAMP DEFAULT NULL COMMENT '回應時間',
  `Report_Status` INT NOT NULL COMMENT '檢舉狀態 \n0:未處理  1:已成立 2:未成立',

  constraint fk_Article_Report_Member
  foreign key (Reporter_No) references `Member` (Member_No),
  constraint fk_Emplo_Permi_Article
  foreign key (Reported_Artic_No) references `Article` (Article_No),

  PRIMARY KEY (`Report_No`)
);

CREATE TABLE `Article_Replication` (
  `Article_Repli_No` int NOT NULL AUTO_INCREMENT COMMENT '回覆ID',
  `Reply_Article_No` int DEFAULT NULL COMMENT '回覆文章ID',
  `Replier_No` int DEFAULT NULL COMMENT '回覆人ID',
  `Content` varchar(1000) NOT NULL COMMENT '回覆內容',
  `Reply_Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回覆時間',

  constraint fk_Article_Replication_Article
  foreign key (Reply_Article_No) references `Article` (Article_No),
  constraint fk_Article_Replication_Member
  foreign key (Replier_No) references `Member` (Member_No),

  PRIMARY KEY (`Article_Repli_No`)
);

CREATE TABLE `Attraction` (
  `Attra_No` int NOT NULL AUTO_INCREMENT COMMENT '景點ID',
  `Sort` varchar(10) NOT NULL COMMENT '分類',
  `Attra_Name` varchar(200) NOT NULL COMMENT '名稱',
  `Descr` varchar(2000)  DEFAULT NULL COMMENT '描述',
  `Location` varchar(200) DEFAULT NULL COMMENT '地點',
  `Is_On_Shelf` int NOT NULL COMMENT '在架狀態 \n0:未審核 1:在架上 2:下架',
  `Attra_Pic1` varchar(300) DEFAULT NULL COMMENT '景點照片',
  PRIMARY KEY (`Attra_No`)
);

CREATE TABLE `Itine` (
  `Itine_No` int NOT NULL AUTO_INCREMENT COMMENT '行程ID',
  `Itine_Name` varchar(30) NOT NULL COMMENT '行程名稱',
  `Builder` int DEFAULT NULL COMMENT '發話方創建人 IDID',
  `Update_Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
  `Itine_Status` int NOT NULL COMMENT '行程狀態 \n0:未完成1:已完成2:刪除3:已出遊',
  `Itine_Board` varchar(2000) DEFAULT NULL COMMENT '行程留言板',

  constraint fk_Itine_Member
  foreign key (Builder) references `Member` (Member_No),

  PRIMARY KEY (`Itine_No`)
);

CREATE TABLE `Itine_Item` (
  `Itine_No` int NOT NULL COMMENT '行程ID',
  `Attra_No` int NOT NULL COMMENT '景點ID',
  `Start_Time` TIMESTAMP NOT NULL COMMENT '開始時間',
  `End_Time` TIMESTAMP NOT NULL COMMENT '結束時間',
  `Note` varchar(1000) NULL COMMENT '備註',
  `Manager` int DEFAULT NULL COMMENT '負責人ID',
  `IsDone` bit(1) DEFAULT NULL COMMENT '是否完成 \n0:未完成 1:完成',
  `Finish_Date` DATE DEFAULT NULL COMMENT '完成日期',
  `Task_Note` varchar(1000) DEFAULT NULL COMMENT '分工備註',

  constraint fk_Itine_Item_Itine
  foreign key (Itine_No) references `Itine` (Itine_No),
  constraint fk_Itine_Item_Attraction
  foreign key (Attra_No) references `Attraction` (Attra_No),
  constraint fk_Itine_Item_Member
  foreign key (Manager) references `Member` (Member_No),

  PRIMARY KEY (`Itine_No`,`Attra_No`)
);

CREATE TABLE `Itine_Member` (
  `Itine_No` int NOT NULL COMMENT '行程ID',
  `Member_No` int NOT NULL COMMENT '會員ID',
  `IsEditable` int NOT NULL COMMENT '可否編輯 \n0:尚未加入1:不能編輯2:可以編輯',

  constraint fk_Itine_Member_Itine
  foreign key (Itine_No) references `Itine` (Itine_No),
  constraint fk_Itine_Member_Member
  foreign key (Member_No) references `Member` (Member_No),

  PRIMARY KEY (`Itine_No`,`Member_No`)
);

CREATE TABLE `Friend_Invitation` (
  `Friend_Invit_No` int NOT NULL AUTO_INCREMENT COMMENT '邀請ID',
  `Adder` int DEFAULT NULL COMMENT '加入方',
  `Confirmer` int DEFAULT NULL COMMENT '確認方',

  constraint fk_Friend_Invitation_MemberAdder
  foreign key (Adder) references `Member` (Member_No),
  constraint fk_Friend_Invitation_MemberConfirmer
  foreign key (Confirmer) references `Member` (Member_No),

  PRIMARY KEY (`Friend_Invit_No`)
);

CREATE TABLE `Friend_List` (
  `Member_No` int NOT NULL COMMENT '會員ID',
  `Friend_No` int NOT NULL COMMENT '好友ID',

  constraint fk_Friend_List_Member
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_Friend_List_MemberFriend
  foreign key (Friend_No) references `Member` (Member_No),

  PRIMARY KEY (`Member_No`,`Friend_No`)
);

CREATE TABLE `Order` (
  `Order_No` int NOT NULL AUTO_INCREMENT COMMENT '訂單ID',
  `Member_No` int DEFAULT NULL COMMENT '會員ID',
  `Order_Date` date NOT NULL DEFAULT (CURRENT_DATE) COMMENT '更新時間',
  `Sum` int NULL COMMENT '總計',
  `Credit_Card_No` varchar(20) NOT NULL COMMENT '信用卡卡號',

  constraint fk_Order_Member
  foreign key (Member_No) references `Member` (Member_No),

  PRIMARY KEY (`Order_No`)
);

CREATE TABLE `Product` (
  `Product_No` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `Product_Name` varchar(30) NOT NULL COMMENT '商品名稱',
  `List_Price` int NOT NULL COMMENT '定價',
  `Descr` varchar(1000) NOT NULL COMMENT '商品敘述',
  `Total_Rate_Count` int DEFAULT NULL COMMENT '總評價筆數',
  `Total_Rate` int DEFAULT NULL COMMENT '總評價分',
  `Product_Status` bit(1) DEFAULT NULL COMMENT '商品狀態 \n0:已上架 1:未上架',
  `Product_Pic1` LONGBLOB DEFAULT NULL COMMENT '商品照片一',
  `Product_Pic2` LONGBLOB DEFAULT NULL COMMENT '商品照片二',
  `Product_Pic3` LONGBLOB DEFAULT NULL COMMENT '商品照片三',
  PRIMARY KEY (`Product_No`)
);

CREATE TABLE `Order_Item` (
  `Order_No` int NOT NULL COMMENT '訂單ID',
  `Product_No` int NOT NULL COMMENT '商品ID',
  `Product_Count` int NOT NULL COMMENT '商品數量',
  `Selling_Price` int NOT NULL COMMENT '售價',

  constraint fk_Order_Item_Order
  foreign key (Order_No) references `Order` (Order_No),
  constraint fk_Order_Item_Product
  foreign key (Product_No) references `Product` (Product_No),

  PRIMARY KEY (`Order_No`,`Product_No`)
);

CREATE TABLE `Favorite_Product` (
  `Member_No` int NOT NULL COMMENT '會員ID',
  `Favorite_Product_No` int NOT NULL COMMENT '收藏商品ID',

  constraint fk_Favorite_Product_Order
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_Favorite_Product_Product
  foreign key (Favorite_Product_No) references `Product` (Product_No),

  PRIMARY KEY (`Member_No`,`Favorite_Product_No`)
);

CREATE TABLE `Product_Rate` (
  `Member_No` int NOT NULL COMMENT '會員ID',
  `Product_No` int NOT NULL COMMENT '商品ID',
  `Rate` int NOT NULL COMMENT '評價星等',
  `Comment` varchar(1000) DEFAULT NULL COMMENT '評價內容',

  constraint fk_Product_Rate_Member
  foreign key (Member_No) references `Member` (Member_No),
  constraint fk_Product_Rate_Product
  foreign key (Product_No) references `Product` (Product_No),

  PRIMARY KEY (`Member_No`,`Product_No`)
);

CREATE TABLE `Credit_Card` (
  `Credit_Card_No` varchar(16) NOT NULL COMMENT '信用卡號',
  `Member_No` int NOT NULL COMMENT '會員ID',
  `Credit_Card_Name` varchar(20) DEFAULT NULL COMMENT '信用卡名稱',
  `Exp_Date` date DEFAULT NULL COMMENT '信用卡到期日',

  constraint fk_Credit_Card_Member
  foreign key (Member_No) references `Member` (Member_No),

  PRIMARY KEY (`Credit_Card_No`,`Member_No`)
);

CREATE TABLE `Notify` (
  `Notify_No` int NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `Notify_Person` int DEFAULT NULL COMMENT '通知對象ID',
  `Notify_Content` varchar(20) NOT NULL COMMENT '通知內容',
  `Notify_Time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '通知時間',

  constraint fk_Notify_Member
  foreign key (Notify_Person) references `Member` (Member_No),

  PRIMARY KEY (`Notify_No`)
);

CREATE TABLE `FAQ` (
  `Question_No` int NOT NULL AUTO_INCREMENT COMMENT '常見問題ID',
  `Question` varchar(1000) NOT NULL COMMENT '常見問題',
  `Answer` varchar(1000) NOT NULL COMMENT '常見問題回覆',
  `Update_Time` date DEFAULT NULL DEFAULT (CURRENT_DATE) COMMENT '商品常見問題更新時間敘述',
  PRIMARY KEY (`Question_No`)
);

CREATE TABLE `Promotion` (
  `Promot_No` int NOT NULL AUTO_INCREMENT COMMENT '主打促銷ID',
  `Promot_Content` varchar(1000) NOT NULL COMMENT '主打促銷內容',
  `Promot_Start` date DEFAULT NULL COMMENT '主打促銷開始日期',
  `Promot_End` date DEFAULT NULL COMMENT '主打促銷截止日期',
  `Release_Date` date NULL DEFAULT (CURRENT_DATE) COMMENT '廣告上架日期',
  `Promot_Product_No` int DEFAULT NULL COMMENT '商品ID',
  `Promot_Product_Price` int NOT  NULL COMMENT '促銷商品價格',
  `Promot_Product_Title` varchar(50) NOT NULL COMMENT '促銷商品標題',
  `Promot_Product_Pic` LONGBLOB DEFAULT NULL COMMENT '促銷商品照片',
  PRIMARY KEY (`Promot_No`)
);

CREATE TABLE `News` (
  `News_No` int NOT NULL AUTO_INCREMENT COMMENT '最新消息ID',
  `News_Content` varchar(1000) NOT NULL COMMENT '最新消息內容',
  `Release_Date` date NULL DEFAULT (CURRENT_DATE) COMMENT '最新消息發佈日期',
  `News_Title` varchar(50) NOT NULL COMMENT '最新消息標題',
  `News_Pic` LONGBLOB DEFAULT NULL COMMENT '最新消息照片',
  PRIMARY KEY (`News_No`)
);

-- create index
CREATE INDEX index_service_message ON `service_message`(`Member_No`,`Emplo_No`);
CREATE INDEX index_Message_SR ON `Message`(`Speacker_No`,`Receiver_No`);
CREATE INDEX index_Article_No ON `Article`(`Article_No`);
CREATE INDEX index_Article_Title ON `Article`(`Title`);
CREATE INDEX index_Attraction_Name ON `Attraction`(`Attra_Name`);
CREATE INDEX index_Friend_Invitation_AC ON `Friend_Invitation`(`Adder`,`Confirmer`);
CREATE INDEX index_Friend_List_M ON `Friend_List`(`Member_No`);
CREATE INDEX index_Friend_List_F ON `Friend_List`(`Friend_No`);
CREATE INDEX index_Product_Name ON `Product`(`Product_Name`);
CREATE INDEX index_Product_Rate_MNO ON `Product_Rate`(`Member_No`);
CREATE INDEX index_Product_Rate_Rate ON `Product_Rate`(`Rate`);
CREATE INDEX index_Notify_P ON `Notify`(`Notify_Person`);

-- *************************Start Create Store Procedure*************************
-- ----friendList----findFriendListByPK-----
DELIMITER $$
DROP PROCEDURE IF EXISTS findFriendListByPK $$
CREATE PROCEDURE findFriendListByPK(memNO INT, freindNo INT)
BEGIN  
  select memNO Member_No, FriendList from ( 
	  SELECT Friend_No FriendList FROM Friend_List WHERE Member_No = memNO 
	  union 
	  SELECT Member_No FriendList FROM Friend_List WHERE Friend_No = memNO 
	)aa 
	where FriendList = freindNo ;
END $$
DELIMITER ;

-- ----friendList----findFriendByMemberName
DELIMITER $$
DROP PROCEDURE IF EXISTS findFriendByMemberName $$
CREATE PROCEDURE findFriendByMemberName(memNO INT, memberName Varchar(100))
BEGIN  
	SELECT * 
	FROM Member 
	WHERE NAME like CONCAT('%', memberName , '%') and member_no in 
	(
		SELECT Friend_No FriendList FROM Friend_List WHERE Member_No = memNO 
		union 
		SELECT Member_No FriendList FROM Friend_List WHERE Friend_No = memNO
	);
END $$
DELIMITER ;

-- ----friendInvit----findFriendInvitListBy2MemNo-----
DELIMITER $$
DROP PROCEDURE IF EXISTS findFriendInvitListBy2MemNo $$
CREATE PROCEDURE findFriendInvitListBy2MemNo(memNO INT, freindNo INT)
BEGIN 
	select aa.Friend_Invit_No, bb.Adder, bb.Confirmer from ( 
		SELECT Friend_Invit_No, Confirmer FriendInvit FROM Friend_Invitation WHERE Adder = memNO 
		union 
		SELECT Friend_Invit_No, Adder FriendInvit FROM Friend_Invitation WHERE Confirmer = memNO 
	)aa ,Friend_Invitation bb
	where aa.Friend_Invit_No = bb.Friend_Invit_No 
    and aa.FriendInvit = freindNo ;
END $$
DELIMITER ;

-- *************************End Create Store Procedure***************************


-- =======================Start Create Trigger===================================
DELIMITER $$
CREATE TRIGGER `FAQ_AFTER_UPDATE` BEFORE UPDATE ON `FAQ` FOR EACH ROW
BEGIN
	SET NEW.`Update_Time` = (CURRENT_DATE);
END$$
DELIMITER ;

-- 修改itine-item紀錄時自動更新itine的Update_Time時間
DELIMITER |
CREATE TRIGGER tt_upd AFTER UPDATE ON itine_item
FOR EACH ROW
BEGIN
UPDATE itine
SET update_time= NOW()
WHERE itine_no = NEW.itine_no;
END|
DELIMITER ;
-- =======================End Create Trigger=====================================

-- insert data
-- 會員資料
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('1', 'KING5566', '123', 'KING', 'A123456789', '2021-01-01', '0912345678', 'XXX@YYY.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('2', 'BLAKE5566', '456', 'BLAKE', 'A123456789', '2020-11-24', '0933385044', 'SKC149@YYY.COM', 1);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('3', 'CLARK5566', '789', 'CLARK', 'A123456789', '2020-06-10', '0911231456', 'JF99WK8@YYY.COM',1);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('4', 'MICHELLE5566', '12wdftj3', 'MICHELLE', 'A123456789', '2021-01-29', '0988948772', 'WLS032849@YYY.COM', 2);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('5', 'ALLEN5566', '1hyej5ki3', 'ALLEN', 'A123456789', '2021-02-02', '0910294490', 'XDKD294907@YYY.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('6', 'TOM5566', 'krr49j3g', 'TOM', 'A123456789', '2005-11-11', '0910496491', 'HS64U290@AAC.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('7', 'BELLA5566', '4hjngjnoe5', 'BELLA', 'A123456789', '2021-02-02', '0910299368', 'UDKS0475@JDC.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('8', 'SINDY5566', '666gre7', 'SINDY', 'A123456789', '2021-02-02', '0988859304', 'ODJCY6485@HKDL.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('9', 'AMY5566', 'eryhoj45', 'AMY', 'A123456789', '2021-02-02', '0911298593', 'KDHS7777@JDHYI.COM', 0);
INSERT INTO `member` (`Member_No`, `Account`, `Password`, `Name`, `ID_Number`, `Birth_Date`, `Phone`, `Email`, `Member_State`) VALUES ('10', 'MAX5566', '4tehytj', 'MAX', 'A123456789', '2021-02-02', '0910258390', 'PETER1@QQQ.COM', 0);

-- 朋友列表
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('1', '3');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('1', '5');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('2', '1');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('2', '6');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('2', '8');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('3', '2');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('3', '4');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('4', '1');
INSERT INTO `Friend_List` (`Member_No`, `Friend_No`) VALUES ('4', '2');

-- 聊天訊息
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('1', '3', '安安,在嗎？', '2021-01-01 10:38:41', 1);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('3', '1', '嗯嗯', '2021-01-02 10:50:00', 1);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('1', '3', '明天要不要一起去看台北逛逛？', '2021-01-02 10:51:00', 1);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('3', '1', '先去洗澡囉', '2021-01-02 22:48:00', 1);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('1', '3', 'OK', '2021-01-02 23:00:41', 0);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('1', '3', '你洗好久...', '2021-01-03 15:24:34', 0);
INSERT INTO `Message` (`Speacker_No`, `Receiver_No`, `Content`, `Time`, `IsRead`) VALUES ('1', '3', 'hi...', '2021-01-05 11:13:49', 0);

-- 通知
INSERT INTO `Notify` (`Notify_Person`, `Notify_Content`, `Notify_Time`) VALUES ('1', '我不做人啦 要求加入好友', '2021-02-03 10:38:41');
INSERT INTO `Notify` (`Notify_Person`, `Notify_Content`, `Notify_Time`) VALUES ('1', '我不做人啦 邀請你加入共同編輯行程', '2021-02-03 14:00:00');
INSERT INTO `Notify` (`Notify_Person`, `Notify_Content`, `Notify_Time`) VALUES ('1', '有人報名了你的體驗', '2021-02-06 09:43:00');
INSERT INTO `Notify` (`Notify_Person`, `Notify_Content`, `Notify_Time`) VALUES ('2', '體驗成團通知', '2021-02-09 06:16:02');
INSERT INTO `Notify` (`Notify_Person`, `Notify_Content`, `Notify_Time`) VALUES ('3', '體驗成團通知', '2021-02-09 07:00:53');


---- 最新消息 News----
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('九族文化村是全台灣最大的賞櫻景點，擁有日本櫻花協會認證，唯一海外賞櫻名所，每逢櫻花祭5000多棵不同品種的『櫻花』相繼盛開。九族園內的花種豐富，我們特別推薦「九族百重櫻」，這是由山櫻花嫁接八重櫻而成的全新品種，命名為九族百重櫻。園區內如櫻花彩虹、觀山樓、櫻花湖、天理園等地皆有種植，不僅量大而且密集，視覺上非常具有震撼美。','2021-04-02','南投九族文化村櫻花季一日旅');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('陽明山舊稱草山，因紀念明朝學者王陽明而改名為陽明山，它是臺北的都市後花園，位居近郊，是大家很方便就可以遠離塵囂，休閒的好去處。由於它位居火山地帶，特殊的火山地形及地質構造，造就了這一區的溫泉景觀。陽明山溫泉大致可分為4個區域：陽明山國家公園周邊、冷水坑、馬槽及火庚子坪，由於地熱運動頻繁，這4個區域溫泉所含的礦物成分都不同，使得其水溫、泉質與療效也大不相同。陽明山位於大屯火山區，溫泉在陽明山風景區內，分前山溫泉和後山溫泉兩塊，有很多溫泉旅館，加上山上眾多風景區和美食，很適合大家前來一趟溫泉泡湯之旅。','2021-04-15','台北陽明山國家公園溫泉泡湯賞櫻');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('與日本動畫大師宮崎駿電影場景相似的「九份老街」，街道由「三橫一豎」等四條道路為主，被許多階梯圍繞，別具特色。孤懸在山丘上的九份山城，以能鳥瞰基隆嶼山海險峙，霧雨迷濛的美景而聞名。在九份老街可逛逛特色紀念品店，及品嚐在地美食，沿路上充滿懷舊的建築也讓人不禁駐足欣賞，若是想暫時遠離熙嚷的街道，不妨走入九份的茶屋，九份的茶屋各具特色，除了品茗之餘，包括陶瓷茶具的捏製巧思、來自臺灣產地的各式茶葉、茶的烹煮方式與茶道、茶屋的設計與生活美學、精緻茶點餐飲及四季晨晚各異的山城景緻，駐足在此泡上一壺好茶，享受悠閒的時光，欣賞專屬的山城美景。夜晚的九份，商家燈火鼎盛的美麗景象，別有一番風味，不妨在此留宿一晚，感受有別於白日的不夜風情。','2021-05-09','新北九份老街與黃金山城');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('愛河發源於高雄市仁武區，流經高雄市區，為高雄主要河川之一，猶如城市的母親之河。下游河岸整治而成河濱公園，是市區內散步休憩的好去處，也是端午節划龍舟與元宵節燈會的舞台。過去由於工業污染與都市廢水排放不當，使得愛河因污染嚴重而惡名昭彰，但近年來經政府努力整治，並陸續在河岸兩旁進行綠化，闢建休息設施，方使愛河的浪漫風華得以重現。愛河入夜以後沿著水面點點霓虹彩光，東西兩岸各具不同情調的「愛河曼波」與「黃金愛河」咖啡藝文廣場，散發迷人的咖啡香及快慢交替的美妙樂音，令人不得放慢腳步而融入其中，讓港灣城市的水岸休閒風情，為身心靈作徹底的放鬆。','2021-05-20','高雄愛河與駁二藝術特區');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('阿里山國家風景區豐富的自然奇觀、地形景觀、自然生態、人文資源及遊憩景點，早已成為臺灣最具代表性的風景，聲名遠播海內外，其獨特的美景迄今仍是熱門旅遊景點。臺灣好行─阿里山線行駛台18線省道(阿里山公路)，從海拔200公尺爬升至2500公尺，沿途豐富的天然地形景觀、多樣性的自然生態和人文資源，是臺灣最具代表性的旅遊線。 搭乘臺灣好行阿里山線不僅可以輕鬆旅行阿里山，更能在視覺感觀上得到滿足。','2021-06-08','嘉義阿里山奮起湖二日遊');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('1989年，柴山部分地區解除軍事管制，政府將柴山規劃為一處「自然公園」，特別規劃了許多綜橫交錯的步道，提供高雄市民及遊客健行及遊憩空間，目前是高雄市最熱門的登山健行步道，不論清晨或傍晚，來此登山健行的遊客絡繹不絕，而因為遊客不當餵養獼猴，雖然使得人與獼猴更接近，但也破壞了獼猴原有的生存型態，來到柴山您可以放鬆心情去體驗這裡的花草樹林及特殊的地形景觀，但請遊客務必遵守這裡的生態，不要任意的餵養獼猴，讓這裡的生態得以永續保存。','2021-06-21','高雄柴山秘境之旅半日遊');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('太魯閣國家公園座落於花蓮、臺中及南投三縣市，是臺灣第四座成立的國家公園，前身為日治時期成立之次高太魯閣國立公園（1937-1945）。其範圍以立霧溪峽谷、東西橫貫公路沿線及其外圍山區為主，包括合歡群峰、奇萊連峰、南湖中央尖山連峰、清水斷崖、立霧溪流域及三棧溪流域等，全區面積九萬二千公頃。境內河川以脊樑山脈為主要的分水嶺向東西奔流。東側是立霧溪流域，面積約佔整個國家公園的三分之二，主流貫穿公園中部，支流則由西方及北方來會；脊樑山脈西側狹長的區域是大甲溪上游的南湖溪、耳無溪、畢綠溪等等。太魯閣國家公園涵蓋了劇烈造山運動隆起形成的變質岩區，區內岩層走向大致成東北往西南向，園內高山突兀，峽谷深邃，奇景美不勝收，極具特色之處。','2021-07-18','花蓮太魯閣與清水斷崖一日遊');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('南投清境是旅人最愛玩樂旅行的賞景熱點，合歡山群位列台灣百岳，山景壯麗，是旅人挑戰爬山的人氣標地；而武嶺是公路海拔的最高點，每次玩合歡山，都一定要來武嶺上打個卡；合歡山主峰景緻迷人，四季展現不同的美，冬天雪景及春天銀河，每個畫面都令人震撼!玩清境當然必遊清境農場，走進古錐的綿羊城堡，還有萌羊陪你玩；近期開放的清境高空觀景步道，讓旅人能近距離的欣賞山巒和雲海，還有情侶約會和親子必訪的音樂城堡及清境小瑞士花園，絕對是快門按不完；午後再到清境山中廚房或紫屋餐廳用餐下午茶，南投清境一日遊，輕鬆行程一包搞定，來清境就要是感受大地的自然之美!','2021-08-16','南投清境農場綿羊秀一日遊');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('Xpark水族館是由台灣與日本橫濱八景島人氣水族館樂園耗時5年規劃打造，為台灣首座新都會型的水生公園，並創造出360度的沈浸式空間，顛覆傳統水族館樣貌。「Xpark」將生活在大地上萬物的生長環境，透過空間演出與科技的融合，真實的展現在這座新型的水生公園。就連氣溫、濕度、味道與聲音都是經過精細的演算，表演於所有場館展場中，不論是天花板還是地板，甚至是水槽中的投影影像，皆能讓遊客感受到360°的沈浸式空間。水族館，不再是單純透過壓克力玻璃觀賞生物，「Xpark」將用五感讓大家彷彿身歷其境。','2021-09-03','桃園Xpark 都會型水生公園半日遊');
INSERT INTO `News` (News_Content, Release_Date, News_Title) VALUES ('初鹿牧場位在台東縱谷裡的高海拔台地上，是台灣最大的坡地牧場，也是台東最老的大型觀光牧場。有大片草地讓孩子盡情跑跳、打滾，還可以滑草、搭小火車，餵食乳牛、驢子、袋鼠、山羊…好多動物，最後去農產品販售區買伴手禮，去咖啡廳喝杯鮮奶咖啡、吃支香濃鮮奶霜淇淋，是闔家休閒的好地方‧幅員遼闊的初鹿牧場，牧場內規劃有滾草區、餵食區、可愛動物區、森林浴區等，其中滾草區放置了數綑乾牧草，一綑一綑大型的乾牧草散置在翠綠的草地上，極具異國風情，讓人體驗到牧場滾草的樂趣。登上放牧區的觀景台，可看見成群的乳牛悠閒地在草原上漫步，形成一幅充滿牧場野趣的優美景致。','2021-09-28','台東初鹿牧場一日遊');

----  主打促銷 Promotion---- 
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('武陵農場位在雪霸國家公園裡，是台灣著名的高山農場，因為高海拔加上多樣的地理景觀，一年四季都很值得造訪！春季有紫藤花，夏季可以看繡球花，秋季則是楓紅、銀杏、落羽松，冬季則是有最粉嫩的梅花與櫻花。武陵農場距離梨山和福壽山農場、合歡山約一小時左右的車程，所以也有不少人攜家帶眷，將景點一次玩好、玩滿。','2021-04-01','2021-04-15','2021-03-08','1','3200','南投 | 武陵農場櫻花之旅一日遊(2人)');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('「薰衣草森林」是由兩個懷抱著紫色夢想的女孩子所創立，在台北外商銀行工作六年的詹慧君，與來自高雄的鋼琴老師林庭妃，兩人在接觸過來自西方的香藥草一段時間後，一直很希望擁有一畝薰衣草田，讓自我的身心在純樸的生活中得到安靜。於是，帶著全身的家當，兩人來到台中新社的中和村，這裡有很多樹、很多山，層巒疊嶂的蓊鬱山林穿插梅花、山櫻花、野薑花、檳榔花、桃花等四季花卉，山上散居的二十多戶人家就與螢火蟲、野兔、大冠鷲、竹雞快樂的生活著，兩人與園主王媽媽一家人除草、整地、挖土、排列步道、蓋房子，一磚一瓦親手打造出自己的夢想天地，旅客可以沿著小路緩慢上山，體驗簡單樸石的山居生活，認識、照顧香草植物，或是手作餅乾與香草料理，嘗試都市所沒有的全新體驗。','2021-03-01','2021-12-31','2021-03-10','2','1450','台中 | 薰衣草森林遊園一日券(雙人)');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('愛河是高雄景點知名的人氣地標，玩樂高雄，又怎麼能不來這踩點取景呢?白天的愛河景觀唯美，晚上點燈更是迷人，而且一定不能錯過搭乘高雄，愛河貢多拉船，坐在船上吹風遊河，用不同的角度欣賞河岸風光，還能聆聽船夫在貢多拉船上高歌演唱與解說，隨著柔美的旋律，能同時感受到浪漫與放鬆，愛河在地的特色體驗，非常值得旅人一遊，當然，愛河貢多拉船也是情侶約會的小基地。','2021-03-15','2021-05-16','2021-03-10','3','3000','高雄 | 浪漫愛河貢多拉乘船券(2人)');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('在這裡，沒有「哈利波特」的神奇魔法，也沒有「大亨小傳」的華麗堂皇，只有簡單的自然風貌，和自然精靈共生的小小住所；希望能帶給大家最真實的歡樂，以及難忘的回憶！立即預訂花蓮豪華露營地踏浪星辰，與七星潭、星空零距離接觸，忘記心中的煩惱，喜歡旅行和大自然的朋友們，等著一同探險，感受露營的美妙，立即從 KKday 預訂，與家人朋友共享美好時光，一起玩樂與烤肉，捕捉生命中的美好回憶，您只需簡單行李，即可享受免搭篷輕鬆入住，期盼您蒞臨七星潭人氣露營區，體驗我們為您準備的水滴型帳蓬及溫馨露營車。','2021-03-31','2021-05-31','2021-03-12','4','7500','花蓮露營｜踏浪星辰 Camp 露營券');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('隨著季節變化而有不同風情的縱谷，不僅有震撼人心的風景，還有許多有趣的活動，從花海、自然農遊、文化慶典到溫泉泡湯，都為它的美麗寫出一段精彩的詩篇，一年四季怎麼玩都玩不膩，不論何時都有最精彩的活動等著你前來體驗！ 花東縱谷國家風景區在1997年成立，是台灣東部的國家風景區之一。在花東縱谷中各向海岸山脈和中央山脈看去的第一條稜線為風景區的東西界，北界為花蓮溪和木瓜溪，南至卑南鄉，範圍內的九處都市計畫區和國立東華大學校地不屬於風景區管理範圍。由北到南可將此風景區分為「花蓮系統」、「玉里系統」和「臺東系統」三個遊憩重點。重要的景點有鯉魚潭、池南國家森林遊樂區、舞鶴石柱、玉里金針山、富里六十石山、紅葉溫泉、關山親水公園、鹿野高台、大陂池、利吉小黃山等。','2021-03-25','2021-12-31','2021-03-15','5','899','花蓮 | 花東縱谷＆鯉魚潭導覽一日遊');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('您真的有來過台北嗎?如果沒有的話，不訪參考我們為您規劃台北/新北 8 小時包車的行程之旅吧。我們將帶您深度體驗平溪、十分瀑布、北海岸、九份各個知名景點，由專業的導覽人員帶您一天走完台北近郊熱門景點，不用煩惱火車公車交通轉乘、浪費寶貴旅遊時間。重要的是可以讓您能夠不拘束於旅行團設計好的行程。此活動將由專業包車司機大哥，介紹當地景點、人文小吃。','2021-03-20','2021-04-20','2021-03-18','6','3200','新北｜北海岸野柳、平溪、九份經典一日遊');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('古坑鄉位於臺灣雲林縣東南端，地勢東高西低，東側以阿里山山脈為制高點，西側則為以石牛溪為主的沖積平原及嘉南平原，北接斗六市，東北臨南投縣竹山鎮，東毗嘉義縣阿里山鄉，南鄰嘉義縣梅山鄉，西與西南毗斗南鎮、嘉義縣大林鎮，為雲林縣面積最大、人口密度最低的鄉鎮，亦為雲林縣唯一一個面積逾100平方公里的行政區。著名的劍湖山世界坐落於此，以全國數一數二多的落雷及生產古坑咖啡而聞名。','2021-03-25','2021-05-26','2021-03-20','7','899','雲林古坑 | 草嶺秘境雲嶺之丘 & 台版小嵐山一日遊');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('苗栗不僅是桐花的故鄉，熱鬧的南庄鄉也有許多趣味好玩景點，像是超多客家美食的南庄老街，還有近年超人氣的景觀餐廳，蘇維拉莊園及雲水渡假森林，都是假期適合走遊的好地方；若您喜歡森林系的花園餐廳，相遇森林屋、碧落角、橄覽樹等風景，非常推薦您來苗栗南庄走一趟。','2021-03-25','2021-07-31','2021-03-22','8','3200','苗栗南庄｜山嵐秘境蘇維拉莊園一日遊');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('現在預約台北千島湖一日遊，一天之內走遍必去石碇千島湖、八卦茶園、石碇老街！專業中英文導覽，帶你從台北出發，深入台北秘境石碇千島湖的醉美景色。北部網美秘境，帶領你直擊台北近郊的秘境-石碇千島湖，參加我們達人所推薦的當地一日遊最方便，還在猶豫什麼，快來一探究竟千島湖的夢幻美麗景緻吧！','2021-03-26','2021-06-30','2021-03-24','9','3200','台北石碇 | 千島湖、八卦茶園&老街一日遊');
INSERT INTO `Promotion` (Promot_Content, Promot_Start, Promot_End, Release_Date, Promot_Product_No, Promot_Product_Price, Promot_Product_Title) VALUES ('芒芒人海遇見你｜遊台必訪的台北後花園－陽明山，包車直達接送，不需辛苦排隊轉車，擎天岡遼闊的草原景觀，連綿的綠茵上牛隻漫步吃草，形成台北難得一見的牧野風光。每年秋冬之際，大屯山芒草盛開，一整片的芒草隨風搖曳，讓人心情舒爽並享受當下的悠閒時光。陽明山擎天岡踏青野餐，小牛就在你身邊，欣賞台北市最高點夜景，從大屯山俯瞰台北夜景，秋冬季還能賞芒草，冷水坑溫泉泡腳放鬆，緩解平日生活的壓力。','2021-03-28','2021-08-31','2021-03-26','10','4800','台北陽明山｜擎天岡草原＆大屯山賞芒草＆冷水坑｜包車一日遊');

-- 常見問題 --
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('行程交通延誤怎麼辦?','若行程當天，因受到天災如颱風或大雪等人力不可抗拒之因素影響，致使班機延誤起飛或其他交通停駛延誤，無法如預期時間前往行程時，請與店家聯絡。','2021-01-12');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('在哪些情有可原的狀況下，旅客可獲得退款？','我們明白，在發生某些情況時，旅客的旅行計劃會受到影響。店家可依據情況，授權不受取消規定限制而給旅客退款。旅客可能會獲得全額退款，也代表店家將不會收到任何帳款。','2021-01-18');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('如果旅遊行程出發當天，我不小心遲到了該怎麼辦?','很抱歉，為維護行程品質以及每位旅客體驗之權益，我們不接受出發集合時間以外的旅客中途加入旅遊行程，因此請務必準時抵達集合地點。此外，若當天無故缺席，我們無法退還團費給您。因此，若是當天因為突發狀況而無法準時集合的旅客，請在出發集合時間前務必聯絡店家。','2021-01-22');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('已經前往目的地了，卻聯絡不到店家怎麼辦？','如果您已經抵達目的地，但聯繫不上店家：請聯繫站內客服(0800-123-321)，客服人員將會協助您聯繫店家。','2021-01-28');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('若行程因新型冠狀病毒(COVID-19)而取消該行程會退費嗎?','若因疫情導致行程取消，該行程費用會無償退費。目前依照交通部觀光局，針對旅遊的旅客和旅行社間，所訂定的「退費原則」，是依照衛福部與消費者保護保護法之規則訂定，因此消費者無須擔心。','2021-01-31');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('如果有人請我從Guide me網站以外付款，我該怎麼做？','如果你在Guide me網站站外支付了預訂款項（例如透過匯款或銀行轉帳），即有可能成為詐騙的受害者。若要尋求協助，請立即通知我們。','2021-02-05');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('接受哪些付款方式？','我們目前只支援信用卡付款方式，主要取決於你付款帳號的所在國家／地區。除了各大信用卡和簽帳卡外，部分國家和地區也提供了其他付款選項。','2021-02-08');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('如果遇到政府頒布的旅遊限制和警示，請問我該怎麼辦?','若因疫情導致政府頒布旅遊限制和警示，該行程費用會無償退費。目前依照交通部觀光局，針對旅遊的旅客和旅行社間，所訂定的「退費原則」，是依照衛福部與消費者保護保護法之規則訂定，因此消費者無須擔心。','2021-02-14');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('如果遇到天災與不可抗力之因素，該怎麼辦?','若因天災或不可抗力之因素導致政府頒布旅遊限制和警示，該行程費用會無償退費。目前依照交通部觀光局，針對旅遊的旅客和旅行社間，所訂定的「退費原則」，是依照衛福部與消費者保護保護法之規則訂定，因此消費者無須擔心。','2021-02-18');
INSERT INTO `Faq` (Question, Answer, Update_Time) VALUES ('在貴網站遇到詐騙怎麼辦?','請先撥打客服電話，告知客服人員被詐騙細節，本公司會依法協助處理相關事宜。','2021-02-25');

-- 員工 --
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('qaz123456', 'k12345678','早餐店阿姨', '0927418841', '2012-03-04', 1) ;
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('wsxqaz830472', 'k12345678', '清水優心', '0927418841', '2012-03-04', 1);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('edcwsxqaz0800', 'abc987', '高橋信二', 0989053662, '2015-11-15', 1);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('mkdij456', 'ppop873', '達比修有', 0934271260, '2015-08-23', 1);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('aefe341', 'aweda1901923', '乾真大', 0987062906, '2015-09-28', 1);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('yhthty785', 'weawe0911',  '松本剛', 0982192913, '2015-09-30', 0);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('ukyuk0903', 'zdoijwae892',  '今井順之助', 0986525867, '2017-12-01', 0);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('trhdtr543', 'aefoiaj1111', '近藤健介', 0958739955, '2018-06-21', 0);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('rtdtrh888', 'tubame1290', '今川優馬', 0988301913, '2020-02-09', 1);
INSERT INTO `Employee` (Account, Password, Name, Phone, Start_From, Emp_State) VALUES ('oprkogaerg0663', 'apoepo8973', '上原健太', 0932786376, '2021-02-10', 1);

-- Function 表格
insert into `function` (Funct_No, Funct_Name) values (1, '員工帳戶管理');
insert into `function` (Funct_No, Funct_Name) values (2, '會員資料管理');
insert into `function` (Funct_No, Funct_Name) values (3, '前台首頁管理');
insert into `function` (Funct_No, Funct_Name) values (4, '商城相關管理');
insert into `function` (Funct_No, Funct_Name) values (5, '體驗相關管理');
insert into `function` (Funct_No, Funct_Name) values (6, '景點相關管理');
insert into `function` (Funct_No, Funct_Name) values (7, '討論區相關管理');
insert into `function` (Funct_No, Funct_Name) values (8, '線上客服回覆');

-- 員工權限 --
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (1,1) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (1,2) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (1,3) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (1,4) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (1,5) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (2,1) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (3,1) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (4,1) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (5,1) ;
INSERT INTO `Emplo_Permi` (Emplo_No, Funct_No) VALUES (5,2) ;

-- 客服訊息
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('1', '1', 'Hi~', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('1', '1', 'Hi, 有什麼需要為您服務的嗎?', 1);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('1', '1', '我電腦壞了...', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('1', '1', '...', 1);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', '哈囉', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', 'Hi, 有什麼需要為您服務的嗎?', 1);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', '我購買的票券尚未收到耶', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', '怎麼辦?', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', '麻煩給我訂單編號,我查詢一下', 1);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', 'XXXXXX', 0);
INSERT INTO `service_message` (`Member_No`, `Emplo_No`, `Content`, `Direction`) VALUES ('2', '1', '這在為您查詢請稍等', 1);

-- Attraction 表格
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','中正公園','好玩','中壢',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','大安公園','好好玩','台北',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','中山公園','好好好玩','台中',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','陽明山公園','好玩','台北',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','墾丁公園','好玩好玩','屏東',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','玉山','好美','南投',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('餐廳','當歸鴨','好吃','中壢',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('餐廳','我家公園','好散步','台中',0,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('景點','八仙樂園','玩水好玩','新北',2,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('住宿','宗軒家','免費住','中壢',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','高鐵','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','火車','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','捷運','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','計程車','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','飛機','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','走路','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','機車','','',1,"");
insert into `Attraction` (Sort, Attra_Name, Descr, Location, Is_On_Shelf,Attra_Pic1) values ('交通','汽車','','',1,"");


-- Itine 表格
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中五日",1,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台南五日",2,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台五日",1,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台dsf中五日",1,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中adf五日",1,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中afdsfds五日",2,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中a五日",3,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中dfg五日",3,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中dfdf五日",2,1); 
insert into `Itine` (itine_Name, Builder, Itine_Status) values("台中ggd五日",2,1); 

-- Itine_Item 表格
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(1,1,'2021-03-19 14:00:00', '2021-03-19 15:00:00',1,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(1,2,'2021-03-19 15:00:00', '2021-03-19 16:00:00',2,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(1,3,'2021-03-19 16:00:00', '2021-03-19 17:00:00',3,1); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(1,4,'2021-03-20 14:00:00', '2021-03-19 15:00:00',3,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(2,1,'2021-03-16 14:00:00', '2021-03-19 15:00:00',1,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(2,2,'2021-03-17 14:00:00', '2021-03-19 15:00:00',1,1); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(2,3,'2021-03-18 14:00:00', '2021-03-19 15:00:00',1,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(4,1,'2021-03-15 14:00:00', '2021-03-19 15:00:00',3,1); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(4,2,'2021-03-16 14:00:00', '2021-03-19 15:00:00',3,0); 
insert into Itine_Item (Itine_No, Attra_No, Start_Time, End_Time, Manager, IsDone) values(4,3,'2021-03-17 14:00:00', '2021-03-19 15:00:00',3,0); 

-- Itine_Member 表格
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(1,2,0);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(1,3,1);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(1,4,2);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(1,5,3);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(2,1,1);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(2,3,2);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(2,4,3);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(2,5,0);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(3,3,2);
insert into Itine_Member (Itine_No, Member_No, IsEditable) values(3,2,1);

-- Exper_Type 體驗種類表格
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(1,'烹飪體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(2,'動物體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(3,'靈修體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(4,'運動體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(5,'咖啡茶道體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(6,'賞花體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(7,'戶外露營體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(8,'音樂體驗');
INSERT INTO Exper_Type (Exper_Type_No, Exper_Type_Name)
values(9,'線上體驗');



-- Experience 體驗表格
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(5,1,1,'和咖啡冠軍一起沖煮咖啡',1000,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(1,1,1,'跟義大利奶奶學做「手工義大利麵」',800,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(2,2,1,'和小山羊的約會',800,0);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(3,2,1,'與日本僧侶一同冥想',1060,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(6,3,1,'京都人帶您一起賞楓',600,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(5,3,2,'京都茶道體驗',2500,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(4,4,2,'體驗日本鈴鹿F1賽道',3000,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(1,4,2,'跟著達人體驗雪營野炊',2000,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(4,5,2,'跟著滑雪教練一起衝樹林',5000,1);
INSERT INTO Experience (Exper_Type_No, Host_No, Checker_No, Name, Price, Exper_Status)
values(1,5,2,'跟著王剛一起做川菜',20000,1);


-- Exper_Order 體驗梯次表格
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit,Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(1,'2021-02-10 10:00:00','2021-02-17 10:00:00','2021-02-18 10:00:00','2021-02-18 12:00:00',99, 1, 5000, 2, 5);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(2,'2021-02-20 10:00:00','2021-02-28 10:00:00','2021-03-01 10:00:00','2021-03-01 15:00:00',99, 1, 5000, 2, 10);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(2,'2021-03-01 10:00:00','2021-03-09 10:00:00','2021-03-10 10:00:00','2021-03-10 12:00:00',99, 1, 5000, 0, 20);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(2,'2021-03-10 10:00:00','2021-03-17 10:00:00','2021-03-18 10:00:00','2021-03-17 13:00:00',99, 1, 5000, 0, 30);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(3,'2021-03-01 10:00:00','2021-03-10 10:00:00','2021-03-11 10:00:00','2021-03-11 16:00:00',99, 1, 5000, 0, 24);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(3,'2021-03-15 10:00:00','2021-03-16 10:00:00','2021-03-17 10:00:00','2021-03-17 16:00:00',99, 1, 5000, 0, 32);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit,Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(4,'2021-03-10 10:00:00','2021-03-17 10:00:00','2021-03-18 10:00:00','2021-03-18 16:00:00',99, 1, 5000, 0, 33);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(5,'2021-03-02 10:00:00','2021-03-11 10:00:00','2021-03-12 10:00:00','2021-03-12 16:00:00',99, 1, 5000, 0, 6);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(5,'2021-03-13 10:00:00','2021-03-15 10:00:00','2021-03-16 10:00:00','2021-03-16 16:00:00',99, 1, 5000, 1, 15);
INSERT INTO Exper_Order (Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum)
values(5,'2021-03-17 10:00:00','2021-03-25 10:00:00','2021-03-26 10:00:00','2021-03-26 16:00:00',99, 1, 5000, 0, 17);

-- Exper_Application 體驗報名單(0:確認出發 1:取消報名)
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(1,1,1,2000,1,2);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(2,1,2,4000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(3,1,1,20000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(3,2,2,2000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(4,1,1,2500,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(5,1,1,2000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(5,3,3,6000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(5,1,1,20000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(5,2,2,40000,0,1);
INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status)
values(5,1,1,1060,1,3);

-- Favorite_Exper 收藏體驗表格
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(1,1);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(2,2);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(1,3);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(1,4);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(3,1);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(3,2);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(3,3);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(4,1);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(5,1);
INSERT INTO Favorite_Exper (Member_No,Exper_No)
values(5,3);

-- Exper_Report 檢舉體驗表格
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(1,2,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-19 15:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(1,3,'體驗達人長太醜',CURRENT_TIMESTAMP,'檢舉未成案','2021-03-18 10:00:00',2);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(1,4,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-19 16:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(2,2,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-18 11:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(3,2,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-19 12:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(3,3,'體驗內容未符合資料',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-20 12:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(4,2,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-20 13:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(4,4,'體驗達人遲到',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-19 15:30:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(4,5,'達人未退款',CURRENT_TIMESTAMP,'檢舉已處理','2021-03-19 15:00:00',1);
INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_Time, Is_Checked)
values(5,6,'體驗行程無聊',CURRENT_TIMESTAMP,'檢舉未成案','2021-03-19 15:00:00',2);

-- Exper_Rate 評價體驗表格
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(1,1,5,'超棒的，體驗達人非常專業，體驗到當地在地生活');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(1,2,1,'體驗達人遲到，給一顆星都嫌多');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(2,1,5,'超讚的，給你拍拍手');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(2,2,1,'體驗達人遲到，感受非常差');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(2,3,3,'還行，差強人意');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(3,2,1,'體驗達人遲到，怎麼不能給零顆星？？？');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(3,1,5,'超棒的，體驗達人非常專業');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(4,1,5,'超讚，還會再去');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(5,1,5,'超棒的，給最高評價五顆星');
-- INSERT INTO Exper_Rate (Member_No, Exper_No, Rate, Rate_Content)
-- values(5,2,1,'達人遲到，不敬業');

-- product --
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("桃園城市航空館自由行",1000,
"為來往世界各地的過境旅客、商務人士及旅遊團體，提供一個現代化與舒適的住宿環境",10,3,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values(
"雪霸國家公園‧大鹿林道‧觀霧豐富行",
4000,
"雪霸休閒農場緊臨檜木、柳杉、巨木林，景緻瑰麗的國家公園，資源極為豐富",
20,4,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("台東花園飯店2天",2500,
"出海賞鯨、探離島，甚至泡湯、踏青、品嚐海鮮野菜等等，都能讓您輕鬆自在",
15,3,1);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("武陵農場浪漫櫻花季",1500,
"種類繁多的植物相，山水間綠意盎然",10,3,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("水頭聚落‧金門悠閒走三日",2500,
"保存的閩式建築與洋樓，是全島最多、最精美。得月樓、金水國小、僑鄉文化展示館等建築，都別具特色金門",
14,4,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("明池森林,‧馬告生態探索二日",
2500,"近百棵千年以上的紅檜與台灣扁柏矗立園區，令人讚嘆",30,4,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("宿霧+台灣虎航5日",28000,
"生態豐富還有各式各樣的保育區",25,5,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("泰國清邁5日遊",15000,
"國家公園各式景觀晚上還有泰式按摩",20,5,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("菲律賓長灘島5日遊",16000,
"曾被BMW的旅遊雜誌評為最美沙灘之一",25,4,0);
insert into `product`(product_name,list_price,descr,
total_rate_count,total_rate,product_status)values("南越5日遊",6000,
"有下龍灣奇景以及美不勝收的畫面",31,3,0);

-- product rate --
insert into product_rate(member_no,product_no,rate,comment)
values(2,2,3,"great");
insert into product_rate(member_no,product_no,rate,comment)
values(3,3,3,"norml");
insert into product_rate(member_no,product_no,rate,comment)
values(4,4,2,"soso");
insert into product_rate(member_no,product_no,rate,comment)
values(5,5,3,"boring");
insert into product_rate(member_no,product_no,rate,comment)
values(1,6,5,"good place");
insert into product_rate(member_no,product_no,rate,comment)
values(2,7,5,"comfortable");
insert into product_rate(member_no,product_no,rate,comment)
values(3,8,2,"nice place");
insert into product_rate(member_no,product_no,rate,comment)
values(4,9,4,"nice service");
insert into product_rate(member_no,product_no,rate,comment)
values(5,10,4,"good");
insert into product_rate(member_no,product_no,rate,comment)
values(1,1,5,"great");

-- order --
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-12-11',10000,'2wsx3edc124566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(3,'2020-12-10',12000,'2wefqcwzdbd4566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(4,'2020-1-11',12500,'2wsbgdchef566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(5,'2020-2-11',15000,'2wshtjryrr566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(1,'2020-4-11',25000,'2wsxredgsgs6');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-7-1',25000,'2wfew345456ef6');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-8-11',280000,'1wsx3edc124566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-9-1',150000,'2wsx3e4c1fefd24566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-12-1',160000,'2ws245424566');
insert into `order`(member_no,order_date,sum,credit_card_No)
values(2,'2020-11-11',60000,'214retgwee566');

-- order item --
insert into order_item(order_no,product_no,product_count,selling_price)
values(2,2,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(3,3,3,12000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(4,4,5,12500);
insert into order_item(order_no,product_no,product_count,selling_price)
values(5,5,10,15000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(6,6,10,25000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(7,7,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(8,8,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(9,9,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(1,9,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(1,10,10,10000);
insert into order_item(order_no,product_no,product_count,selling_price)
values(2,1,10,10000);

-- favorite product --
insert into favorite_product(member_no,favorite_product_no)
values(2,2);
insert into favorite_product(member_no,favorite_product_no)
values(3,3);
insert into favorite_product(member_no,favorite_product_no)
values(4,4);
insert into favorite_product(member_no,favorite_product_no)
values(5,5);
insert into favorite_product(member_no,favorite_product_no)
values(1,6);
insert into favorite_product(member_no,favorite_product_no)
values(2,7);
insert into favorite_product(member_no,favorite_product_no)
values(3,8);
insert into favorite_product(member_no,favorite_product_no)
values(4,9);
insert into favorite_product(member_no,favorite_product_no)
values(1,10);
insert into favorite_product(member_no,favorite_product_no)
values(1,1);

-- 信用卡
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('350568504285232', '1', 'AE', '2000-12-05');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('4079143705285696', '2', 'AE', '2001-01-03');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('4585590225523278', '3', 'CC', '2008-11-30');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('4485805778747806', '4', '爸爸的卡', '2005-04-06');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('5317333209428541', '5', '乾爹', '2020-01-04');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('5505628207587681', '1', '刷這張就對了', '1999-12-29');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('371766457805817', '3', '', '2025-07-26');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('363137872094306', '6', '', '2023-06-26');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('4168120209557822', '7', 'GG', '2024-03-05');
INSERT INTO `guideme`.`Credit_Card` (`Credit_Card_No`, `Member_No`, `Credit_Card_Name`, `Exp_Date`) VALUES ('5376416578946017', '8', '嘿嘿', '2015-11-23');

