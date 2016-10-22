package restore.sql;

import restore.sql.hibernate.BasicFormatterImpl;
import restore.sql.hibernate.Formatter;
import restore.sql.hibernate.StringHelper;

/**
 * restore the mybatis generate sql to original whole sql
 * @author ob
 */
public class RestoreSqlUtil {
    public static String restoreSql(final String preparing, final String parameters) {
        String restoreSql = "";
        String preparingSql = "";
        String parametersSql = "";
        try {
            if(preparing.contains("Preparing:")) {
                preparingSql = preparing.split("Preparing:")[1].trim();
            } else {
                preparingSql = preparing;
            }
            boolean hasParam = false;
            if(parameters.contains("Parameters:")) {
                if(parameters.split("Parameters:").length > 1) {
                    parametersSql = parameters.split("Parameters:")[1].trim();
                    if(StringHelper.isNotEmpty(parametersSql)) {
                        hasParam = true;
                    }
                }
            } else {
                parametersSql = parameters;
            }
            if(hasParam) {
                String[] sqlArray = preparingSql.split("\\?");
                String[] paramArray = parametersSql.split(",");
                for(int i=0; i<paramArray.length; ++i) {
                    if (paramArray[i].contains("(String)") || paramArray[i].contains("(Timestamp)") || paramArray[i].contains("(Date)")) {
                        int pos = paramArray[i].lastIndexOf("(");
                        restoreSql += sqlArray[i] + "'" + paramArray[i].substring(0, pos).trim() + "'";
                    } else if (paramArray[i].lastIndexOf("(Integer)") != -1) {
                        int pos = paramArray[i].lastIndexOf("(");
                        restoreSql += sqlArray[i] + paramArray[i].substring(0, pos).trim();
                    }
                }
                if (sqlArray.length > paramArray.length) {
                    restoreSql += sqlArray[sqlArray.length - 1];
                }
            } else {
                restoreSql =  preparingSql;
            }
            restoreSql = simpleFormat(restoreSql);
        } catch (Exception e) {
            return "";
        }
        return restoreSql;
    }

    public static String simpleFormat(String sql) {
        if(StringHelper.isNotEmpty(sql)) {
            return sql.replaceAll("(?i)from", "\nFROM")
                    .replaceAll("(?i)where", "\nWHERE")
                    .replaceAll("(?i)left join", "\nLEFT JOIN")
                    .replaceAll("(?i)right join", "\nRIGHT JOIN")
                    .replaceAll("(?i)limit", "\nLIMIT")
                    .replace(" ON", "\n ON").replace(" on", "\n on")
                    .replace(" UNION", "\n UNION\n").replace(" union", "\n union\n");
        }
        return "";
    }

    public static void main(String[] args) {
        String sql = "2016-10-21 12:00:01.937 DEBUG c.o.m.v.b.e.Mapper.selectByCondition -  -  - ==>  Preparing: select * from table where id = ?";
        String param = "2016-10-21 12:00:01.937 DEBUG c.o.m.v.b.e.Mapper.selectByCondition -  -  - ==> Parameters:  3(Integer)";
        String restoreSql = restoreSql(sql, param);
        Formatter formatter = new BasicFormatterImpl();
        String result = formatter.format(restoreSql);
        System.out.println(restoreSql);
        System.out.println("----------------------");
        System.out.println(result);
    }
}