$(document).ready(function() {
//	$("#dataTable").DataTable({
//		lengthMenu : [[3, 25, 50, -1], [3, 25, 50, "All"]], 
//		language : {
//			"lengthMenu" : "顯示 _MENU_ 筆資料",
//			"sProcessing" : "處理中...",
//			"sZeroRecords" : "没有匹配结果",
//			"sInfo" : "目前有 _MAX_ 筆資料",
//			"sInfoEmpty" : "目前共有 0 筆紀錄",
//			"sInfoFiltered" : " ",
//			"sInfoPostFix" : "",
//			"sSearch" : "尋找:",
//			"sUrl" : "",
//			"sEmptyTable" : "尚未有資料紀錄存在",
//			"sLoadingRecords" : "載入資料中...",
//			"sInfoThousands" : ",",
//			"oPaginate" : {
//				"sFirst" : "首頁",
//				"sPrevious" : "上一頁",
//				"sNext" : "下一頁",
//				"sLast" : "末頁"
//			},
//			"order" : [ [ 0, "desc" ] ],
//			"oAria" : {
//				"sSortAscending" : ": 以升序排列此列",
//				"sSortDescending" : ": 以降序排列此列"
//			}
//		},
//
//	});
	
	$("#dataTableAllAttraction").DataTable({
		"lengthMenu" : [[3, 10, 50, -1], [3, 10, 50, "All"]], 
		language : {
			"lengthMenu" : "顯示 _MENU_ 筆資料",
			"sProcessing" : "處理中...",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "目前有 _MAX_ 筆資料",
			"sInfoEmpty" : "目前共有 0 筆紀錄",
			"sInfoFiltered" : " ",
			"sInfoPostFix" : "",
			"sSearch" : "尋找:",
			"sUrl" : "",
			"sEmptyTable" : "尚未有資料紀錄存在",
			"sLoadingRecords" : "載入資料中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首頁",
				"sPrevious" : "上一頁",
				"sNext" : "下一頁",
				"sLast" : "末頁"
			},
			"order" : [ [ 0, "desc" ] ],
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		},
		searching:false,
		paging:false,
		info:false,
		ordering:false,
		fixedHeader: true,

	});
});