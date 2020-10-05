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
     * �õ��û�auto_id,title���б�
     * @param deep �ͺŵ����
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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
     * �õ��û�auto_id,title���б�
     * @param deep �ͺŵ����
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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
     * �õ��ͺ���������
     * @sql formָ����������form,thisDeepID���������name,nextDeepID�¸��������name
     */
      public String getDropList(String form,String tableName,String thisDeepID,String nextDeepID,long deep,long sum){
         String result="";
          ResultSet rs=null;
          int i=0;
          int j=0;
      String[][] thisDeep = this.getModelDeepList(deep,tableName); //ȡ�ò��ż�����id�б�
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//�û�id�б�
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
                    result += "		oOption.text="+"\"��ѡ��\""+";";
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
                result += "\n" + "<option value=0>��ѡ��</option>";
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
            result += "\n" + "<option value=0>��ѡ��</option>";

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
     * �õ��ͺ���������
     * @sql formָ����������form,thisDeepID���������name,nextDeepID�¸��������name
     */
      public String getRootDropList(String form,String tableName,String thisDeepID,String nextDeepID,long deep,long sum){
         String result="";
          ResultSet rs=null;
          int i=0;
          int j=0;
      String[][] thisDeep = this.getModelDeepList(deep,tableName); //ȡ�ò��ż�����id�б�
      //out.print(thisDeep.length);
      String[][] nextDeep=null;//�û�id�б�
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
                    result += "		oOption.text="+"\"��ѡ��\""+";";
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
                result += "\n" + "<option value=0>��ѡ��</option>";
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
            result += "\n" + "<option value=0>��ѡ��</option>";

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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
        //�ó�����ĳ���
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
        //ע�����еĹرձ�����finally��ִ��
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
      String[][] nextDeep=null;//�û�id�б�
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
      String[][] nextDeep=null;//�û�id�б�
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
                    ".options[num].value;"; //�õ�ѡ�еĲ��ŵ�id��

                result += "if (side==" + "\"0\"" + "){ return; }";

                for (int r = 0; r < thisDeep.length; r++) {
                  //ȡ�����еĲ��ŵ�id
                  //�����еĲ���id����ѡ�е�id���Ƚϣ���������ȡ�����еĸò��ŵ��û�id
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
