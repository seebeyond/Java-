  package smart.app.common;

  import java.sql.*;
  import smart.css.database.*;
  import java.util.*;

  public class DropList {
  private int pageCount;
  private int count;
  public DropList() {
        }


 /**
     * 得到用户auto_id,title的列表
     * @param deep 型号的类别
     * @return String[][]
     */
    public String[][] getModelDeepList(long deep,String tableName) {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from "+tableName+" where deep=" + deep +
            " order by sequence";
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }

/**
     * 得到用户auto_id,title的列表
     * @param deep 型号的类别
     * @return String[][]
     */
    public String[][] getChildModelList(long parentID,String tableName) {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from "+tableName+" where parent_id=" + parentID +
            " order by sequence";
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }
/**
     * 得到型号树下拉框
     * @sql form指下拉框所在form,thisDeepID本下拉框的name,nextDeepID下个下拉框的name
     */
      public String getDropList(String form,String tableName,String thisDeepID,String nextDeepID,long deep,long sum){
         String result="";
          ResultSet rs=null;
          int i=0;
          int j=0;
      String[][] thisDeep = this.getModelDeepList(deep,tableName); //取得部门级部门id列表
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//用户id列表
          try{
            smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

              try{
          if(deep<sum){
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getChildModelList(Long.parseLong(thisDeep[r][0]),tableName);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";

                  result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text="+"\"请选择\""+";";
                    result += "		oOption.value="+"\"0\""+";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";

                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }
                if(sum>2){
                if (deep <= sum-2) {
                  result += nextDeepID + "_onchange();";
                }
                }
                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                result += "\n" + "<option value=0>请选择</option>";
                if (deep == 1) {
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                        thisDeep[i][1] + "</option>";
                  }
                }
                result = result + "</select>";
              }
            if(deep==sum){
            result += "<select name=" + thisDeepID + " id=" + thisDeepID + " >";
            result += "\n" + "<option value=0>请选择</option>";

            result = result + "</select>";
            }
              }
          catch (Exception e) {

            }
          finally{
          dbo.close();
                  }
            }
            catch (Exception e) {

            }
            return result;
          }
/**
     * 得到型号树下拉框
     * @sql form指下拉框所在form,thisDeepID本下拉框的name,nextDeepID下个下拉框的name
     */
      public String getRootDropList(String form,String tableName,String thisDeepID,String nextDeepID,long deep,long sum){
         String result="";
          ResultSet rs=null;
          int i=0;
          int j=0;
      String[][] thisDeep = this.getModelDeepList(deep,tableName); //取得部门级部门id列表
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//用户id列表
          try{
            smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

              try{
          if(deep<sum-1){
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getChildModelList(Long.parseLong(thisDeep[r][0]),tableName);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";

                  result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text="+"\"请选择\""+";";
                    result += "		oOption.value="+"\"0\""+";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";

                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }
                if(sum>2){
                if (deep <= sum-3) {
                  result += nextDeepID + "_onchange();";
                }
                }
                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                result += "\n" + "<option value=0>请选择</option>";
                if (deep == 0) {
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                        thisDeep[i][1] + "</option>";
                  }
                }
                result = result + "</select>";
              }
            if(deep==sum-1){
            result += "<select name=" + thisDeepID + " id=" + thisDeepID + " >";
            result += "\n" + "<option value=0>请选择</option>";

            result = result + "</select>";
            }
              }
          catch (Exception e) {

            }
          finally{
          dbo.close();
                  }
            }
            catch (Exception e) {

            }
            return result;
          }


      public String[][] getContentTypeList() {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from d_p_contenttype";
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }
  public String[][] getWorkContentList(String flag) {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from d_p_workcontent where flag="+flag;
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }
 public String[][] getWorkContentList() {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from d_p_workcontent";
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }
     public String[][] getWorkResultList(String parentID) {
      String[][] list = null;

      ResultSet rs = null;
      String sql = "";
      int count = 0;
      int i = 0;
      smart.css.database.DBOperator dbe = new smart.css.database.DBOperator();
        sql = "select auto_id,title from d_p_result where content_id="+parentID;
      try {
        //得出数组的长度
        count = dbe.getCountValue(sql);
        list = new String[count][2];
        rs = dbe.executeQuery(sql);
        while (rs.next()) {
          list[i][0] = rs.getString("auto_id");
          list[i][1] = rs.getString("title");
          i++;
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      finally {
        //注意所有的关闭必须在finally中执行
        try {
          dbe.close(rs);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return list;
    }
      public String getWorkContentDrop(String form,String thisDeepID,String nextDeepID ,long flag){
         String result="";
          ResultSet rs=null;
          int i=0;
      String[][] thisDeep = null;
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//用户id列表
          try{
            smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();

              try{
                if(flag==1){
                thisDeep = this.getContentTypeList();
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getWorkContentList(thisDeep[r][0]);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";


                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }
                  result += nextDeepID + "_onchange();";
                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                        thisDeep[i][1] + "</option>";
                  }
                result = result + "</select>";
              }
              if(flag==2){
                thisDeep = this.getWorkContentList();
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getWorkResultList(thisDeep[r][0]);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";


                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }

                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                  thisDeep = this.getWorkContentList("1");
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                        thisDeep[i][1] + "</option>";
                  }
                result = result + "</select>";
              }
            if(flag==3){
            result += "<select name=" + thisDeepID + " id=" + thisDeepID + " >";
            thisDeep = this.getWorkResultList("1");
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                        thisDeep[i][1] + "</option>";
                  }
            result = result + "</select>";

            }
              }
          catch (Exception e) {

            }
          finally{
          dbo.close();
                  }
            }
            catch (Exception e) {

            }
            return result;
          }
public String getDefWorkContentDrop(String form,String thisDeepID,String nextDeepID ,long flag,String content){
         String result="";
          ResultSet rs=null;
          int i=0;
          String cType = "";

      String[][] thisDeep = null;
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//用户id列表
          try{
            smart.css.database.DBOperator dbo = new smart.css.database.DBOperator();
        if(content!=null&&!content.equals("")){
          String sqls = "select flag from d_p_workcontent where auto_id ="+content;
          cType = dbo.getSingleValue(sqls).toString();
        }
              try{
                if(flag==1){
                thisDeep = this.getContentTypeList();
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getWorkContentList(thisDeep[r][0]);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";


                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }
                  result += nextDeepID + "_onchange();";
                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                  for (i = 0; i < thisDeep.length; i++) {
                    if(thisDeep[i][0].equals(cType.trim())){
                    result += "\n" + "<option value=" + thisDeep[i][0] + " selected>" +
                    thisDeep[i][1] + "</option>";
                    }
                    else{
                    result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                    thisDeep[i][1] + "</option>";
                    }
                  }
                result = result + "</select>";
              }
              if(flag==2){
                thisDeep = this.getWorkContentList();
                result += "<script language=javascript>";
                result += "function " + thisDeepID + "_onchange()";
                result += "{";
                result += "	var oOption;var length;var side;var i;var num;";
                result += "	num=document." + form + "." + thisDeepID +
                    ".selectedIndex;";
                result += "	side=document." + form + "." + thisDeepID +
                    ".options[num].value;"; //得到选中的部门的id号

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //取出所有的部门的id
                  //用所有的部门id和所选中的id做比较，如果相等则取出所有的该部门的用户id
                  result += "if (side==" + "\"" + thisDeep[r][0] + "\"" + ")";
                  result += "{";

                  nextDeep = this.getWorkResultList(thisDeep[r][0]);
                  result += "		for(var x=document." + form + "." + nextDeepID +
                      ".length-1;x>=0;x--){";
                  result += "			document." + form + "." + nextDeepID +
                      ".options[x] = null;";
                  result += "		}";


                  for (int u = 0; u < nextDeep.length; u++) {
                    result += "		oOption=document.createElement(" + "\"" + "OPTION" +
                        "\"" + ");";
                    result += "		oOption.text=" + "\"" + nextDeep[u][1] + "\"" + ";";
                    result += "		oOption.value=" + "\"" + nextDeep[u][0] + "\"" + ";";
                    result += "		document." + form + "." + nextDeepID +
                        ".add(oOption);";
                  }
                  if(nextDeep.length==0){
                      result += "return;";
                    }
                  result += "	}";
                }

                result += "	}";
                result += "</script>";

                //new
                result += "<select name=" + thisDeepID + " id=" + thisDeepID +
                    "   LANGUAGE=javascript onchange='return  " + thisDeepID +
                    "_onchange()'>";
                  thisDeep = this.getWorkContentList(cType);
                  for (i = 0; i < thisDeep.length; i++) {
                    if(thisDeep[i][0].equals(content)){
                    result += "\n" + "<option value=" + thisDeep[i][0] + " selected>" +
                     thisDeep[i][1] + "</option>";
                    }
                    else{
                      result += "\n" + "<option value=" + thisDeep[i][0] + ">" +
                          thisDeep[i][1] + "</option>";
                    }
                  }
                result = result + "</select>";
              }
            if(flag==3){
            result += "<select name=" + thisDeepID + " id=" + thisDeepID + " >";
            thisDeep = this.getWorkResultList(content);
                  for (i = 0; i < thisDeep.length; i++) {
                    result += "\n" + "<option value=" + thisDeep[i][0] + " selected>" +
                        thisDeep[i][1] + "</option>";
                  }
            result = result + "</select>";

            }
              }
          catch (Exception e) {

            }
          finally{
          dbo.close();
                  }
            }
            catch (Exception e) {

            }
            return result;
          }
      }
