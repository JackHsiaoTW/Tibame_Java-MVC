package util;

import java.util.*;

public class jdbcUtil_CompositeQuery_Exper_Order {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("word".equals(columnName)) {
			// 用於varchar

			aCondition = "NAME" + " like '%" + value + "%' " + "or Exper_Descr" + " like '%" + value + "%' "
					 + "or Exper_Type_Name" + " like '%"+ value + "%' ";
		} else if ("datefilter1".equals(columnName)) {

			aCondition = " Exper_Order_Start <" + " str_to_date('" + value + " 23:59:59', '%Y-%m-%d %H:%i:%s')"
					+"and Exper_Order_Start>str_to_date('"+value+" 00:00:00', '%Y-%m-%d %H:%i:%s')";
//			"to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		}else if("price".equals(columnName)) {
			aCondition= "Exper_Now_Price <"+value;
		}
		return aCondition;
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}
}
